package http;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import utilityclasses.HttpMethods;

public class RequestHttp {

	private HttpMethods method;
	private String path;
	private String versionHttp;
	private Map<String, String> headers;
	private JSONObject body;

	public RequestHttp(HttpMethods method, String path, String versionHttp, Map<String, String> headers,
			JSONObject body) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;
		this.body = body;
	}

	public RequestHttp(HttpMethods method, String path, String versionHttp, Map<String, String> headers) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;

	}

	public RequestHttp(HttpMethods method, String path, String versionHttp) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;

	}

	public RequestHttp() {

	}

	public HttpMethods getMethod() {
		return method;
	}

	public void setMethod(HttpMethods method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersionHttp() {
		return versionHttp;
	}

	public void setVersionHttp(String versionHttp) {
		this.versionHttp = versionHttp;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public JSONObject getBody() {
		return body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
	}

	public String headersToString() {

		StringBuilder stringHeaders = new StringBuilder();
		for (Entry<String, String> header : headers.entrySet()) {

			stringHeaders.append(header.getKey() + ":" + header.getValue() + "\r\n");

		}

		return stringHeaders.toString();

	}

	@Override
	public String toString() {

		if (body != null) {

			return this.method +" "+ this.path +" "+ this.versionHttp + "\r\n" + this.headersToString() + "\r\n" + this.body;

		} else {

			return this.method + this.path + this.versionHttp + "\r\n" + this.headersToString() + "\r\n";

		}

	}

}
