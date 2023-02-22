package allclasses;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientUser extends User implements Serializable {

	private static final long serialVersionUID = -2973765919995933879L;
	private ArrayList<Invoice> listInvoices;
	private String statusConsume;
	private double totalConsume;

	public ClientUser(String id, String name, String password, String statusConsume, ArrayList<Invoice> listInvoices) {

		super(id, name, password);

	}

	public ArrayList<Invoice> getListInvoices() {
		return listInvoices;
	}

	public void setListInvoices(ArrayList<Invoice> listInvoices) {
		this.listInvoices = listInvoices;
	}

	public String getStatusConsume() {
		return statusConsume;
	}

	public void setStatusConsume(String statusConsume) {
		this.statusConsume = statusConsume;
	}

	public double getTotalConsume() {
		return totalConsume;
	}

	public void setTotalConsume(double totalConsume) {
		this.totalConsume = totalConsume;
	}

	
}
