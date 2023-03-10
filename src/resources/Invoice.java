package resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice {

	private String id;
	private String idClient;
	private String issuanceDate;
	private String expirationDate;
	private double fare;
	private double consumption;
	private double invoiceValue;

	public Invoice(String id, String idClient, double fare, double consumption) {

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.id = id;
		this.idClient = idClient;
		this.issuanceDate = currentDate.format(formatter);
		this.expirationDate = currentDate.plusDays(15).format(formatter);
		this.fare = fare;
		this.consumption = consumption;
		this.invoiceValue = consumption * fare;

	
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

}
