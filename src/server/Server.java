package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket socketServer;
	private Socket clientSocket;

	private void generateServerSocket(int portServer) throws IOException {

		socketServer = new ServerSocket(portServer);

	}

	private void generateAndStartThreadClient(Socket socketClient) {

		ThreadClient threadClient = new ThreadClient(socketClient);
		new Thread(threadClient).start();

	}

	private void execServer(int portServer) throws IOException {

		boolean connection = true;

		generateServerSocket(portServer);
		System.out.println("Server executado no IP:" + socketServer.getLocalPort());

		while (connection) {

			clientSocket = socketServer.accept();
			System.out.println("Client connected");
			System.out.println(clientSocket.getInetAddress());
			generateAndStartThreadClient(clientSocket);

		}

		socketServer.close();

	}

	public static void main(String[] args) throws IOException {

		Server serverMain = new Server();
		serverMain.execServer(8000);

	}

}
