package model;

/**
 * this class can contain every elements of the row 
 * of the old DataBase
 * 
 * @author paulintchonin
 *
 */

public class RowDatabase {
	
	private int id;
	
	private float x;

	private float y;
	
	private int id_rp;
	
	private String ssid;
	
	private String bssid;
	
	private int level;

	private int frequency ;

	private Long timestamp;

	private int channel ;

	public RowDatabase(int id, float x, float y,int id_rp,String ssid, String bssid, int level) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.id_rp= id_rp;
		this.ssid=ssid;
		this.bssid = bssid;
		this.level = level;
	}
	
	public RowDatabase (){
		
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getId_rp() {
		return id_rp;
	}
	
	public void setId_rp(int id_rp) {
		this.id_rp = id_rp;
	}

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public String getSsid() {
		return ssid;
	}
	
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	@Override
	public String toString() {
		
		return " x : "+ x+" y : "+y+" "+" id_rp : "+ id_rp+" ssid : "+ssid+" bssid : "+bssid+"  potenza : "+level;
	}

}
