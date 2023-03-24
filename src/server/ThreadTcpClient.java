package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import http.ProtocolHttp;
import http.RequestHttp;
import routers.PathRouter;

/**
 * Esta � a classe ThreadTcpClient, que � utilizada para representar e utilizar
 * de uma thread de um cliente TCP que se conecta ao servidor para auxiliar no
 * processamennto de dados
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class ThreadTcpClient implements Runnable {

	private Socket socket;
	private String connection;

	/**
	 * Esse � o m�todo, que retorna o ip e a porta em conjunto e em formato string
	 * para exibi��o no console do servidor
	 * 
	 * @return Representa��o em conjunto em string do ip e da porta do servidor
	 */

	public String getConnection() {
		return connection;
	}

	/**
	 * Esse � o m�todo, que seta o ip e a porta em conjunto e em formato string para
	 * exibi��o no console do servidor
	 * 
	 * @param connection - Representa��o em conjunto usando string do ip e da porta do servidor
	 */
	public void setConnection(String connection) {
		this.connection = connection;
	}

	/**
	 * Esse � o construtor da classe ThreadTcpClient, que constroe os objetos que
	 * representam as threads do cliente TCP
	 * 
	 * @param socket - Socket TCP do cliente
	 */

	public ThreadTcpClient(Socket socket) {

		this.socket = socket;
		this.connection = (socket.getInetAddress() + ":" + socket.getPort());

	}

	/**
	 * Esse � o m�todo, que utiliza sobrescrita e deve ser implementado atrav�s da
	 * interface runnable para a execu��o de threads, neste caso para a thread do
	 * cliente TCP
	 */
	@Override
	public void run() {

		RequestHttp reqHttp;
		PathRouter pathRouter = new PathRouter();
		String respHttp;

		try {

			while (true) {

				if (socket.getInputStream().available() > 0) {

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
