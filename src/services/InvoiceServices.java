package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import resources.Invoice;
import utilityclasses.Fares;

/**
 * Esta � a classe ConsumptionServices, que representa os servi�os de fatura da
 * aplica��o
 */
public class InvoiceServices {

	private static Map<String, ArrayList<Invoice>> mapInvoices;
	private static long idInvoice = 0;

	/**
	 * Esse � o construtor da classe InvoiceServices, que atribui ao map que dever�
	 * possuir as faturas do cliente uma instancia
	 */
	public InvoiceServices() {

		mapInvoices = new HashMap<>();

	}

	/**
	 * Esse � o m�todo, que retorna o map de faturas dos clientes
	 * 
	 * @return Map das faturas dos clientes
	 */
	public static Map<String, ArrayList<Invoice>> getMapInvoices() {
		return mapInvoices;
	}

	/**
	 * Esse � o m�todo, que seta o map de faturas dos clientes
	 * 
	 * @param Map<String,ArrayList<Invoice>> mapInvoices Map de faturas dos clientes
	 */
	public static void setMapInvoices(Map<String, ArrayList<Invoice>> mapInvoices) {
		InvoiceServices.mapInvoices = mapInvoices;
	}

	/**
	 * Esse � o m�todo, que cria e adiciona uma fatura ao cliente.
	 * 
	 * @param String idClient Identificador do cliente
	 */
	public static Invoice addInvoice(String idClient) {

		if (containsClient(idClient)) {

			LocalDateTime currentDate = LocalDateTime.now();

			if (containsInvoiceDate(idClient)) {

				currentDate = currentDate.plusDays(1);

			}

			Invoice invoice = new Invoice(Long.toString(idInvoice), idClient, UserServices.getUser(idClient).getName(),
					currentDate, Fares.FARE_1.getFare(), ConsumptionServices.valueConsumptionInPeriod(idClient),
					UserServices.getUser(idClient).getStatusConsumption());
			ArrayList<Invoice> copyListInvoice = mapInvoices.get(idClient);
			copyListInvoice.add(invoice);
			mapInvoices.replace(idClient, copyListInvoice);
			idInvoice++;

			return invoice;

		}

		return null;

	}

	/**
	 * Esse � o m�todo, que retorna a fatura de um cliente em formato JSON
	 * 
	 * @param String idClient Identificador do cliente
	 */
	public static JSONObject getInvoiceJSON(String idInvoice) {

		Invoice invoice;

		if ((invoice = getInvoice(idInvoice)) != null) {

			JSONObject json = new JSONObject();
			json.put("idInvoice", invoice.getId());
			json.put("idClient", invoice.getIdClient());
			json.put("name", invoice.getNameClient());
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

	/**
	 * Esse � o m�todo, que retorna as faturas de um cliente em formato JSON
	 * 
	 * @param String idClient Identificador do cliente
	 */
	public static JSONObject getInvoicesJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

			json.put("invoices", mapInvoices.get(idClient));

			return json;

		}

		return null;

	}

	/**
	 * Esse � o m�todo, que retorna a fatura de um cliente
	 * 
	 * @param String idClient Identificador do cliente
	 */
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

	/**
	 * Esse � o m�todo, que verifica se um cliente existe
	 * 
	 * @param String idClient Identificador do cliente
	 * @return Retorna verdadeiro se existe e falso se n�o existe
	 */
	public static boolean containsClient(String idClient) {

		for (String id : mapInvoices.keySet()) {

			if (id.equals(idClient)) {

				return true;
			}

		}

		return false;

	}

	/**
	 * Esse � o m�todo, que verifica se a data de uma fatura j� existe
	 * 
	 * @param String idClient Identificador do cliente
	 * @return Retorna verdadeiro se existe e falso se n�o existe
	 */
	public static boolean containsInvoiceDate(String idClient) {

		for (Invoice invoice : mapInvoices.get(idClient)) {

			if (invoice.getIssuanceDate().equals(idClient)) {

				return true;
			}

		}

		return false;

	}

	/**
	 * Esse � o m�todo, que adiciona uma chave de cliente ao map de faturas dos
	 * clientes
	 * 
	 * @param String idClient Identificador do cliente
	 */
	public static void addSlotClientInvoices(String idClient) {

		mapInvoices.put(idClient, new ArrayList<Invoice>());

	}

	/**
	 * Esse � o m�todo, que adiciona uma chave de cliente ao map de consumo dos
	 * clientes
	 * 
	 * @param String idClient Identificador do cliente
	 * @return Retorna a data da ultima fatura ou a data inicial caso n�o existam faturas
	 */
	public static LocalDateTime getInitialDateInvoiceOrInitialDate(String idClient) {

		if (InvoiceServices.getMapInvoices().get(idClient).isEmpty()) {

			return LocalDateTime.MIN;

		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		ArrayList<Invoice> userInvoices = InvoiceServices.getMapInvoices().get(idClient);
		Invoice lastInvoice = userInvoices.get(userInvoices.size() - 1);
		LocalDateTime lastInvoiceDate = LocalDateTime.parse(lastInvoice.getIssuanceDate(), dateTimeFormatter);
		LocalDateTime newInvoiceDate = lastInvoiceDate.plusSeconds(10);

		return newInvoiceDate;

	}
	
	/**
	 * Esse � o m�todo, que deleta as faturas de um cliente
	 * 
	 * @param String idClient Identificador do cliente
	 */
	public static void deleteUserInvoices(String idClient) {

		if (containsClient(idClient)) {

			mapInvoices.remove(idClient);

		}

	}

}
