package apirest;

import java.util.Map;

public class RequestHttp {

	private String method;
	private String path;
	private String versionHttp;
	private Map<String, String> headers;
	private String body;

	public RequestHttp(String method, String path, String versionHttp, Map<String, String> headers, String body) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;
		this.body = body;

	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
