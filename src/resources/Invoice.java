package resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utilityclasses.StatusConsumeEnum;

public class Invoice {

	private String id;
	private String idClient;
	private String nameClient;
	private String issuanceDate;
	private String expirationDate;
	private double fare;
	private double consumption;
	private double invoiceValue;
	private StatusConsumeEnum currentStatus;

	public Invoice(String id, String idClient, String nameClient, LocalDateTime issuanceDate, double fare,
			double consumption, StatusConsumeEnum currentStatus) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		this.id = id;
		this.idClient = idClient;
		this.nameClient = nameClient;
		this.issuanceDate = issuanceDate.format(formatter);
		this.expirationDate = issuanceDate.plusDays(15).format(formatter);
		this.fare = fare;
		this.consumption = consumption;
		this.invoiceValue = consumption * fare;
		this.currentStatus = currentStatus;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public double getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public StatusConsumeEnum getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(StatusConsumeEnum currentStatus) {
		this.currentStatus = currentStatus;
	}

}
