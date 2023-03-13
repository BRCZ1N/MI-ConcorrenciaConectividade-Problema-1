package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import resources.Invoice;
import utilityclasses.Fares;

public class InvoiceServices {

	private static Map<String, ArrayList<Invoice>> mapInvoices;
	private static long idInvoice = 0;

	public InvoiceServices() {

		mapInvoices = new HashMap<>();

	}

	public static String addInvoice(String idClient, LocalDate dateInitial, LocalDate dateFinal) {

		if (containsClient(idClient)) {

			Invoice invoice = new Invoice(Double.toString(idInvoice), idClient, Fares.FARE_1.getFare(),
					ConsumptionServices.valueConsumptionInPeriod(idClient, dateInitial, dateFinal));
			refreshInvoiceMap(idClient, invoice);
			idInvoice++;

			return invoice.getId();

		}
		
		return null;

	}

	private static void refreshInvoiceMap(String idClient, Invoice invoice) {

		ArrayList<Invoice> copyListInvoice = mapInvoices.get(idClient);
		copyListInvoice.add(invoice);
		mapInvoices.replace(idClient, copyListInvoice);

	}

	public static JSONObject getInvoiceJSON(String idInvoice) {

		Invoice invoice;

		if ((invoice = getInvoice(idInvoice)) != null) {

			JSONObject json = new JSONObject();
			json.put("idInvoice", idInvoice);
			json.put("invoice", invoice);
			return json;

		}

		return null;
	}

	public static JSONObject getInvoicesJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

			json.put("idClient", idClient);
			json.put("invoices", mapInvoices.get(idClient));

			return json;

		}

		return null;

	}

	public static Invoice getInvoice(String idInvoice) {

		for (Entry<String, ArrayList<Invoice>> objectMap : mapInvoices.entrySet()) {

			for (Invoice invoice : objectMap.getValue()) {

				if (invoice.getId().equals(idInvoice)) {

					return invoice;

				}

			}

		}

		return null;

	}

	public static boolean containsClient(String idClient) {

		for (String id : mapInvoices.keySet()) {

			if (id.equals(idClient)) {

				return true;
			}

		}

		return false;

	}

	public static void addSlotClientInvoices(String idClient) {

		mapInvoices.put(idClient, new ArrayList<Invoice>());

	}

}
