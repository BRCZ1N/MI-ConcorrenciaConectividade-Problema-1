package client;

import java.time.LocalDate;

public class Invoice {

	private LocalDate expirationDate;
	private double consumption;
	private long idMeasurer;
	private long idClient;
	private double totalInvoice;
	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
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
	public double getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(double totalInvoice) {
		this.totalInvoice = totalInvoice;
	}



}
