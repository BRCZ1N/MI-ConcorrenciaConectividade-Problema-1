package model;

import java.time.LocalDateTime;

public class Consumption {

	private LocalDateTime date;
	private double amount;
	private final String unitMeasurement = "Kwh";

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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
