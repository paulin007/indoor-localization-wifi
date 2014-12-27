package onlinePhase;

public class Rssi {
	
	private String mac;
	
	private int level;

	public Rssi(String mac, int level) {
		super();
		this.mac = mac;
		this.level = level;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return mac+"  "+level;
	}
	
}
