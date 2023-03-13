package utilityclasses;

import java.util.Map;
import java.util.Map.Entry;

public class ResponseHttp {

	private String statusLine;
	private Map<String, String> headers;
	private String body;

	public ResponseHttp(String statusLine, Map<String, String> headers, String body) {

		this.statusLine = statusLine;
		this.headers = headers;
		this.body = body;
	}

	public ResponseHttp(String statusLine, Map<String, String> headers) {

		this.statusLine = statusLine;
		this.headers = headers;

	}

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
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

	public String headersToString() {

		StringBuilder stringHeaders = new StringBuilder();
		for (Entry<String, String> header : headers.entrySet()) {

			stringHeaders.append(header.getKey()+":"+header.getValue() + "\r\n");

		}

		return stringHeaders.toString();

	}

	@Override
	public String toString() {

		return this.statusLine + this.headersToString() + "\r\n" + this.body;

	}

}
