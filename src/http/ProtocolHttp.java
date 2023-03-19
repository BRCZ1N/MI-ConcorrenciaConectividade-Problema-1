package http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

import utilityclasses.HttpMethods;

public class ProtocolHttp {

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

	public static void sendMessage(OutputStream out, String response) throws IOException {

		BufferedOutputStream buffer = new BufferedOutputStream(out);
		
		buffer.write(response.getBytes("UTF-8"));
		buffer.flush();
	

	}

}
