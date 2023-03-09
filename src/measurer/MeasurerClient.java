package measurer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import resources.Consumption;
import utilityclasses.ProtocolHttp;

public class MeasurerClient {

	private Socket measurerSocket;
	private Consumption consumption;
	private double amount = 0;
	private double consumptionChangeVariable = 1;
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		MeasurerClient measurer = new MeasurerClient();
		measurer.startMeasurer();

	}

	private void startMeasurer() {

//		
//		do {
//			
//			System.out.println("---------------------------------------------------------");
//			System.out.println("----------------------Medidor----------------------------");
//			System.out.println("---------------------------------------------------------");
//
//			System.out.println("Digite o ID do medidor:");
//			String idMeasurer = scanner.nextLine();
//			
//			execMeasurerSocket("localhost",8000);
//			
//			req = ProtocolHttp.sendResponse();
//			
//			if() {
//				
//				
//			}
//			
//		}while();

	}

	private void generateSocket(String ip, int port) {

		try {

			measurerSocket = new Socket(ip, port);

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

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
