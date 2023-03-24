package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import resources.Consumption;

/**
 * Esta é a classe ConsumptionServices, que representa os serviços de consumo da
 * aplicação
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class ConsumptionServices {

	private static Map<String, ArrayList<Consumption>> mapConsumptions;

	/**
	 * Esse é o construtor da classe ConsumptionServices, que atribui ao map que
	 * deverá possuir os consumos do cliente uma instancia
	 */
	public ConsumptionServices() {

		mapConsumptions = new HashMap<>();

	}

	/**
	 * Esse é o método, que retorna o map de consumos dos clientes
	 * 
	 * @return Map do consumos dos clientes
	 */
	public static Map<String, ArrayList<Consumption>> getMapConsumptions() {
		return mapConsumptions;
	}

	/**
	 * Esse é o método, que seta o map de consumos dos clientes
	 * 
	 * @param mapConsumptions - Map de consumos do cliente
	 */
	public static void setMapConsumptions(Map<String, ArrayList<Consumption>> mapConsumptions) {
		ConsumptionServices.mapConsumptions = mapConsumptions;
	}

	/**
	 * Esse é o método, que adiciona um consumo ao cliente e que chama o metodo de
	 * mudança de status de consumo.
	 * 
	 * @param idClient    - Identificador do cliente
	 * @param consumption - Consumo do cliente
	 */
	public static void addConsumption(String idClient, Consumption consumption) {

		if (containsClient(idClient)) {

			UserServices.refreshStatusConsumption(idClient, consumption.getAmount());
			ArrayList<Consumption> copyListConsumption = mapConsumptions.get(idClient);
			copyListConsumption.add(consumption);
			mapConsumptions.replace(idClient, mapConsumptions.get(idClient));

		}

	}

	/**
	 * Esse é o método, que retorna o consumo total do cliente dentro de um periodo
	 * para geração de faturas.
	 * 
	 * @param idClient - Identificador do cliente
	 * @return Consumo do cliente em um periodo
	 */
	public static double valueConsumptionInPeriod(String idClient) {

		double consumptionTotal = 0;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime consumptionDate;
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime invoiceDateTime = InvoiceServices.getInitialDateInvoiceOrInitialDate(idClient);

		for (Consumption consumption : mapConsumptions.get(idClient)) {

			consumptionDate = LocalDateTime.parse(consumption.getDateTime(), dateTimeFormatter);

			if ((consumptionDate.isAfter(invoiceDateTime) && consumptionDate.isBefore(currentDateTime))
					|| consumptionDate.equals(currentDateTime)) {

				consumptionTotal += consumption.getAmount();

			}

		}

		return consumptionTotal;

	}

	/**
	 * Esse é o método, que retorna o consumo total acumulado
	 * 
	 * @param idClient - Identificador do cliente
	 * @return Consumo total do cliente
	 */
	public static double valueConsumptionTotal(String idClient) {

		double accumulatedConsumption = 0;

		for (Consumption consumption : mapConsumptions.get(idClient)) {

			accumulatedConsumption += consumption.getAmount();

		}

		return accumulatedConsumption;

	}

	/**
	 * Esse é o método, que retorna os consumos do cliente em formato JSON
	 * 
	 * @param idClient - Identificador do cliente
	 * @return Os consumos do cliente em formato JSON
	 */
	public static JSONObject getConsumptionsJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

			json.put("idClient", idClient);
			json.put("accumulatedConsumption", ConsumptionServices.valueConsumptionTotal(idClient));
			json.put("historic", mapConsumptions.get(idClient));

			return json;

		}

		return null;

	}

	/**
	 * Esse é o método, que verifica se um cliente existe
	 * 
	 * @param idClient - Identificador do cliente
	 * @return Retorna verdadeiro se existe e falso se não existe
	 */
	public static boolean containsClient(String idClient) {

		for (String id : mapConsumptions.keySet()) {

			if (id.equals(idClient)) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Esse é o método, que adiciona uma chave de cliente ao map de consumo dos
	 * clientes
	 * 
	 * @param idClient - Identificador do cliente
	 */
	public static void addSlotClientConsumptions(String idClient) {

		mapConsumptions.put(idClient, new ArrayList<Consumption>());

	}

	/**
	 * Esse é o método, que calcula a media de todos os consumos
	 * 
	 * @param idClient - Identificador do cliente
	 * @return Retorna a média de consumo do cliente
	 */
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

	/**
	 * Esse é o método, que deleta os consumos de um cliente
	 * 
	 * @param idClient - Identificador do cliente
	 */
	public static void deleteUserConsumption(String idClient) {

		if (containsClient(idClient)) {

			mapConsumptions.remove(idClient);

		}

	}

}
