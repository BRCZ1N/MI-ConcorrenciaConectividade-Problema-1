package services;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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

	public static boolean deleteUser(String id) {

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

}
