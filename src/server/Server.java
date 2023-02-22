package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import allclasses.AdministratorUser;
import allclasses.ClientUser;
import allclasses.Messages;

public class Server {

	private ServerSocket socketServer;
	private Socket clientSocket;

	private void generateServerSocket(int portServer) throws IOException {

		socketServer = new ServerSocket(portServer);

	}

	private boolean generateAndStartThreadClient(Socket socketClient, String userCredentials) {

		String[] userCredentialsSplit = userCredentials.split(":");

		if (userAuthentication(userCredentialsSplit[0], userCredentialsSplit[1])) {

			ThreadClient threadClient = new ThreadClient(socketClient);
			new Thread(threadClient).start();
			Messages.sendMessage(socketClient, "LOGADO COM SUCESSO");
			return true;

		} else {

			Messages.sendMessage(socketClient, "USER NOT FOUND");
			return false;

		}

	}

	private void execServer(int portServer) throws IOException {

		boolean connection = true;

		generateServerSocket(portServer);
		System.out.println("Server executado na porta: " + socketServer.getLocalPort());
		System.out.println("Server executado no IP:" + socketServer.getInetAddress());

		while (connection) {

			clientSocket = socketServer.accept();

			boolean confirmClient;
			
			do {

				confirmClient = generateAndStartThreadClient(clientSocket,(String) Messages.receiveMessage(clientSocket));

			} while (!confirmClient);

		}

		socketServer.close();

	}

	public static void main(String[] args) throws IOException {

		Server serverMain = new Server();
		serverMain.execServer(12345);

	}

}
