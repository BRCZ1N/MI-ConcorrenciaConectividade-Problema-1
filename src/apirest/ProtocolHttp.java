package apirest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import org.json.JSONObject;

public class ProtocolHttp {

	public static RequestHttp readRequest(InputStream input) throws IOException {

		Queue<String> httpData = new LinkedList<String>();
		String reqLine;

		try (Scanner scanner = new Scanner(input)) {
			while(scanner.hasNextLine()) {

				httpData.add(scanner.nextLine());

			}
		}
		
		String responseHeaders[] = httpData.poll().split("\s");
		Map<String, String> mapHeaders = new HashMap<String, String>();
		while (!(reqLine = httpData.poll()).isEmpty()) {

			String[] header = reqLine.split(":\s");
			mapHeaders.put(header[0], header[1]);

		}

		StringBuilder bodyJson = new StringBuilder();
		String bodyLine;

		while ((bodyLine = httpData.poll()) != null) {

			bodyJson.append(bodyLine);

		}

		String body = bodyJson.toString().replaceAll("\\s+", "");

		System.out.println(responseHeaders[0]);
		System.out.println(responseHeaders[1]);
		System.out.println(responseHeaders[2]);
		for (Map.Entry<String, String> currentObject : mapHeaders.entrySet()) {

			System.out.println(currentObject.getKey());
			System.out.println(currentObject.getValue());

		}

		System.out.println(body);

		return new RequestHttp(responseHeaders[0], responseHeaders[1], responseHeaders[2], mapHeaders,
				new JSONObject(body));
	}

	public static void sendResponse() {

	}

}
