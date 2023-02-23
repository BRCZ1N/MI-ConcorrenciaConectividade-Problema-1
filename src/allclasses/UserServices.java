package allclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import client.ClientUser;
import client.Consumption;

public class UserServices implements UserServicesInterfaces<ClientUser> {

	private Map<String, ClientUser> usersMap;
	private long id = 0;

	public UserServices() {

		usersMap = new HashMap<String, ClientUser>();

	}

	public boolean addClient(ClientUser user) {

		if (getUser(user.getName()) == null) {

			user.setId(Long.toString(id));
			usersMap.put(Long.toString(id), user);
			id += 1;
			return true;

		}

		return false;

	}

	public boolean deleteClient(String id) {

		if (getUser(id) != null) {

			usersMap.containsKey(id);
			return true;

		}

		return false;

	}

	public Map<String, ClientUser> getAllClients() {

		return usersMap;

	}

	private ClientUser getUser(String id) {

		for (Entry<String, ClientUser> user : usersMap.entrySet()) {

			if (user.getKey().equals(id)) {

				return user.getValue();

			}

		}

		return null;

	}

	public boolean userAuthentication(String id, String password) {

		for (Entry<String, ClientUser> user : usersMap.entrySet()) {

			if (user.getKey().equals(id) && user.getValue().getPassword().equals(password)) {

				return true;

			}

		}

		return false;

	}

	public double getConsumptionAccumulated(String id) {

		return getUser(id).getConsumptionAccumulated();

	}

//	public ArrayList<Consumption> getConsumptionRange(String id, String dataInicial, String dataFinal){
//		
//		ArrayList<Consumption> consumption;
//		
//		for(Consumption csm:getUser(id).getListConsumption()) {
//			
//			if()
//			
//		}
//		
//	}

	public ArrayList<Consumption> getAllHistoryOfConsumption(String id) {

		return getUser(id).getListConsumption();

	}

}
