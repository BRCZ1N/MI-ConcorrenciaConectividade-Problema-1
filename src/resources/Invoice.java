package resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utilityclasses.StatusConsumeEnum;

/**
 * Esta � a classe Invoice, que � a representa��o da fatura do cliente
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class Invoice {

	private String id;
	private String idClient;
	private String nameClient;
	private String issuanceDate;
	private String expirationDate;
	private double fare;
	private double consumption;
	private double invoiceValue;
	private String currentStatusConsumption;

	/**
	 * Esse � o construtor da classe Invoice, que constroe o objeto que representa
	 * uma fatura de um cliente
	 * 
	 * @param String        id - Identificador da fatura
	 * @param String        idClient - Identificador do cliente
	 * @param String        nameClient - Nome do cliente
	 * @param LocalDateTime issuanceDate - Data de inicio da fatura
	 * @param double        fare - Tarifa da faturas
	 * @param double        consumption - Consumo at� a data da fatura
	 * @param String        currentStatus - Status de consumo do cliente
	 */
	public Invoice(String id, String idClient, String nameClient, LocalDateTime issuanceDate, double fare,
			double consumption, String currentStatusConsumption) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		this.id = id;
		this.idClient = idClient;
		this.nameClient = nameClient;
		this.issuanceDate = issuanceDate.format(formatter);
		this.expirationDate = issuanceDate.plusDays(15).format(formatter);
		this.fare = fare;
		this.consumption = consumption;
		this.invoiceValue = consumption * fare;
		this.currentStatusConsumption = currentStatusConsumption;

	}

	/**
	 * Esse � o m�todo, que retorna o identificador da fatura
	 * 
	 * @return Identificador da fatura
	 */
	public String getId() {
		return id;
	}

	/**
	 * Esse � o m�todo, que seta o id da fatura
	 * 
	 * @param String id - Identificador da fatura
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Esse � o m�todo, que retorna o identificador do cliente da fatura
	 * 
	 * @return O identificador do cliente da fatura
	 */
	public String getIdClient() {
		return idClient;

	}

	/**
	 * Esse � o m�todo, que seta o identificador do cliente da fatura
	 * 
	 * @param String idClient - Identificador do cliente
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	/**
	 * Esse � o m�todo, que retorna o nome do cliente ao qual pertence a fatura
	 * 
	 * @return Nome do cliente que pertence a fatura
	 */
	public String getNameClient() {
		return nameClient;
	}

	/**
	 * Esse � o m�todo, que seta o nome do cliente ao qual pertence a fatura
	 * 
	 * @param String nameClient - Nome do cliente
	 */
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	/**
	 * Esse � o m�todo, retorna a data em que a fatura foi gerada
	 * 
	 * @return Data de gera��o da fatura
	 */
	public String getIssuanceDate() {
		return issuanceDate;
	}

	/**
	 * Esse � o m�todo, que seta a data em que a fatura foi gerada
	 * 
	 * @param String issuanceDate - Data de gera��o da fatura
	 */
	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	/**
	 * Esse � o m�todo, que retorna a data que expira a fatura do cliente
	 * 
	 * @return Data de expira��o da fatura
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Esse � o m�todo, que seta a data que expira a fatura do cliente
	 * 
	 * @param String issuanceDate - Data de gera��o da fatura
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Esse � o m�todo, que retorna a tarifa da fatura
	 * 
	 * @return Tarifa da fatura
	 */
	public double getFare() {
		return fare;
	}

	/**
	 * Esse � o m�todo, que seta a tarifa da fatura
	 * 
	 * @param double fare - Tarifa da fatura
	 */
	public void setFare(double fare) {
		this.fare = fare;
	}

	/**
	 * Esse � o m�todo, que retorna o consumo da fatura do cliente
	 * 
	 * @return Consumo da fatura
	 */
	public double getConsumption() {
		return consumption;
	}

	/**
	 * Esse � o m�todo, que seta o consumo da fatura
	 * 
	 * @param double consumption - Consumo da fatura
	 */
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	/**
	 * Esse � o m�todo, que retorna o valor da fatura do cliente
	 * 
	 * @return Valor da fatura
	 */
	public double getInvoiceValue() {
		return invoiceValue;
	}

	/**
	 * Esse � o m�todo, que seta o valor da fatura
	 * 
	 * @param double invoiceValue - Valor da fatura
	 */
	public void setInvoiceValue(double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	/**
	 * Esse � o m�todo, que retorna o status de consumo atual do cliente
	 * 
	 * @return Staus de consumo atual
	 */
	public String getCurrentStatusConsumption() {
		return currentStatusConsumption;
	}

	/**
	 * Esse � o m�todo, que seta o status de consumo atual do cliente
	 * 
	 * @param String currentStatus - Status de consumo atual
	 */
	public void setCurrentStatus(String currentStatusConsumption) {
		this.currentStatusConsumption = currentStatusConsumption;
	}

}
