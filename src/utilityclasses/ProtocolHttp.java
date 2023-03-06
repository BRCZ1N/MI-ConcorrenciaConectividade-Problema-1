package utilityclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONObject;

public class ProtocolHttp {

	public static RequestHttp readRequest(InputStream input) throws IOException, InterruptedException {

		Queue<String> httpData = new LinkedList<String>();
		String reqLine;
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
		String responseHeaders[] = null;
		Map<String, String> mapHeaders = null;
		String body = null;
		int character;
		StringBuilder str = new StringBuilder();

		while ((character = buffer.read()) != -1) {
		    System.out.print((char) character);
		}
		
		while ((character = buffer.read()) != -1) {

			if (character == '\n' || character == '\r') {

				str.append((char)character);
				httpData.add(str.toString());
				System.out.print(str.toString());
				str = new StringBuilder();

			}else {
				
				str.append((char)character);
				
			}

		}
		
		if(httpData.isEmpty()) {
			
			return null;
			
		}

		responseHeaders = httpData.poll().split("\s");
		mapHeaders = new HashMap<String, String>();
		while (!(reqLine = httpData.poll()).isEmpty()) {

			String[] header = reqLine.split(":\s");
			mapHeaders.put(header[0], header[1]);

		}

		StringBuilder bodyJson = new StringBuilder();
		String bodyLine;

		while ((bodyLine = httpData.poll()) != null) {

			bodyJson.append(bodyLine);

		}

		return new RequestHttp(responseHeaders[0], responseHeaders[1], responseHeaders[2], mapHeaders,
				new JSONObject(body));


	}

	public static void sendResponse() {

	}

}

