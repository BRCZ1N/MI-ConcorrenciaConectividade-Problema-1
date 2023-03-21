package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import http.ProtocolHttp;
import http.RequestHttp;
import routers.PathRouter;

public class ThreadTcpClient implements Runnable {

	private Socket socket;
	private String connection;

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public ThreadTcpClient(Socket socket) {

		this.socket = socket;
		this.connection = (socket.getInetAddress() + ":" + socket.getPort());

	}

	@Override
	public void run() {

		RequestHttp reqHttp;
		PathRouter pathRouter = new PathRouter();
		String respHttp;

		
		try {

			while (true) {
				

				if(socket.getInputStream().available() > 0) {
					
					reqHttp = ProtocolHttp.readRequest(socket.getInputStream());

					if (reqHttp != null) {
					
						respHttp = pathRouter.execRoute(reqHttp);
						ProtocolHttp.sendMessage(socket.getOutputStream(), respHttp);
						System.out.println();
						System.out.println("===================================================================");
						System.out.println("Resposta enviada ao cliente conectado na porta:" + socket.getPort());
						System.out.println(respHttp);
						System.out.println("===================================================================");
						reqHttp = null;

					}
					
				}

			}

		} catch (IOException e) {

			Thread.currentThread().interrupt();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
