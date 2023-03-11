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

		while (true) {

			String message = new String(packet.getData(), StandardCharsets.UTF_8);
			Pattern pattern = Pattern
					.compile("(\\d+)-(\\d+.\\d+)-(\\d{2})/(\\d{2})/(\\d{4}) (\\d{2}):(\\d{2}):(\\d{2})");
			Pattern patternAuthenticator = Pattern.compile("\\w+:\\w+");
			Matcher matcher = pattern.matcher(message);
			Matcher matcherAuthenticator = patternAuthenticator.matcher(message);
			
			if (matcher.matches()) {

				String[] messageConsumption = message.split("-");
				Consumption consumption = new Consumption(Double.parseDouble(messageConsumption[1]),
						messageConsumption[2]);
				ConsumptionServices.addConsumption(messageConsumption[0], consumption);

			} else if (matcherAuthenticator.matches()) {

				String[] messageCredentials = message.split(":");
				dataPacket = UserServices.authenticateClient(messageCredentials[0], messageCredentials[1]);
				packet = new DatagramPacket(dataPacket, dataPacket.length, packet.getAddress(), packet.getPort());
				try {
					socket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {
					
					dataPacket = "denied authenticate".getBytes();
					packet = new DatagramPacket(dataPacket, dataPacket.length,packet.getAddress(),packet.getPort());
					socket.send(packet);
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
