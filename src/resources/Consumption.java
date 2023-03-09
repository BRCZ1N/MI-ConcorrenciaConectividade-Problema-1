package resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consumption {

	private String dateTime;
	private double amount;
	private final String unitMeasurement = "Kwh";
	private String idClient;
	private String idMeasurer;

	public Consumption(double amount, String idClient, String idMeasurer) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.dateTime = currentDateTime.format(formatter);
		this.amount = amount;
		this.idClient = idClient;
		this.idMeasurer = idMeasurer;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getIdMeasurer() {
		return idMeasurer;
	}

	public void setIdMeasurer(String idMeasurer) {
		this.idMeasurer = idMeasurer;
	}

	public String getUnitMeasurement() {
		return unitMeasurement;
	}

}
