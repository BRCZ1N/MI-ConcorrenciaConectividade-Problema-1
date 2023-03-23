package http;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import utilityclasses.HttpMethods;

/**
 * Esta é a classe RequestHttp, que representa a requisição http do cliente
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
	 * Esse é o construtor da classe RequestHttp, que constroe o objeto http que
	 * representa a requisição do cliente em formato completo
	 * 
	 * @param HttpMethods        method - Representa o método da requisição http.
	 * @param String             path - Armazena o caminho da requisição http.
	 * @param String             versionHttp - Representa a versão atual da
	 *                           requisição http.
	 * @param Map<String,String> headers - Representa os cabeçalhos da requisição
	 *                           http.
	 * @param JSONObject         body - Representa o corpo da requisição http.
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
	 * Esse é o construtor da classe RequestHttp, que constroe o objeto http que
	 * representa a requisição do cliente sem o corpo
	 * 
	 * @param HttpMethods        method - Representa o método da requisição http.
	 * @param String             path - Armazena o caminho da requisição http.
	 * @param String             versionHttp - Representa a versão atual da
	 *                           requisição http.
	 * @param Map<String,String> headers - Representa os cabeçalhos da requisição
	 *                           http.
	 */
	public RequestHttp(HttpMethods method, String path, String versionHttp, Map<String, String> headers) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;

	}

	/**
	 * Esse é o construtor da classe RequestHttp, que constroe o objeto http que
	 * representa a requisição do cliente sem headers e sem corpo
	 * 
	 * @param HttpMethods method - Representa o método da requisição http.
	 * @param String      path - Armazena o caminho da requisição http.
	 * @param String      versionHttp - Representa a versão atual da requisição
	 *                    http.
	 */
	public RequestHttp(HttpMethods method, String path, String versionHttp) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;

	}

	/**
	 * Esse é o construtor vazio da classe RequestHttp, que constroe o objeto da
	 * classe RequestHttp vazio
	 */
	public RequestHttp() {

	}

	/**
	 * Esse é o método, que retorna o método da requisição http
	 * 
	 * @return Método da requisição http
	 */
	public HttpMethods getMethod() {
		return method;
	}

	/**
	 * Esse é o método, que seta o método da requisição http
	 * 
	 * @param HttpMethods method - O método da requisição http
	 */
	public void setMethod(HttpMethods method) {
		this.method = method;
	}

	/**
	 * Esse é o método, que retorna o caminho da requisição http
	 * 
	 * @return O path da requisição http
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Esse é o método, que seta o caminho da requisição http
	 * 
	 * @param String path - O path da requisição http
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Esse é o método, que retorna a versão da requisição http
	 * 
	 * @return A versão da requisição http
	 */
	public String getVersionHttp() {
		return versionHttp;
	}

	/**
	 * Esse é o método, que seta a versão da requisição http
	 * 
	 * @param String versionHttp - A versão da requisição http
	 */
	public void setVersionHttp(String versionHttp) {
		this.versionHttp = versionHttp;
	}

	/**
	 * Esse é o método, que retorna os cabeçalhos da requisição http
	 * 
	 * @return Os cabeçalhos da requisição http
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Esse é o método, que seta os cabeçalhos da requisição http
	 * 
	 * @param Map<String,String> headers - Os cabeçalhos da requisição http
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * Esse é o método, que retorna o corpo da requisição http
	 * 
	 * @return O corpo da requisição http
	 */
	public JSONObject getBody() {
		return body;
	}

	/**
	 * Esse é o método, que seta o corpo da requisição http
	 * 
	 * @param JSONObject body - O corpo da requisição http
	 */
	public void setBody(JSONObject body) {
		this.body = body;
	}

	/**
	 * Esse é o método, que formata os cabeçalhos da requisição no modelo padrão de
	 * cabeçalhos http
	 * 
	 * @return Os cabeçalhos da requisição http em string
	 */
	public String headersToString() {

		StringBuilder stringHeaders = new StringBuilder();
		for (Entry<String, String> header : headers.entrySet()) {

			stringHeaders.append(header.getKey() + ":" + " " + header.getValue() + "\r\n");

		}

		return stringHeaders.toString();

	}

	/**
	 * Esse é o método, que formata todo o objeto da requisição no modelo padrão de
	 * uma requisição http
	 * 
	 * @return O objeto formatado em uma requisição http padrão em formato String
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
