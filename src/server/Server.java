package server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket socketServer;
	private Socket clientSocket;
	private DatagramSocket datagramSocket;

	private void generateServer(int portServerSocket, int portDatagramSocket) throws IOException {

		socketServer = new ServerSocket(portServerSocket);
		datagramSocket = new DatagramSocket(portDatagramSocket);

	}

	private void generateAndStartThreadClient(Socket socketClient) {

		ThreadTcpClient threadTcpClient = new ThreadTcpClient(socketClient);
		new Thread(threadTcpClient).start();

	}

	private void execServer(int portServerSocket, int portDatagramSocket) throws IOException {

		boolean connection = true;

		generateServer(portServerSocket, portDatagramSocket);
		System.out.println("Server executado no IP:" + socketServer.getLocalPort());

		new Thread(() -> {

			while (connection) {

				ThreadUdpClient threadUdpClient = new ThreadUdpClient(datagramSocket);
				new Thread(threadUdpClient).start();

			}

		}).start();

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
		serverMain.execServer(8000, 8100);

	}

}
