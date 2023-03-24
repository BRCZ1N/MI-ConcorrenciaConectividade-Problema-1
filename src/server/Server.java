package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import services.ConsumptionServices;
import services.InvoiceServices;
import services.UserServices;

/**
 * Esta é a classe Server, que representa a aplicação do servidor
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class Server {

	private ServerSocket socketServer;
	private Socket clientSocket;
	private DatagramSocket datagramSocket;
	private DatagramPacket datagramPacket;
	private byte[] bufferPacket = new byte[1024];
	private UserServices userService = new UserServices();
	private ConsumptionServices consumptionService = new ConsumptionServices();
	private InvoiceServices invoiceService = new InvoiceServices();

	/**
	 * Esse é o método que instancia sockets para o servidor para conexões TCP para
	 * clientes http e conexões UDP para os medidores, para isso ele recebe como
	 * parametros uma porta para o socket TCP e uma para o socket UDP
	 *
	 * @param portServerSocket   - Porta do servidor TCP
	 * @param portDatagramSocket - Porta do servidor UDP
	 * @throws IOException
	 */
	private void generateServer(int portServerSocket, int portDatagramSocket) throws IOException {

		socketServer = new ServerSocket(portServerSocket);
		datagramSocket = new DatagramSocket(portDatagramSocket);

	}

	/**
	 * Esse é o método que gera e inicia uma Thread para clientes TCP da aplicação
	 * servidor
	 *
	 * @param socketClientTCP - Socket do cliente TCP
	 */
	private void generateAndStartThreadClientTCP(Socket socketClientTCP) {

		ThreadTcpClient threadTcpClient = new ThreadTcpClient(socketClientTCP);
		new Thread(threadTcpClient).start();

	}

	/**
	 * Esse é o método que gera e inicia uma Thread para os medidores UDP da
	 * aplicação servidor
	 *
	 * @param datagramSocket - Socket UDP do medidor
	 * @param datagramPacket - Pacote datagram do medidor UDP
	 * @param bufferPacker   - Buffer de dados
	 */
	private void generateAndStartThreadClientUDP(DatagramSocket datagramSocket, DatagramPacket datagramPacket,
			byte[] bufferPacket) {

		ThreadUdpClient threadUdpClient = new ThreadUdpClient(datagramSocket, datagramPacket, bufferPacket);
		new Thread(threadUdpClient).start();

	}

	/**
	 * Esse é o método que executa o servidor desde as proprias threads do servidor
	 * até as proprios sockets UDP e TCP
	 * 
	 * @param portServerSocket   - Porta TCP para o servidor
	 * @param portDatagramSocket - Porta UDP para o servidor
	 * @throws IOException
	 */
	private void execServer(int portServerSocket, int portDatagramSocket) throws IOException {

		UserServices.generateUsersTest();
		boolean connection = true;

		generateServer(portServerSocket, portDatagramSocket);
		System.out.println("Server executado no IP:" + InetAddress.getLocalHost().getHostAddress() + " - Porta:"
				+ socketServer.getLocalPort());

		new Thread(() -> {

			try {

				while (connection) {

					datagramPacket = new DatagramPacket(bufferPacket, bufferPacket.length);
					datagramSocket.receive(datagramPacket);
					generateAndStartThreadClientUDP(datagramSocket, datagramPacket, bufferPacket);

				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		}).start();

		while (connection) {

			clientSocket = socketServer.accept();
			System.out.println("Cliente conectado a partir da porta:" + clientSocket.getPort());
			generateAndStartThreadClientTCP(clientSocket);

		}

		socketServer.close();

	}

	/**
	 * Este é o metodo principal dessa aplicação que inicia a mesma. Ele recebe um
	 * array de argumentos de linha de comando como entrada.
	 *
	 * @param args - O array de argumentos de linhas de comando.
	 * @throws IOException Erro de entrada e saida
	 */
	public static void main(String[] args) throws IOException {

		Server serverMain = new Server();
		serverMain.execServer(8000, 8100);

	}

}
