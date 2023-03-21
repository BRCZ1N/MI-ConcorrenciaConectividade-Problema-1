package measurer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
		String authenticate = "denied authenticate";

		do {

			System.out.println("---------------------------------------------------------");
			System.out.println("----------------------Medidor----------------------------");
			System.out.println("---------------------------------------------------------");

			System.out.println("Digite o ID do cliente:");
			idClient = scanner.next();

			System.out.println("Digite a senha do cliente:");
			passwordClient = scanner.next();

			userCredentials = String.format("%s:%s", idClient, passwordClient);

			byte[] byteCopy = userCredentials.getBytes();
			System.arraycopy(byteCopy, 0, bytePackage, 0, byteCopy.length);

			try {

				measurerPacket = new DatagramPacket(bytePackage, bytePackage.length, InetAddress.getByName("localhost"),8100);
				measurerSocket.send(measurerPacket);
				measurerPacket = new DatagramPacket(bytePackage, bytePackage.length);
				Thread.sleep(18);
				measurerSocket.receive(measurerPacket);
				authenticate = (new String(bytePackage, StandardCharsets.UTF_8));
				bytePackage = new byte[1024];
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (authenticate.trim().equals("denied authenticate"));

		execMeasurer();

	}

	private void execMeasurer() {

		System.out.println("Medidor ativo");

		new Thread(() -> {

			while (true) {

				amount += consumptionChangeVariable;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
				LocalDateTime dateHour = LocalDateTime.now();
				String dateTime = dateHour.format(dateTimeFormatter);
				String str = String.format("%s-%s-%s", idClient, Double.toString(amount), dateTime);
				byte[] byteCopy = str.getBytes();
				System.arraycopy(byteCopy, 0, bytePackage, 0, byteCopy.length);

				try {
					measurerPacket = new DatagramPacket(bytePackage, bytePackage.length,
							InetAddress.getByName("localhost"), 8100);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					amount = 0;
					measurerSocket.send(measurerPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}).start();

		while (true) {

			System.out.println("Se quiser alterar o valor de ritmo do medidor");
			System.out.println("Digite o novo ritmo de consumo:");
			double rhythmConsumption = scanner.nextDouble();
			if (amount <= 0) {

				System.out.println("Ritmo de consumo inv�lido");

			} else {

				System.out.println("Ritmo de consumo alterado");
				consumptionChangeVariable += rhythmConsumption;

			}

		}

	}

}
