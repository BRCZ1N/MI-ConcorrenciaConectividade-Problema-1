package measurer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Esta é a classe MeasurerClient, que representa a aplicação do medidor UDP que
 * se conecta ao servidor.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class MeasurerClient {

	private DatagramSocket measurerSocket;
	private DatagramPacket measurerPacket;
	private double amount = 0;
	private double consumptionChangeVariable = 1;
	private Scanner scanner = new Scanner(System.in);
	private byte[] bytePackage = new byte[1024];
	private String idClient;
	private String passwordClient;

	/**
	 * Este é o metodo principal dessa aplicação que inicia a mesma. Ele recebe um
	 * array de argumentos de linha de comando como entrada.
	 *
	 * @param String[] args - O array de argumentos de linhas de comando.
	 * 
	 */

	public static void main(String[] args) throws UnknownHostException {

		MeasurerClient measurer = new MeasurerClient();
		measurer.generateSocketClient();
		measurer.startMeasurer();

	}

	/**
	 * Este é o metodo que instancia o socket UDP do medidor
	 */
	private void generateSocketClient() {

		try {

			measurerSocket = new DatagramSocket();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Esse é o metodo de execução do menu de login dessa aplicação.
	 */
	private void startMeasurer() throws UnknownHostException {

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

				measurerPacket = new DatagramPacket(bytePackage, bytePackage.length,
						InetAddress.getByName("172.16.103.3"), 8100);
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

	/**
	 * Esse é o metodo de execução do simulador de medidor da aplicação.
	 */
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
							InetAddress.getByName("172.16.103.3"), 8100);
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

				System.out.println("Ritmo de consumo invï¿½lido");

			} else {

				System.out.println("Ritmo de consumo alterado");
				consumptionChangeVariable += rhythmConsumption;

			}

		}

	}

}
