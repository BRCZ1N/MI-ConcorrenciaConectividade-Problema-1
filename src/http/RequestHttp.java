package http;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import utilityclasses.HttpMethods;

/**
 * Esta � a classe RequestHttp, que representa a requisi��o http do cliente
 * conectado ao servidor.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class RequestHttp {

	private HttpMethods method;
	private String path;
	private String versionHttp;
	private Map<String, String> headers;
	private JSONObject body;

	/**
	 * Esse � o construtor da classe RequestHttp, que constroe o objeto http que
	 * representa a requisi��o do cliente em formato completo
	 * 
	 * @param HttpMethods        method - Representa o m�todo da requisi��o http.
	 * @param String             path - Armazena o caminho da requisi��o http.
	 * @param String             versionHttp - Representa a vers�o atual da
	 *                           requisi��o http.
	 * @param Map<String,String> headers - Representa os cabe�alhos da requisi��o
	 *                           http.
	 * @param JSONObject         body - Representa o corpo da requisi��o http.
	 */
	public RequestHttp(HttpMethods method, String path, String versionHttp, Map<String, String> headers,
			JSONObject body) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;
		this.body = body;
	}

	/**
	 * Esse � o construtor da classe RequestHttp, que constroe o objeto http que
	 * representa a requisi��o do cliente sem o corpo
	 * 
	 * @param HttpMethods        method - Representa o m�todo da requisi��o http.
	 * @param String             path - Armazena o caminho da requisi��o http.
	 * @param String             versionHttp - Representa a vers�o atual da
	 *                           requisi��o http.
	 * @param Map<String,String> headers - Representa os cabe�alhos da requisi��o
	 *                           http.
	 */
	public RequestHttp(HttpMethods method, String path, String versionHttp, Map<String, String> headers) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;

	}

	/**
	 * Esse � o construtor da classe RequestHttp, que constroe o objeto http que
	 * representa a requisi��o do cliente sem headers e sem corpo
	 * 
	 * @param HttpMethods method - Representa o m�todo da requisi��o http.
	 * @param String      path - Armazena o caminho da requisi��o http.
	 * @param String      versionHttp - Representa a vers�o atual da requisi��o
	 *                    http.
	 */
	public RequestHttp(HttpMethods method, String path, String versionHttp) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;

	}

	/**
	 * Esse � o construtor vazio da classe RequestHttp, que constroe o objeto da
	 * classe RequestHttp vazio
	 */
	public RequestHttp() {

	}

	/**
	 * Esse � o m�todo, que retorna o m�todo da requisi��o http
	 * 
	 * @return M�todo da requisi��o http
	 */
	public HttpMethods getMethod() {
		return method;
	}

	/**
	 * Esse � o m�todo, que seta o m�todo da requisi��o http
	 * 
	 * @param HttpMethods method - O m�todo da requisi��o http
	 */
	public void setMethod(HttpMethods method) {
		this.method = method;
	}

	/**
	 * Esse � o m�todo, que retorna o caminho da requisi��o http
	 * 
	 * @return O path da requisi��o http
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Esse � o m�todo, que seta o caminho da requisi��o http
	 * 
	 * @param String path - O path da requisi��o http
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Esse � o m�todo, que retorna a vers�o da requisi��o http
	 * 
	 * @return A vers�o da requisi��o http
	 */
	public String getVersionHttp() {
		return versionHttp;
	}

	/**
	 * Esse � o m�todo, que seta a vers�o da requisi��o http
	 * 
	 * @param String versionHttp - A vers�o da requisi��o http
	 */
	public void setVersionHttp(String versionHttp) {
		this.versionHttp = versionHttp;
	}

	/**
	 * Esse � o m�todo, que retorna os cabe�alhos da requisi��o http
	 * 
	 * @return Os cabe�alhos da requisi��o http
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Esse � o m�todo, que seta os cabe�alhos da requisi��o http
	 * 
	 * @param Map<String,String> headers - Os cabe�alhos da requisi��o http
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * Esse � o m�todo, que retorna o corpo da requisi��o http
	 * 
	 * @return O corpo da requisi��o http
	 */
	public JSONObject getBody() {
		return body;
	}

	/**
	 * Esse � o m�todo, que seta o corpo da requisi��o http
	 * 
	 * @param JSONObject body - O corpo da requisi��o http
	 */
	public void setBody(JSONObject body) {
		this.body = body;
	}

	/**
	 * Esse � o m�todo, que formata os cabe�alhos da requisi��o no modelo padr�o de
	 * cabe�alhos http
	 * 
	 * @return Os cabe�alhos da requisi��o http em string
	 */
	public String headersToString() {

		StringBuilder stringHeaders = new StringBuilder();
		for (Entry<String, String> header : headers.entrySet()) {

			stringHeaders.append(header.getKey() + ":" + " " + header.getValue() + "\r\n");

		}

		return stringHeaders.toString();

	}

	/**
	 * Esse � o m�todo, que formata todo o objeto da requisi��o no modelo padr�o de
	 * uma requisi��o http
	 * 
	 * @return O objeto formatado em uma requisi��o http padr�o em formato String
	 */
	@Override
	public String toString() {

		if (body != null) {

			return this.method + " " + this.path + " " + this.versionHttp + "\r\n" + this.headersToString() + "\r\n"
					+ this.body;

		} else {

			return this.method + " " + this.path + " " + this.versionHttp + "\r\n" + this.headersToString() + "\r\n";

		}

	}

}
