package http;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Esta é a classe ResponseHttp, que representa a resposta da requisição que
 * será enviada ao cliente conectado ao servidor.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class ResponseHttp {

	private String statusLine;
	private Map<String, String> headers;
	private String body;

	/**
	 * Esse é o construtor da classe ResponseHttp, que constroe o objeto http que
	 * representa a resposta http que será enviada para o cliente em formato
	 * completo.
	 * 
	 * @param String             statusLine - Representa o status da resposta da
	 *                           requisição.
	 * @param Map<String,String> headers - Representa os cabeçalhos da resposta
	 *                           http.
	 * @param JSONObject         body - Representa o corpo da resposta http.
	 */
	public ResponseHttp(String statusLine, Map<String, String> headers, String body) {

		this.statusLine = statusLine;
		this.headers = headers;
		this.body = body;
	}

	/**
	 * Esse é o construtor da classe ResponseHttp, que constroe o objeto http que
	 * representa a resposta http que será enviada para o cliente em formato sem
	 * corpo.
	 * 
	 * @param String             statusLine - Representa o status da resposta da
	 *                           requisição.
	 * @param Map<String,String> headers - Representa os cabeçalhos da resposta
	 *                           http.
	 */

	public ResponseHttp(String statusLine, Map<String, String> headers) {

		this.statusLine = statusLine;
		this.headers = headers;

	}

	/**
	 * Esse é o construtor vazio da classe ResponseHttp, que constroe o objeto da
	 * classe ResponseHttp vazio
	 */
	public ResponseHttp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Esse é o método, que retorna o status da resposta http
	 * 
	 * @return Status da requisição http
	 */
	public String getStatusLine() {
		return statusLine;
	}

	/**
	 * Esse é o método, que seta o status da resposta http
	 * 
	 * @param String statusLine - O status da resposta http
	 */
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	/**
	 * Esse é o método, que retorna os cabeçalhos da resposta http
	 * 
	 * @return Cabeçalhos da requisição http
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Esse é o método, que seta os cabeçalhos da resposta http
	 * 
	 * @param Map<String,String> headers - Os cabeçalhos da resposta http
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * Esse é o método, que retorna o corpo da resposta http
	 * 
	 * @return Corpo da requisição http
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Esse é o método, que seta o corpo da resposta http
	 * 
	 * @param String body - O corpo da resposta http
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Esse é o método, que formata os cabeçalhos da resposta no modelo padrão de
	 * cabeçalhos http
	 * 
	 * @return Os cabeçalhos da resposta http em string
	 */
	public String headersToString() {

		StringBuilder stringHeaders = new StringBuilder();
		for (Entry<String, String> header : headers.entrySet()) {

			stringHeaders.append(header.getKey() + ":" + " " + header.getValue() + "\r\n");

		}

		return stringHeaders.toString();

	}

	/**
	 * Esse é o método, que formata todo o objeto da resposta no modelo padrão de
	 * uma resposta http
	 * 
	 * @return O objeto formatado em uma resposta http padrão em formato String
	 */
	@Override
	public String toString() {

		if (body != null) {

			return this.statusLine + "\r\n" + this.headersToString() + "\r\n" + this.body;

		} else {

			return this.statusLine + "\r\n" + this.headersToString() + "\r\n";

		}

	}

}
