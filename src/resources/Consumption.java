package resources;

import java.time.LocalDateTime;

public class Consumption {

	private LocalDateTime dateTime;
	private double amount;
	private final String unitMeasurement = "Kwh";

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
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
