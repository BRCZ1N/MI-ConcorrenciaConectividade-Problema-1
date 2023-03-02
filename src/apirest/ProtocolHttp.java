package apirest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ProtocolHttp {

	public static RequestHttp readRequest(Socket socket) throws IOException {

		BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String lineRequest;
		
		String responseHeaders[] = buffer.readLine().split("\s"); 
		
		Map<String,String> mapHeaders = new HashMap<String,String>(); 
		while (!(lineRequest = buffer.readLine()).isEmpty()) {

			String[] header = lineRequest.split(":\s");
			mapHeaders.put(header[0], header[1]);

		}
		String body = "Ok";
		
		return new RequestHttp(responseHeaders[0],responseHeaders[1],responseHeaders[2],mapHeaders,body);
	}

	public static void sendResponse() {

	}

}