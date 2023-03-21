package client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import org.json.JSONObject;

import http.ProtocolHttp;
import http.RequestHttp;
import http.ResponseHttp;
import utilityclasses.HttpCodes;
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

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		Client client = new Client();
		client.generateSocketClient("localhost", 8000);
		client.clientExecution();

	}

	private void clientExecution() throws IOException, InterruptedException {

		String clientAuthentication = "";
		RequestHttp request;
		ResponseHttp resp;

		do {

			System.out.println("===================================================");
			System.out.println("========= Consumo de energia inteligente ==========");
			System.out.println("===================================================");
			System.out.println("Digite o id:");
			clientID = scan.next();
			System.out.println("Digite a senha:");
			clientPassword = scan.next();

			Map<String, String> mapHeaders = new HashMap<>();
			mapHeaders.put("Host", clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort());
			request = new RequestHttp(HttpMethods.GET,"/user/auth/id:" + clientID.replace(" ", "") + "&password:" + clientPassword.replace(" ", ""),"HTTP/1.1", mapHeaders);
			ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
			Thread.sleep(100);
			resp = readResponse(clientSocket.getInputStream());

		} while (!resp.getStatusLine().equals(HttpCodes.HTTP_200.getCodeHttp()));

		clientMenu();

	}

	private void clientMenu() throws IOException, InterruptedException {

		boolean connection = true;

		while (connection) {

			System.out.println("===================================================");
			System.out.println("========= Consumo de energia inteligente ==========");
			System.out.println("===================================================");
			System.out.println("================ Menu de cliente ==================");
			System.out.println("===================================================");
			System.out.println("====== (1) - Visualizar histórico de consumo");
			System.out.println("====== (2) - Gerar fatura");
			System.out.println("====== (3) - Visualizar fatura");
			System.out.println("====== (4) - Visualizar todas as faturas geradas");
			System.out.println("====== (5) - Status de consumo do cliente");
			System.out.println("====== (6) - Desconectar");
			System.out.println("=========== Digite a opçao desejada ===============");

			String opcao = scan.next();

			RequestHttp request;
			ResponseHttp response;
			JSONObject jsonBody;
			Map<String, String> mapHeaders = new HashMap<>();

			switch (opcao) {

				case "1":

					mapHeaders = new HashMap<>();
					mapHeaders.put("Host",clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort());
					request = new RequestHttp(HttpMethods.GET, "/consumption/historic/" + clientID, "HTTP/1.1",mapHeaders);
					ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
					Thread.sleep(100);
					response = readResponse(clientSocket.getInputStream());

					if (response.getStatusLine().equals(HttpCodes.HTTP_200.getCodeHttp().replaceAll("\r\n", ""))) {

						System.out.println("Historico do cliente:");
						jsonBody = new JSONObject(response.getBody());
						System.out.println("Idenficador do cliente: " + jsonBody.get("idClient"));
						System.out.println("Historico de consumo do cliente:");
						System.out.println(jsonBody.getJSONArray("historic"));
						System.out.println();

					} else {
						
						System.out.println("ERRO:");
						System.out.println(response.getStatusLine());

					}

					break;

				case "2":

					mapHeaders = new HashMap<>();
					mapHeaders.put("Host",clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort());
					request = new RequestHttp(HttpMethods.GET, "/invoice/newInvoice/" + clientID, "HTTP/1.1", mapHeaders);
					ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
					Thread.sleep(100);
					response = readResponse(clientSocket.getInputStream());
					
					if (response.getStatusLine().equals(HttpCodes.HTTP_201.getCodeHttp())) {

						jsonBody = new JSONObject(response.getBody());
						System.out.println("Fatura gerada:");
						System.out.println(jsonBody);
						System.out.println();

					} else {

						System.out.println("ERRO:");
						System.out.println(response.getStatusLine());

					}
					break;

				case "3":

					System.out.println("Digite o id da fatura:");
					String idInvoice = scan.next();
					mapHeaders = new HashMap<>();
					mapHeaders.put("Host",clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort());
					request = new RequestHttp(HttpMethods.GET, "/invoice/" + idInvoice, "HTTP/1.1", mapHeaders);
					ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
					Thread.sleep(100);
					response = readResponse(clientSocket.getInputStream());
					
					if (response.getStatusLine().equals(HttpCodes.HTTP_200.getCodeHttp())) {

						jsonBody = new JSONObject(response.getBody());
						System.out.println("Fatura:");
						System.out.println(jsonBody);
						System.out.println();
						
					}else {
						
						System.out.println("ERRO:");
						System.out.println(response.getStatusLine());
						
					}
					break;

				case "4":

					mapHeaders = new HashMap<>();
					mapHeaders.put("Host",clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort());
					request = new RequestHttp(HttpMethods.GET, "/invoice/all/" + clientID, "HTTP/1.1", mapHeaders);
					ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
					Thread.sleep(100);
					response = readResponse(clientSocket.getInputStream());
					
					if(response.getStatusLine().equals(HttpCodes.HTTP_200.getCodeHttp())) {
						
						jsonBody = new JSONObject(response.getBody());
						System.out.println("Faturas:");
						System.out.println(jsonBody);
						System.out.println();
						
					}else {
						
						System.out.println(response.getStatusLine());
						
					}
					break;

				case "5":

					mapHeaders = new HashMap<>();
					mapHeaders.put("Host",clientSocket.getLocalAddress().getHostAddress() + ":" + clientSocket.getLocalPort());
					request = new RequestHttp(HttpMethods.GET, "/user/statusConsumption/" + clientID, "HTTP/1.1",mapHeaders);
					ProtocolHttp.sendMessage(clientSocket.getOutputStream(), request.toString());
					Thread.sleep(18);
					response = readResponse(clientSocket.getInputStream());
					
					if(response.getStatusLine().equals(HttpCodes.HTTP_200.getCodeHttp())) {
						
						jsonBody = new JSONObject(response.getBody());
						System.out.println("Status de consumo:");
						System.out.println(jsonBody);
						System.out.println();
						
					}else {
						
						System.out.println("ERRO:");
						System.out.println(response.getStatusLine());
							
					}
					break;

				case "6":

					connection = false;
					break;

				default:

					System.out.println("Opção invalida");
					break;

			}

		}

		System.out.println("Sessão encerrada");

	}

	public ResponseHttp readResponse(InputStream input) throws IOException {

		ResponseHttp req = new ResponseHttp();
		Queue<String> httpData = new LinkedList<String>();
		String reqLine = null;
		String responseHeaders;
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

			req = new ResponseHttp(responseHeaders, mapHeaders, bodyJson.toString());

		}

		return req;
	}

}
