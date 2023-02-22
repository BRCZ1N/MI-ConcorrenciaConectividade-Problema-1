package micc;

import java.time.LocalDate;

public class Invoice {

	private LocalDate expirationDate;
	private double amount;
	private double consume;
	private long idMeasurer;
	private long idClient;

	public LocalDate getExpirationDate() {
		
		return expirationDate;
		
	}

	public void setExpirationDate(LocalDate expirationDate) {
		
		this.expirationDate = expirationDate;
		
	}

	public double getAmount() {
		
		return amount;
		
	}

	public void setAmount(double amount) {
		
		this.amount = amount;
		
	}

	public double getConsume() {
		
		return consume;
		
	}

	public void setConsume(double consume) {
		
		this.consume = consume;
		
	}

	public long getIdMeasurer() {
		
		return idMeasurer;
		
	}

	public void setIdMeasurer(long idMeasurer) {
		
		this.idMeasurer = idMeasurer;
		
	}

	public long getIdClient() {
		
		return idClient;
		
	}

	public void setIdClient(long idClient) {
		
		this.idClient = idClient;
		
	}

}
