package services;

import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;
import resources.Invoice;

public class InvoiceServices {

	private static Map<String, ArrayList<Invoice>> mapInvoices;
	private static long idInvoice = 0;

	public static boolean addInvoice(String id) {

		ArrayList<Invoice> listInvoices;

		if (getInvoices(id) != null) {

			listInvoices = getInvoices(id);
			listInvoices.add(new Invoice());
			mapInvoices.replace(id, listInvoices);
			idInvoice++;

			return true;

		}

		return false;

	}

	private static ArrayList<Invoice> getInvoices(String idClient) {

		if (mapInvoices.get(idClient) != null) {

			return mapInvoices.get(idClient);

		}

		return null;

	}

	public static JSONObject getClientInvoice(String idClient, String idInvoice) {

		if (getInvoices(idClient) != null) {

			for (Invoice invoice : getInvoices(idClient)) {

				if (invoice.getId().equals(idInvoice)) {

					JSONObject json = new JSONObject();
					json.put("idClient", idClient);
					json.put("idInvoice", idInvoice);
					json.put("invoice", invoice);
					return json;

				}

			}

		}

		return null;
	}

	public static JSONObject getClientInvoices(String id) {

		JSONObject json = new JSONObject();

		if (getInvoices(id) != null) {

			json.put("id", id);
			json.put("Invoices", mapInvoices.get(id));

			return json;

		}

		return null;

	}

}
