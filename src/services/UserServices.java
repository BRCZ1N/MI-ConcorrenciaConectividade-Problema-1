package services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;
import resources.Consumer;
import resources.Invoice;
import resources.User;

public class UserServices {

	private static Map<String, User> usersMap;
	private static long id = 0;

	public UserServices() {

		usersMap = new HashMap<String, User>();

	}

	public static boolean addUser(User user) {

		if (getUser(user.getName()) == null) {

			user.setId(Long.toString(id));
			usersMap.put(Long.toString(id), user);
			id += 1;
			return true;

		}

		return false;

	}

	public static boolean deleteClient(String id) {

		if (getUser(id) != null) {

			usersMap.remove(id);
			return true;

		}

		return false;

	}

	private static User getUser(String id) {

		for (Entry<String, User> user : usersMap.entrySet()) {

			if (user.getKey().equals(id)) {

				return user.getValue();

			}

		}

		return null;

	}

	public static JSONObject getConsumptionAccumulated(String id) {

		Consumer client = (Consumer) getUser(id);
		JSONObject json = new JSONObject();
		if (client != null) {

			json.put("id", client.getId());
			json.put("consumptionAccumulated", client.getConsumptionAccumulated());

			return json;

		}

		return null;
	}

	public static JSONObject getAllHistoryOfConsumption(String id) {

		Consumer client = (Consumer) getUser(id);
		JSONObject json = new JSONObject();

		if (client != null) {

			json.put("id", client.getId());
			json.put("consumptionAccumulated", client.getConsumptionAccumulated());
			json.put("listConsumption", client.getListConsumption());

			return json;

		}

		return null;
	}

	public static JSONObject getListInvoices(String id) {

		Consumer client = (Consumer) getUser(id);
		JSONObject json = new JSONObject();

		if (client != null) {

			json.put("id", client.getId());
			json.put("mapInvoices", client.getMapInvoices());

			return json;

		}

		return null;

	}

	public static JSONObject getInvoice(String id) {

		Consumer client = (Consumer) getUser(id);
		JSONObject json = new JSONObject();

		for (Entry<String, Invoice> invoice : client.getMapInvoices().entrySet()) {

			if (invoice.getKey().equals(LocalDateTime.now().getMonth().toString())) {

				json.put("id", invoice.getValue().getId());
				json.put("name", client.getName());
				json.put("expirationDate", invoice.getValue().getExpirationDate());
				json.put("issuanceDate", invoice.getValue().getIssuanceDate());
				json.put("billingPeriod", invoice.getValue().getBillingPeriod());
				json.put("valueFare", invoice.getValue().getValueFare());
				json.put("totalConsumption", invoice.getValue().getTotalConsumption());
				json.put("totalInvoice", invoice.getValue().getTotalInvoice());

				return json;

			}

		}

		return null;

	}

//	public static Map<String, Consumer> getAllClients() {
//
//		Map<String, Consumer> mapClients = new HashMap<String, Consumer>();
//
//		for (Entry<String, User> user : usersMap.entrySet()) {
//
//			if (user instanceof Consumer) {
//
//				mapClients.put(user.getKey(), (Consumer) user.getValue());
//
//			}
//
//		}
//		return mapClients;
//
//	}

//	public static Map<String, Administrator> getAllAdmins() {
//
//		Map<String, Administrator> mapAdmins = new HashMap<String, Administrator>();
//
//		for (Entry<String, User> user : usersMap.entrySet()) {
//
//			if (user instanceof Administrator) {
//
//				mapAdmins.put(user.getKey(), (Administrator) user.getValue());
//
//			}
//
//		}
//		return mapAdmins;
//
//	}

//	public static boolean userAuthentication(String id, String password) {
//
//		for (Entry<String, User> user : usersMap.entrySet()) {
//
//			if (user.getKey().equals(id) && user.getValue().getPassword().equals(password)) {
//
//				return true;
//
//			}
//
//		}
//
//		return false;
//
//	}

}
