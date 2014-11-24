package model;

public class Column {
	
	private String interval;
	private double probability;
	public Column(String interval) {
		super();
		this.interval = interval;
		
	}
	
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}

}
