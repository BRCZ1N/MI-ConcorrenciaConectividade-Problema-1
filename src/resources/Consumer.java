package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Consumer extends User implements Serializable {

	private static final long serialVersionUID = -2973765919995933879L;
	private Map<String, Invoice> mapInvoices;
	private ArrayList<Consumption> listConsumption;
	private String statusConsume;
	private double consumptionAccumulated;

	public Consumer(String id, String name, String password, String statusConsume, ArrayList<Invoice> listInvoices) {

		super(id, name, password);

	}

	public Map<String, Invoice> getMapInvoices() {
		return mapInvoices;
	}

	public void setMapInvoices(Map<String, Invoice> mapInvoices) {
		this.mapInvoices = mapInvoices;
	}

	public ArrayList<Consumption> getListConsumption() {
		return listConsumption;
	}

	public void setListConsumption(ArrayList<Consumption> listConsumption) {
		this.listConsumption = listConsumption;
	}

	public String getStatusConsume() {
		return statusConsume;
	}

	public void setStatusConsume(String statusConsume) {
		this.statusConsume = statusConsume;
	}

	public double getConsumptionAccumulated() {
		return consumptionAccumulated;
	}

	public void setConsumptionAccumulated(double consumptionAccumulated) {
		this.consumptionAccumulated = consumptionAccumulated;
	}

}
