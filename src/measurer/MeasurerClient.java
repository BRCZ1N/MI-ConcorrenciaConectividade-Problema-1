package measurer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class MeasurerClient {

	private DatagramSocket measurerSocket;
	private DatagramPacket measurerPacket;
	private double amount = 0;
	private double consumptionChangeVariable = 1;
	private Scanner scanner = new Scanner(System.in);
	private byte[] bytePackage = new byte[1024];
	private String idClient;
	private String passwordClient;

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

//			System.out.println("Digite a porta do servidor:");
//			String portServer = scanner.nextLine();
//
//			System.out.println("Digite o IP do servidor:");
//			String ipServer = scanner.nextLine();
//			InetAddress ip = InetAddress.getByName(ipServer);

			System.out.println("Digite o ID do cliente:");
			idClient = scanner.nextLine();

			System.out.println("Digite a senha do cliente:");
			passwordClient = scanner.nextLine();

			userCredentials = String.format("%s:%s", idClient, passwordClient);
			bytePackage = userCredentials.getBytes();
			measurerPacket = new DatagramPacket(bytePackage, bytePackage.length, InetAddress.getByName("127.0.0.1"),8100);
			
			try {
				measurerSocket.send(measurerPacket);
				measurerSocket.receive(measurerPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while ((authenticate = new String(measurerPacket.getData(), 0,measurerPacket.getLength())) != "authenticate");
		
		execMeasurer();

	}

	private void execMeasurer() {

		new Thread(() -> {

			while (true) {

				amount += consumptionChangeVariable;

			}

		}).start();

		new Thread(() -> {

			while (true) {

				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				String dateTime = String.format(LocalDate.now().toString(), dateTimeFormatter);
				String str = String.format("%s-%s-%s", idClient, Double.toString(amount), dateTime);
				bytePackage = str.getBytes();
				try {
					measurerPacket = new DatagramPacket(bytePackage, bytePackage.length, InetAddress.getByName("127.0.0.1"), 8100);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					measurerSocket.send(measurerPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();

		while (true) {

			System.out.println("Digite o novo ritmo de consumo:");
			double amountTest = scanner.nextDouble();
			if (amount <= 0) {

				System.out.println("Ritmo de consumo inv�lido");

			} else {

				System.out.println("Consumo alterado");
				amount = amountTest;

			}

		}

	}

}
