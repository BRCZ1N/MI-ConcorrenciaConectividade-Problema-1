package resources;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consumption {

	private String dateTime;
	private double amount;
	private final String unitMeasurement = "Kwh";

	public Consumption(double amount) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.dateTime = currentDateTime.format(formatter);
		this.amount = amount;
		
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

	public String getUnitMeasurement() {
		return unitMeasurement;
	}

}
