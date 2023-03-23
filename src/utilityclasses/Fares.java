package utilityclasses;

/**
 * Esta é a enumeração Fares, que possui as constantes de fatura da aplicação
 */
public enum Fares {

	FARE_1(0.5), FARE_2(1.0);

	private double fare;

	/**
	 * Esse é o construtor da enumeração Fares, que constroe o objeto que representa
	 * as possiveis faturas
	 * 
	 * @param double fare
	 */
	private Fares(double fare) {

		this.fare = fare;

	}
	/**
	 * Esse é o método, que retorna a tarifa do consumo
	 * 
	 * @return Tarifa do consumo
	 */
	public double getFare() {

		return this.fare;

	}

}
