package measurer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import resources.Consumption;
import utilityclasses.ProtocolHttp;

public class MeasurerClient {

	private DatagramSocket measurerSocket;
	private DatagramPacket measurerPacket;
	private Consumption consumption;
	private double amount = 0;
	private double consumptionChangeVariable = 1;
	private Scanner scanner = new Scanner(System.in);
	private byte[] bytePackage = new byte[1024];

	public static void main(String[] args) throws UnknownHostException {

		MeasurerClient measurer = new MeasurerClient();
		measurer.startMeasurer();

	}

	private void startMeasurer() throws UnknownHostException {

		try {
			measurerSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userCredentials;
		String authenticate;

		do {

			System.out.println("---------------------------------------------------------");
			System.out.println("----------------------Medidor----------------------------");
			System.out.println("---------------------------------------------------------");

			System.out.println("Digite a porta do servidor:");
			String portServer = scanner.nextLine();

			System.out.println("Digite o IP do servidor:");
			String ipServer = scanner.nextLine();
			InetAddress ip = InetAddress.getByName(ipServer);

			System.out.println("Digite o ID do cliente:");
			String idClient = scanner.nextLine();

			System.out.println("Digite a senha do cliente:");
			String passwordClient = scanner.nextLine();
			
			userCredentials = String.format("%s:%s", idClient, passwordClient);
			bytePackage = userCredentials.getBytes();
			measurerPacket = new DatagramPacket(bytePackage, bytePackage.length, ip, Integer.parseInt(portServer));
			try {
				measurerSocket.send(measurerPacket);
				measurerSocket.receive(measurerPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while ((authenticate = new String(measurerPacket.getData(),0,measurerPacket.getLength())) != "authenticate");

		execMeasurer();

	}

	private void execMeasurer() {

		new Thread(() -> {

			while (true) {

				amount += consumptionChangeVariable;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();

		while (true) {

			System.out.println("Digite o novo ritmo de consumo:");
			double amountTest = scanner.nextDouble();
			if (amount <= 0) {

				System.out.println("Ritmo de consumo inválido");

			} else {

				System.out.println("Consumo alterado");
				amount = amountTest;

			}

		}

	}

}
