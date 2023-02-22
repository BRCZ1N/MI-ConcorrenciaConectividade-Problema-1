package allclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Messages {

	public static boolean sendMessage(Socket socket, Object message) {
		
		try {

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			output.writeObject(message);
			return true;

		} catch (IOException e) {

			e.getStackTrace();

		}

		return false;

	}

	public static Object receiveMessage(Socket socket) {

		Object message;
		
		try {

			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			message = input.readObject();
			return message;

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		return "Não possui mensagem";

	}

}
