package model;

import onlinePhase.Point;

public class ForEstimationPosition {
	private Point punto;
	private double probability;
	
	public ForEstimationPosition(Point punto, double probability) {
		super();
		this.punto = punto;
		this.probability = probability;
	}

	public Point getPunto() {
		return punto;
	}

	public void setPunto(Point punto) {
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
