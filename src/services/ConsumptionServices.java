package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import resources.Consumption;

public class ConsumptionServices {

	private static Map<String, ArrayList<Consumption>> mapConsumptions;

	public ConsumptionServices() {

		mapConsumptions = new HashMap<>();

	}

	public static void addConsumption(String idClient, Consumption consumption) {

		if (containsClient(idClient)) {

			refreshConsumptionMap(idClient, consumption);

		}

	}
	
//	public static JSONObject currentStatusConsumption(String idClient) {
//		
//		JSONObject json = new JSONObject();
//
//		if (containsClient(idClient)) {
//
//			json.put("idClient", idClient);
//			
//			double currentConsumption = 0;
//			
//			for(Consumption consumption:mapConsumptions.get(idClient)) {
//				
//				 currentConsumption += consumption.getAmount();
//				
//			}
//			
//			
//
//			return json;
//
//		}
//
//		return null;
//		
//	}
	

	public static double valueConsumptionInPeriod(String idClient, LocalDate dateInitial, LocalDate dateFinal) {

		double consumptionTotal = 0;

		for (Map.Entry<String, ArrayList<Consumption>> consumptions : mapConsumptions.entrySet()) {

			for (Consumption consumption : consumptions.getValue()) {

				LocalDate dateConsumption = LocalDate.parse(consumption.getDateTime(),
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));

				if (dateConsumption.isAfter(dateInitial) && dateConsumption.isBefore(dateFinal)) {

					consumptionTotal = consumption.getAmount();
				}

			}

		}

		return consumptionTotal;

	}

	public static double testAddConsumoClient(String id) {

		double consumoTotal = 0;

		for (Entry<String, ArrayList<Consumption>> user : mapConsumptions.entrySet()) {

			if (user.getKey().equals(id)) {

				for (Consumption consumo : user.getValue()) {

					consumoTotal += consumo.getAmount();

				}

			}

		}

		return consumoTotal;

	}

	private static void refreshConsumptionMap(String idClient, Consumption consumption) {

		ArrayList<Consumption> copyListConsumption = mapConsumptions.get(idClient);
		copyListConsumption.add(consumption);
		mapConsumptions.replace(idClient, copyListConsumption);

	}

	public static JSONObject getConsumptionsJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

			json.put("idClient", idClient);
			json.put("historic", mapConsumptions.get(idClient));

			return json;

		}

		return null;

	}

	public static boolean containsClient(String idClient) {

		for (String id : mapConsumptions.keySet()) {

			if (id.equals(idClient)) {

				return true;

			}

		}

		return false;

	}

	public static void addSlotClientConsumptions(String idClient) {

		mapConsumptions.put(idClient, new ArrayList<Consumption>());

	}

}
