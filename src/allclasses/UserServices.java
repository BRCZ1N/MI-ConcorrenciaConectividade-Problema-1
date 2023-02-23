package allclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

	public ClientUser getUser(String id) {

		for (Entry<String, ClientUser> user : usersMap.entrySet()) {

			if (user.getKey().equals(id)) {

				return user.getValue();

			}

		}

		return null;

	}

	public double getConsume(String id) {

		return getUser(id).getConsumptionAccumulated();

	}
	
	public ArrayList<Consumption> getConsumptionRange(String id, String dataInicial, String dataFinal){
		
		ArrayList<Consumption> consumption;
		
		for(Consumption csm:getUser(id).getListConsumption()) {
			
			if()
			
		}
		
	}

}
