package services;

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

	public static Map<String, ArrayList<Invoice>> getMapInvoices() {
		return mapInvoices;
	}

	public static void setMapInvoices(Map<String, ArrayList<Invoice>> mapInvoices) {
		InvoiceServices.mapInvoices = mapInvoices;
	}

	public static long getIdInvoice() {
		return idInvoice;
	}

	public static void setIdInvoice(long idInvoice) {
		InvoiceServices.idInvoice = idInvoice;
	}

	public static String addInvoice(String idClient) {

		if (containsClient(idClient)) {

			Invoice invoice = new Invoice(Long.toString(idInvoice), idClient, UserServices.getUser(idClient).getName(),
					Fares.FARE_1.getFare(), ConsumptionServices.valueConsumptionInPeriod(idClient),
					UserServices.getUser(idClient).getStatusConsumption());
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
			json.put("idInvoice", invoice.getId());
			json.put("idClient", invoice.getIdClient());
			json.put("issuanceDate", invoice.getIssuanceDate());
			json.put("expirationDate", invoice.getExpirationDate());
			json.put("fare", invoice.getFare());
			json.put("consumption", invoice.getConsumption());
			json.put("invoiceValue", invoice.getInvoiceValue());
			json.put("currentStatusClient", invoice.getCurrentStatus());

			return json;

		}

		return null;
	}

	public static JSONObject getInvoicesJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

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
