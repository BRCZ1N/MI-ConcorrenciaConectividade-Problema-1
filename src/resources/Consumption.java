package resources;

/**
 * Esta é a classe Consumption, que é a representação do consumo do cliente
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class Consumption {

	private String dateTime;
	private double amount;
	private final String unitMeasurement = "Kwh";

	/**
	 * Esse é o construtor da classe Consumption, que constroe o objeto que
	 * representa um consumo de cliente
	 * 
	 * @param double amount - Quantidade de consumo
	 * @param String dateTime - Data e hora que o consumo foi gerado
	 */
	public Consumption(double amount, String dateTime) {

		this.dateTime = dateTime;
		this.amount = amount;
	}

	/**
	 * Esse é o método, que retorna a data e a hora do consumo
	 * 
	 * @return Data e hora do consumo
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * Esse é o método, que seta a data e a hora do consumo
	 * 
	 * @param String dateTime - Data e hora do consumo
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Esse é o método, que retorna a quantidade do consumo
	 * 
	 * @return Quantidade do consumo
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Esse é o método, que seta a quantidade do consumo
	 * 
	 * @param double amount - Quantidade do consumo
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Esse é o método, que retorna a unidade de medida do consumo
	 * 
	 * @return Unidade de medida do consumo
	 */
	public String getUnitMeasurement() {
		return unitMeasurement;
	}

}
