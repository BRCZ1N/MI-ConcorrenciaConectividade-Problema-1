package utilityclasses;

/**
 * Esta � a enumera��o Fares, que possui as constantes de fatura da aplica��o
 */
public enum Fares {

	FARE_1(0.5), FARE_2(1.0);

	private double fare;

	/**
	 * Esse � o construtor da enumera��o Fares, que constroe o objeto que representa
	 * as possiveis faturas
	 * 
	 * @param double fare
	 */
	private Fares(double fare) {

		this.fare = fare;

	}
	/**
	 * Esse � o m�todo, que retorna a tarifa do consumo
	 * 
	 * @return Tarifa do consumo
	 */
	public double getFare() {

		return this.fare;

	}

}
