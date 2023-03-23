package resources;

/**
 * Esta � a classe Consumption, que � a representa��o do consumo do cliente
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public class Consumption {

	private String dateTime;
	private double amount;
	private final String unitMeasurement = "Kwh";

	/**
	 * Esse � o construtor da classe Consumption, que constroe o objeto que
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
	 * Esse � o m�todo, que retorna a data e a hora do consumo
	 * 
	 * @return Data e hora do consumo
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * Esse � o m�todo, que seta a data e a hora do consumo
	 * 
	 * @param String dateTime - Data e hora do consumo
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Esse � o m�todo, que retorna a quantidade do consumo
	 * 
	 * @return Quantidade do consumo
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Esse � o m�todo, que seta a quantidade do consumo
	 * 
	 * @param double amount - Quantidade do consumo
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Esse � o m�todo, que retorna a unidade de medida do consumo
	 * 
	 * @return Unidade de medida do consumo
	 */
	public String getUnitMeasurement() {
		return unitMeasurement;
	}

}
