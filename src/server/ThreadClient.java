package server;

import java.io.IOException;
import java.net.Socket;
import micc.Messages;

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

		boolean connectionStatus = true;

		String clientMessage = "";

		while (connectionStatus) {

			if (!clientMessage.equals("quit")) {

				try {

					clientMessage = (String) Messages.receiveMessage(socket);
					System.out.println(clientMessage);

				} catch (ClassCastException e) {

					e.getStackTrace();
				}

			} else {

				connectionStatus = false;

			}
			

		}
		
		try {

			socket.close();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}

}
