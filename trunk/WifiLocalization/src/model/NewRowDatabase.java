package model;

public class NewRowDatabase {
	

	
	private float x;

	private float y;
	
	private int id_rp;

	private String bssid;

	private double media;
	
	private double varianza;

	
	public NewRowDatabase (){
		
	}
	
	
	public NewRowDatabase(float x, float y, int id_rp, String bssid, double media , double varianza) {
		super();
		
		this.x = x;
		this.y = y;
		this.id_rp = id_rp;
		this.bssid = bssid;
		this.media = media;
		this.varianza = varianza;
		
	}



	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setId_rp(int id_rp) {
		this.id_rp = id_rp;
	}
	
	public int getId_rp() {
		return id_rp;
	}
	
	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	
	public void setMedia(float media) {
		this.media = media;
	}
	
	public double getMedia() {
		return media;
	}
	
	public void setVarianza(float varianza) {
		this.varianza = varianza;
	}
	
	public double getVarianza() {
		return varianza;
	}
	

	
	
	@Override
	public String toString() {
		
		return " x : "+ x+" y : "+y+" "+"Id_RP "+id_rp+"bssid : "+bssid+"  media : "+media+" varianza :"+varianza;
	}

}
