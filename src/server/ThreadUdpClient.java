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

/**
 * Esta � a classe ThreadUdpClient, que � utilizada para representar e utilizar
 * de uma thread de um cliente UDP que se conecta ao servidor para auxiliar no
 * processamennto de dados
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class ThreadUdpClient implements Runnable {

	private DatagramSocket socket;
	private String connection;
	private DatagramPacket packet;
	private byte[] dataPacket;

	/**
	 * Esse � o m�todo, que retorna o ip e a porta em conjunto e em formato string
	 * para exibi��o no console do servidor
	 * 
	 * @return Representa��o em conjunto em string do ip e da porta do servidor
	 */
	public String getConnection() {
		return connection;
	}

	/**
	 * Esse � o m�todo, que seta o ip e a porta em conjunto e em formato string para
	 * exibi��o no console do servidor
	 * 
	 * @param String connection - Representa��o em conjunto usando string do ip e da
	 *               porta do servidor
	 */

	public void setConnection(String connection) {
		this.connection = connection;
	}

	/**
	 * Esse � o construtor da classe ThreadUdpClient, que constroe os objetos que
	 * representam as threads do cliente UDP
	 * 
	 * @param DatagramSocket socket - Socket UDP do cliente
	 * @param DatagramPacket packet - Pacote UDP do cliente
	 * @param byte[]         dataPacket - Buffer de dados do cliente
	 */
	public ThreadUdpClient(DatagramSocket socket, DatagramPacket packet, byte[] dataPacket) {

		this.socket = socket;
		this.packet = packet;
		this.dataPacket = dataPacket;
		this.connection = (socket.getInetAddress() + ":" + socket.getPort());

	}

	/**
	 * Esse � o m�todo, que utiliza sobrescrita e deve ser implementado atrav�s da
	 * interface runnable para a execu��o de threads, neste caso para a thread do
	 * cliente UDP
	 */
	@Override
	public void run() {

		try {

			String message = new String(dataPacket, StandardCharsets.UTF_8);
			Pattern pattern = Pattern.compile("[0-9]+-[0-9]+\\.[0-9]+-[0-9]+/[0-9]+/[0-9]+ [0-9]+:[0-9]+:[0-9]+");
			Matcher matcher = pattern.matcher(message);

			if (matcher.find()) {

				String[] messageConsumption = message.split("-");

				Consumption consumption = new Consumption(Double.parseDouble(messageConsumption[1].trim()),
						messageConsumption[2].trim());
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
