package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		BufferedReader buffer;
		RequestHttp req;

		try {

			while (!isEmptyBuffer((buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()))))) {

				req = ProtocolHttp.readRequest(buffer);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public boolean isEmptyBuffer(BufferedReader buffer) throws IOException {
		
		buffer.mark(Integer.MAX_VALUE);
		if (buffer.readLine() != null) {

			buffer.reset();
			return false;

		}

		buffer.reset();
		return true;

	}

}
