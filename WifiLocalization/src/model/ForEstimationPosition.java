package model;

import onlinePhase.Punto;

public class ForEstimationPosition {
	private Punto punto;
	private double probability;
	
	public ForEstimationPosition(Punto punto, double probability) {
		super();
		this.punto = punto;
		this.probability = probability;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}
	
	@Override
	public String toString() {
		
		return punto.toString()+"  probability = "+probability;
	}
	
	

}
