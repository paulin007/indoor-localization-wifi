package model;

import java.util.ArrayList;

/**
 * this class can contain every elements of the row 
 * of the new DataBase
 * @author paulintchonin
 *
 */

public class NewRowDatabase {
	
//	private static int STARTING = 90;
//	private static int ENDING = 22;
	

	private int id;
	
	private float x;

	private float y;

	private int id_rp;
	
	private String ssid;

	private String bssid;

	private double C90, C88, C86, C84, C82, C80, C78, C76, C74, C72, C70, C68,
			C66, C64, C62, C60, C58, C56, C54, C52, C50, C48, C46, C44, C42,
			C40, C38, C36, C34, C32, C30, C28, C26, C24, C22;
	
//	private ArrayList<Column> columns = new ArrayList<Column>();

	public NewRowDatabase() {

	}

	public NewRowDatabase(int id,float x, float y, int id_rp,String ssid, String bssid,
			double c90, double c88, double c86, double c84, double c82,
			double c80, double c78, double c76, double c74, double c72,
			double c70, double c68, double c66, double c64, double c62,
			double c60, double c58, double c56, double c54, double c52,
			double c50, double c48, double c46, double c44, double c42,
			double c40, double c38, double c36, double c34, double c32,
			double c30, double c28, double c26, double c24, double c22) {
		super();
		this.id=id;
		this.x = x;
		this.y = y;
		this.id_rp = id_rp;
		this.ssid = ssid;
		this.bssid = bssid;
		this.C90 = c90;
		this.C88 = c88;
		this.C86 = c86;
		this.C84 = c84;
		this.C82 = c82;
		this.C80 = c80;
		this.C78 = c78;
		this.C76 = c76;
		this.C74 = c74;
		this.C72 = c72;
		this.C70 = c70;
		this.C68 = c68;
		this.C66 = c66;
		this.C64 = c64;
		this.C62 = c62;
		this.C60 = c60;
		this.C58 = c58;
		this.C56 = c56;
		this.C54 = c54;
		this.C52 = c52;
		this.C50 = c50;
		this.C48 = c48;
		this.C46 = c46;
		this.C44 = c44;
		this.C42 = c42;
		this.C40 = c40;
		this.C38 = c38;
		this.C36 = c36;
		this.C34 = c34;
		this.C32 = c32;
		this.C30 = c30;
		this.C28 = c28;
		this.C26 = c26;
		this.C24 = c24;
		this.C22 = c22;
	}

	
//	private void createColumn(){
//		for (int i = STARTING; i > ENDING; i++) {
//			columns.add(new Column("c"+i+"_"+(i-1)));
//		}
//	}
//	
//	private void setProbabily(ArrayList<Double> probabilities){
//		for (int i = 0; i < probabilities.size(); i++) {
//			columns.get(i).setProbability(probabilities.get(i)) ;
//		}	
//	}
	
	
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
	
	public String getSsid() {
		return ssid;
	}
	
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public double getC90() {
		return C90;
	}

	public void setC90(double c90) {
		C90 = c90;
	}

	public double getC88() {
		return C88;
	}

	public void setC88(double c88) {
		C88 = c88;
	}

	public double getC86() {
		return C86;
	}

	public void setC86(double c86) {
		C86 = c86;
	}

	public double getC84() {
		return C84;
	}

	public void setC84(double c84) {
		C84 = c84;
	}

	public double getC82() {
		return C82;
	}

	public void setC82(double c82) {
		C82 = c82;
	}

	public double getC80() {
		return C80;
	}

	public void setC80(double c80) {
		C80 = c80;
	}

	public double getC78() {
		return C78;
	}

	public void setC78(double c78) {
		C78 = c78;
	}

	public double getC76() {
		return C76;
	}

	public void setC76(double c76) {
		C76 = c76;
	}

	public double getC74() {
		return C74;
	}

	public void setC74(double c74) {
		C74 = c74;
	}

	public double getC72() {
		return C72;
	}

	public void setC72(double c72) {
		C72 = c72;
	}

	public double getC70() {
		return C70;
	}

	public void setC70(double c70) {
		C70 = c70;
	}

	public double getC68() {
		return C68;
	}

	public void setC68(double c68) {
		C68 = c68;
	}

	public double getC66() {
		return C66;
	}

	public void setC66(double c66) {
		C66 = c66;
	}

	public double getC64() {
		return C64;
	}

	public void setC64(double c64) {
		C64 = c64;
	}

	public double getC62() {
		return C62;
	}

	public void setC62(double c62) {
		C62 = c62;
	}

	public double getC60() {
		return C60;
	}

	public void setC60(double c60) {
		C60 = c60;
	}

	public double getC58() {
		return C58;
	}

	public void setC58(double c58) {
		C58 = c58;
	}

	public double getC56() {
		return C56;
	}

	public void setC56(double c56) {
		C56 = c56;
	}

	public double getC54() {
		return C54;
	}

	public void setC54(double c54) {
		C54 = c54;
	}

	public double getC52() {
		return C52;
	}

	public void setC52(double c52) {
		C52 = c52;
	}

	public double getC50() {
		return C50;
	}

	public void setC50(double c50) {
		C50 = c50;
	}

	public double getC48() {
		return C48;
	}

	public void setC48(double c48) {
		C48 = c48;
	}

	public double getC46() {
		return C46;
	}

	public void setC46(double c46) {
		C46 = c46;
	}

	public double getC44() {
		return C44;
	}

	public void setC44(double c44) {
		C44 = c44;
	}

	public double getC42() {
		return C42;
	}

	public void setC42(double c42) {
		C42 = c42;
	}

	public double getC40() {
		return C40;
	}

	public void setC40(double c40) {
		C40 = c40;
	}

	public double getC38() {
		return C38;
	}

	public void setC38(double c38) {
		C38 = c38;
	}

	public double getC36() {
		return C36;
	}

	public void setC36(double c36) {
		C36 = c36;
	}

	public double getC34() {
		return C34;
	}

	public void setC34(double c34) {
		C34 = c34;
	}

	public double getC32() {
		return C32;
	}

	public void setC32(double c32) {
		C32 = c32;
	}

	public double getC30() {
		return C30;
	}

	public void setC30(double c30) {
		C30 = c30;
	}

	public double getC28() {
		return C28;
	}

	public void setC28(double c28) {
		C28 = c28;
	}

	public double getC26() {
		return C26;
	}

	public void setC26(double c26) {
		C26 = c26;
	}

	public double getC24() {
		return C24;
	}

	public void setC24(double c24) {
		C24 = c24;
	}

	public double getC22() {
		return C22;
	}

	public void setC22(double c22) {
		C22 = c22;
	}

	@Override
	public String toString() {

		return " x : " + x + " y : " + y + " " + "Id_RP " + id_rp + "bssid : "
				+ bssid;
	}

}
