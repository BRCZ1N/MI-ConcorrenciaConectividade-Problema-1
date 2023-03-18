package client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import org.json.JSONException;

import http.ProtocolHttp;
import http.RequestHttp;
import http.ResponseHttp;
import utilityclasses.HttpMethods;

public class Client {

	private Socket clientSocket;
	private Scanner scan = new Scanner(System.in);
	private String clientID;
	private String clientPassword;

	private void generateSocketClient(String ip, int port) {

		try {

			clientSocket = new Socket(ip, port);

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		Client client = new Client();
		client.generateSocketClient("localhost", 8000);
		client.clientExecution();

	}

	private void clientExecution() throws IOException {

		String clientAuthentication = "";

		do {

			clientAuthentication = "";
			System.out.println("===================================================");
			System.out.println("========= Consumo de energia inteligente ==========");
			System.out.println("===================================================");
			System.out.println("Digite o id:");
			clientID = scan.nextLine();
			System.out.println("Digite a senha:");
			clientPassword = scan.nextLine();
			
			Map<String,String >mapHeaders = new HashMap<>();
			mapHeaders.put("Content-Length", "0");
			RequestHttp request = new RequestHttp(HttpMethods.GET, "/user/historic/" + clientID, "HTTP/1.1");
			ProtocolHttp.sendMessage(clientSocket.getOutputStream(),request.toString());
			
		} while (readResponse(clientSocket.getInputStream()).getBody().equals("NAO AUTENTICADO"));

	}

	private void clientMenu() throws IOException {

		boolean connection;
		
		while ((connection = true)) {

			System.out.println("===================================================");
			System.out.println("========= Consumo de energia inteligente ==========");
			System.out.println("===================================================");
			System.out.println("================ Menu de cliente ==================");
			System.out.println("===================================================");
			System.out.println("====== (1) - Visualizar histórico de consumo     ==");
			System.out.println("====== (2) - Gerar fatura					     ==");
			System.out.println("====== (3) - Visualizar todas as faturas geradas ==");
			System.out.println("====== (4) - Visualizar fatura					 ==");
			System.out.println("====== (5) - Status de consumo do cliente	   	 ==");
			System.out.println("====== (6) - Desconectar 					   	 ==");
			System.out.println("=========== Digite a opção desejada ===============");

			String opcao = scan.nextLine();

			RequestHttp request;
			ResponseHttp response;

			switch (opcao) {

			case "1":

				request = new RequestHttp(HttpMethods.GET, "/consumption/historic/" + clientID, "HTTP/1.1");
				ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
				response = readResponse(clientSocket.getInputStream());
				System.out.println("Histórico do cliente:");
				System.out.println(response);
				break;

			case "2":

				request = new RequestHttp(HttpMethods.GET, "/invoice/newInvoice/" + clientID, "HTTP/1.1");
				ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
				response = readResponse(clientSocket.getInputStream());
				System.out.println("Fatura gerada:");
				System.out.println(response);
				break;

			case "3":

				request = new RequestHttp(HttpMethods.GET, "/invoice/" + clientID, "HTTP/1.1");
				ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
				response = readResponse(clientSocket.getInputStream());
				System.out.println("Fatura:");
				System.out.println(response);
				break;

			case "4":

				request = new RequestHttp(HttpMethods.GET, "/invoice/all/" + clientID, "HTTP/1.1");
				ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
				response = readResponse(clientSocket.getInputStream());
				System.out.println("Faturas:");
				break;

			case "5":

				request = new RequestHttp(HttpMethods.GET, "/consumption/statusConsumption/" + clientID, "HTTP/1.1");
				ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
				response = readResponse(clientSocket.getInputStream());
				System.out.println("Status de consumo:");
				System.out.println(response);
				break;

			default:

				System.out.println("Opção invalida");
				break;

			}

		}

	}

	public ResponseHttp readResponse(InputStream input) throws IOException {

		ResponseHttp req = new ResponseHttp();
		Queue<String> httpData = new LinkedList<String>();
		String reqLine = null;
		String responseHeaders = null;
		Map<String, String> mapHeaders = null;
		StringBuilder str = new StringBuilder();
		String[] linesReq;

		BufferedInputStream buffer = new BufferedInputStream(input);

		if (buffer.available() > 0) {

			while (buffer.available() > 0) {

				str.append((char) buffer.read());

			}

			linesReq = str.toString().split("\r\n");

			for (String line : linesReq) {

				httpData.add(line);

			}

			responseHeaders = httpData.poll();
			mapHeaders = new HashMap<String, String>();

			while (!httpData.isEmpty() && !(reqLine = httpData.poll()).isBlank()) {

				String[] header = reqLine.split(":\s");
				mapHeaders.put(header[0], header[1]);

			}

			StringBuilder bodyJson = new StringBuilder();
			String bodyLine;

			while ((bodyLine = httpData.poll()) != null) {

				bodyJson.append(bodyLine);

			}

			try {

				req = new ResponseHttp(responseHeaders, mapHeaders, bodyJson.toString());

			} catch (JSONException e) {

			} finally {

				req = new ResponseHttp(responseHeaders, mapHeaders);

			}

		}

		return req;

	}

}
