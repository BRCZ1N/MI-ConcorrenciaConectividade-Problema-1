package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import resources.Consumption;
import services.ConsumptionServices;
import services.UserServices;

public class ThreadUdpClient implements Runnable {

	private DatagramSocket socket;
	private String connection;
	private DatagramPacket packet;
	private byte[] dataPacket;

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public ThreadUdpClient(DatagramSocket socket, DatagramPacket packet, byte[] dataPacket) {

		this.socket = socket;
		this.packet = packet;
		this.dataPacket = dataPacket;
		this.connection = (socket.getInetAddress() + ":" + socket.getPort());

	}

	@Override
	public void run() {

		try {
			
			String message = new String(dataPacket, StandardCharsets.UTF_8);
			Pattern pattern = Pattern.compile("[0-9]+-[0-9]+\\.[0-9]+-[0-9]+/[0-9]+/[0-9]+ [0-9]+:[0-9]+:[0-9]+");
			Matcher matcher = pattern.matcher(message);
			
			if (matcher.find()) {
				
				String[] messageConsumption = message.split("-");
				
				Consumption consumption = new Consumption(Double.parseDouble(messageConsumption[1].trim()),messageConsumption[2].trim());
				ConsumptionServices.addConsumption(messageConsumption[0].trim(), consumption);

			} else {
				
				String[] messageCredentials = message.split(":");
				byte[] byteCopy = UserServices.authenticateClient(messageCredentials[0], messageCredentials[1]);
				System.arraycopy(byteCopy, 0, dataPacket, 0, byteCopy.length);
				packet = new DatagramPacket(dataPacket, dataPacket.length, packet.getAddress(), packet.getPort());
				socket.send(packet);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
