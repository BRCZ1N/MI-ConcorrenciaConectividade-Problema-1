package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;
import resources.Consumption;

public class ConsumptionServices {

	private static Map<String, ArrayList<Consumption>> mapConsumptions;
	private static long idConsumption = 0;

	public static void addInvoice(String idClient, LocalDate dateInitial, LocalDate dateFinal) {

		if (containsClient(idClient)) {

			Consumption consumption = new Consumption(idConsumption);
			refreshInvoiceMap(idClient, consumption);
			idConsumption++;

		}

	}

	public static double consumptionInPeriod(String idClient, LocalDate dateInitial, LocalDate dateFinal) {

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

	private static void refreshInvoiceMap(String idClient, Consumption consumption) {

		ArrayList<Consumption> copyListInvoice = mapConsumptions.get(idClient);
		mapConsumptions.replace(idClient, copyListInvoice);

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

}
