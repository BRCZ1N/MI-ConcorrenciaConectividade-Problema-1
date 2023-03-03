package server;

import java.io.IOException;
import java.net.Socket;
import apirest.ProtocolHttp;
import apirest.RequestHttp;

public class ThreadClient implements Runnable {

	private Socket socket;
	private String connection;

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public ThreadClient(Socket socket) {

		this.socket = socket;
		this.connection = (socket.getInetAddress() + ":" + socket.getPort());

	}

	@Override
	public void run() {

		boolean connectionStatus;

		connectionStatus = true;

		while (!socket.isClosed()) {

			RequestHttp req;

			try {

				req = ProtocolHttp.readRequest(socket);

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}
