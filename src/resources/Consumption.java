package resources;

public class Consumption {

	private String dateTime;
	private double amount;
	private final String unitMeasurement = "Kwh";

	public Consumption(double amount,String dateTime) {
		
		this.dateTime = dateTime;
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
