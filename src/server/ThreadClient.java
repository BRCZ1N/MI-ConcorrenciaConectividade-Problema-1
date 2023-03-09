package server;

import java.io.IOException;
import java.net.Socket;
import routers.PathRouter;
import utilityclasses.ProtocolHttp;
import utilityclasses.RequestHttp;

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

		RequestHttp req;
		PathRouter pathRouter = new PathRouter();
		try {

			while (true) {

				req = ProtocolHttp.readRequest(socket.getInputStream());

				if (req != null) {

					pathRouter.execRoute(req);

					req = null;

				}

			}

		} catch (IOException e) {

			System.out.println("Conexao encerrada:" + socket.getLocalPort());
			Thread.currentThread().interrupt();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
