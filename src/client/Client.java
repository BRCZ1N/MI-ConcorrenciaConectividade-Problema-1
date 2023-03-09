package client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import utilityclasses.Messages;

public class Client {

	private Socket clientSocket;
	private static Scanner scan = new Scanner(System.in);

	private void generateSocketClient(String ip, int port) {

		try {

			clientSocket = new Socket(ip, port);

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		Client client = new Client();
		client.generateSocketClient("localhost", 8000);

	}

	private void clientExecution() throws IOException {

		String clientMessage = "";
		String clientAuthentication = "";

		while (!clientMessage.equals("quit")) {

			do {

				System.out.println(clientAuthentication);
				clientAuthentication = "";
				System.out.println("------------Consumo de energia inteligente software------------");
				System.out.println("Digite o id:");
				String clientID = scan.nextLine();
				System.out.println("Digite a senha:");
				String clientPassword = scan.nextLine();
				Messages.sendMessage(clientSocket, clientID + ":" + clientPassword);
				clientAuthentication = (String) Messages.receiveMessage(clientSocket);

			} while (clientAuthentication.equals("USER NOT FOUND"));

		}

		clientSocket.close();

	}

}
