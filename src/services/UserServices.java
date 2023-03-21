package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import resources.User;
import utilityclasses.StatusConsumeEnum;

public class UserServices {

	private static Map<String, User> mapUsers;
	private static long id = 0;

	public UserServices() {

		mapUsers = new HashMap<String, User>();

	}

	public static void generateUsersTest() {

		UserServices.addUser(new User("Usuario1", "Test1", StatusConsumeEnum.NORMAL));// 0 - ID
		UserServices.addUser(new User("Usuario2", "Test2", StatusConsumeEnum.NORMAL));// 1 - ID
		UserServices.addUser(new User("Usuario3", "Test3", StatusConsumeEnum.NORMAL));// 2 - ID
		UserServices.addUser(new User("Usuario4", "Test4", StatusConsumeEnum.NORMAL));// 3 - ID
		UserServices.addUser(new User("Usuario5", "Test5", StatusConsumeEnum.NORMAL));// 4 - ID
		UserServices.addUser(new User("Usuario6", "Test6", StatusConsumeEnum.NORMAL));// 5 - ID
		UserServices.addUser(new User("Usuario7", "Test7", StatusConsumeEnum.NORMAL));// 6 - ID

	}

	public static void addUser(User user) {

		if (getUserPerName(user.getName()) == null) {

			user.setId(Long.toString(id));
			mapUsers.put(Long.toString(id), user);
			id += 1;
			ConsumptionServices.addSlotClientConsumptions(user.getId());
			InvoiceServices.addSlotClientInvoices(user.getId());

		}

	}

	public static void deleteUser(String id) {

		if (getUser(id) != null) {

			mapUsers.remove(id);

		}

	}

	public static User getUser(String id) {

		for (Entry<String, User> user : mapUsers.entrySet()) {

			if (user.getKey().equals(id)) {

				return user.getValue();

			}

		}

		return null;

	}

	private static User getUserPerName(String name) {

		for (Entry<String, User> user : mapUsers.entrySet()) {

			if (user.getValue().getName().equals(name)) {

				return user.getValue();

			}

		}

		return null;

	}

	public static byte[] authenticateClient(String idClient, String password) {

		for (Map.Entry<String, User> user : mapUsers.entrySet()) {

			if (user.getValue().getId().trim().equals(idClient.trim())
					&& user.getValue().getPassword().trim().equals(password.trim())) {

				return "authenticate".getBytes();

			}

		}

		return "denied authenticate".getBytes();

	}

	public static void editUser(User user) {

		if (getUser(user.getId()) == null) {

			mapUsers.replace(user.getId(), user);

		}

	}

	public static JSONObject getStatusConsumptionJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

			json.put("idClient", idClient);
			json.put("statusConsumption", mapUsers.get(idClient).getStatusConsumption().getTypeConsume());

			return json;

		}

		return null;

	}

	public static void refreshStatusConsumption(String idClient, double currentConsumption) {

		User user = getUser(idClient);

		if (currentConsumption > ConsumptionServices.averageConsumptions(idClient) + 200) {

			user.setStatusConsumption(StatusConsumeEnum.HIGH);
			editUser(user);

		} else {

			user.setStatusConsumption(StatusConsumeEnum.NORMAL);
			editUser(user);

		}

	}

	public static boolean containsClient(String idClient) {

		for (String id : mapUsers.keySet()) {

			if (id.equals(idClient)) {

				return true;
			}

		}

		return false;

	}

	public static boolean authClient(String idClient, String passwordClient) {

		for (Entry<String, User> client : mapUsers.entrySet()) {

			if (client.getValue().getId().equals(idClient) && client.getValue().getPassword().equals(passwordClient)) {

				return true;
			}

		}

		return false;

	}

}
