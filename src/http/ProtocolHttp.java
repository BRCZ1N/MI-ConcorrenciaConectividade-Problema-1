package http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

import utilityclasses.HttpMethods;

/**
 * Esta é a classe ProtocolHttp, que possui os métodos de leitura de requisições
 * http advindas de um cliente e de envio de respostas http para o cliente
 * conectado ao servidor.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class ProtocolHttp {

	/**
	 * Esse método, faz a leitura da requisição http advinda de um cliente e molda
	 * em um objeto que representa uma requisição http para o servidor entender e
	 * processar.
	 *
	 * @param input - O InputStream do socket que contém a requisição advinda do
	 *              cliente.
	 * @return O objeto que representa a requisição http para o servidor processar.
	 * @throws IOException Exception de entrada e saida
	 * @throws InterruptedException Exception de thread interrompido
	 */
	public static RequestHttp readRequest(InputStream input) throws IOException, InterruptedException {

		RequestHttp req = new RequestHttp();
		Queue<String> httpData = new LinkedList<String>();
		String reqLine = null;
		String responseHeaders[] = null;
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

			responseHeaders = httpData.poll().split("\s");
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

				req = new RequestHttp(HttpMethods.valueOf(responseHeaders[0]), responseHeaders[1], responseHeaders[2],
						mapHeaders, new JSONObject(bodyJson.toString()));

			} catch (JSONException e) {

			} finally {

				req = new RequestHttp(HttpMethods.valueOf(responseHeaders[0]), responseHeaders[1], responseHeaders[2],
						mapHeaders);

			}

		}

		return req;

	}

	/**
	 * Esse método, é usado para enviar a mensagem do cliente para o servidor ou do
	 * servidor para o cliente
	 *
	 * @param output   - O OutputStream do socket que deve receber a mensagem
	 * @param response - A mensagem em formato string
	 * @throws IOException Erro de entrada e saida
	 * 
	 */
	public static void sendMessage(OutputStream output, String response) throws IOException {

		BufferedOutputStream buffer = new BufferedOutputStream(output);

		buffer.write(response.getBytes("UTF-8"));
		buffer.flush();

	}

}
