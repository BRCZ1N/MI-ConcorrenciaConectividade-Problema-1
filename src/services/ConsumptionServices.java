package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import resources.Consumption;
import resources.Invoice;

public class ConsumptionServices {

	private static Map<String, ArrayList<Consumption>> mapConsumptions;

	public ConsumptionServices() {

		mapConsumptions = new HashMap<>();

	}

	public static Map<String, ArrayList<Consumption>> getMapConsumptions() {
		return mapConsumptions;
	}

	public static void setMapConsumptions(Map<String, ArrayList<Consumption>> mapConsumptions) {
		ConsumptionServices.mapConsumptions = mapConsumptions;
	}

	public static void addConsumption(String idClient, Consumption consumption) {

		if (containsClient(idClient)) {

			UserServices.refreshStatusConsumption(idClient, consumption.getAmount());
			refreshConsumptionMap(idClient, consumption);

		}

	}

	public LocalDate getLastInvoiceDateOrInitialDate(String idClient) {

		if (InvoiceServices.getMapInvoices().get(idClient).isEmpty()) {

			return LocalDate.now();

		}

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ArrayList<Invoice> userInvoices = InvoiceServices.getMapInvoices().get(idClient);
		Invoice invoice = userInvoices.get(userInvoices.size() - 1);
		LocalDate invoiceDate = LocalDate.parse(invoice.getIssuanceDate(), dateFormatter);

		return invoiceDate;

	}

	public static double valueConsumptionInPeriod(String idClient) {

		double consumptionTotal = 0;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime consumptionDate;
		LocalDateTime currentDate = LocalDateTime.now();

		for (Consumption consumption : mapConsumptions.get(idClient)) {

			consumptionDate = LocalDateTime.parse(consumption.getDateTime(), dateTimeFormatter);
			ArrayList<Invoice> userInvoices = InvoiceServices.getMapInvoices().get(idClient);
			Invoice invoice = userInvoices.get(userInvoices.size() - 1);
			LocalDate invoiceDate = LocalDate.parse(invoice.getIssuanceDate(), dateFormatter);

			if ((consumptionDate.toLocalDate().isAfter(invoiceDate)
					&& consumptionDate.toLocalDate().isBefore(currentDate.toLocalDate()))
					|| consumptionDate.toLocalDate().equals(currentDate.toLocalDate())) {

				consumptionTotal += consumption.getAmount();

			}

		}

	}

	return consumptionTotal;

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

	public static double averageConsumptions(String idClient) {

		double average = 0;
		double totalConsumptionClient = 0;
		int amountMeasurements = 0;

		if (containsClient(idClient)) {

			for (Consumption consumption : mapConsumptions.get(idClient)) {

				totalConsumptionClient += consumption.getAmount();

			}
			amountMeasurements = mapConsumptions.get(idClient).size();

			average = totalConsumptionClient / amountMeasurements;

		}

		return average;

	}

}
