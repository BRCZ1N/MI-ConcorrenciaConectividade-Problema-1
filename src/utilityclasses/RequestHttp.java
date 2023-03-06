package utilityclasses;

import java.util.Map;

import org.json.JSONObject;

public class RequestHttp {

	private String method;
	private String path;
	private String versionHttp;
	private Map<String, String> headers;
	private JSONObject body;

	public RequestHttp(String method, String path, String versionHttp, Map<String, String> headers, JSONObject body) {

		this.method = method;
		this.path = path;
		this.versionHttp = versionHttp;
		this.headers = headers;
		this.body = body;

	}

	public RequestHttp() {
		
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

	public JSONObject getBody() {
		return body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
	}

}
