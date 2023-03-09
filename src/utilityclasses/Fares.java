package utilityclasses;

public enum Fares {

	TARIFA_1(0.5), TARIFA_2(1.0);

	private double fare;

	private Fares(double fare) {

		this.fare = fare;

	}

	public double getFare() {

		return this.fare;

	}

}
