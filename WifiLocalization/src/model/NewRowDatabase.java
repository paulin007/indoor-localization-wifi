package model;

import java.util.ArrayList;

/**
 * this class can contain every elements of the row of the new DataBase
 * 
 * @author paulintchonin
 * 
 */

public class NewRowDatabase {

	private int id;

	private float x;

	private float y;

	private int id_rp;

	private String ssid;

	private String bssid;

	private double c98_c97, c96_c95, c94_c93, c92_c91, c90_c89, c88_c87, c86_c85, c84_c83, c82_c81, c80_c79, c78_c77, c76_c75,
			c74_c73, c72_c71, c70_c69, c68_c67, c66_c65, c64_c63, c62_c61, c60_c59, c58_c57, c56_c55, c54_c53, c52_c51, c50_c49,
			c48_c47, c46_c45, c44_c43, c42_c41, c40_c39, c38_c37, c36_c35, c34_c33, c32_c31, c30_c29, c28_c27, c26_c25, c24_c23,
			c22_c21;

	// private ArrayList<Column> columns = new ArrayList<Column>();

	public NewRowDatabase() {
	}

	public NewRowDatabase(int id, float x, float y, int id_rp, String ssid,
			String bssid, double c98_c97, double c96_c95, double c94_c93, double c92_c91,
			double c90_c89, double c88_c87, double c86_c85, double c84_c83, double c82_c81,
			double c80_c79, double c78_c77, double c76_c75, double c74_c73, double c72_c71,
			double c70_c69, double c68_c67, double c66_c65, double c64_c63, double c62_c61,
			double c60_c59, double c58_c57, double c56_c55, double c54_c53, double c52_c51,
			double c50_c49, double c48_c47, double c46_c45, double c44_c43, double c42_c41,
			double c40_c39, double c38_c37, double c36_c35, double c34_c33, double c32_c31,
			double c30_c29, double c28_c27, double c26_c25, double c24_c23, double c22_c21) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.id_rp = id_rp;
		this.ssid = ssid;
		this.bssid = bssid;
		this.c98_c97 = c98_c97;
		this.c96_c95 = c96_c95;
		this.c94_c93 = c94_c93;
		this.c92_c91 = c92_c91;
		this.c90_c89 = c90_c89;
		this.c88_c87 = c88_c87;
		this.c86_c85 = c86_c85;
		this.c84_c83 = c84_c83;
		this.c82_c81 = c82_c81;
		this.c80_c79 = c80_c79;
		this.c78_c77 = c78_c77;
		this.c76_c75 = c76_c75;
		this.c74_c73 = c74_c73;
		this.c72_c71 = c72_c71;
		this.c70_c69 = c70_c69;
		this.c68_c67 = c68_c67;
		this.c66_c65 = c66_c65;
		this.c64_c63 = c64_c63;
		this.c62_c61 = c62_c61;
		this.c60_c59 = c60_c59;
		this.c58_c57 = c58_c57;
		this.c56_c55 = c56_c55;
		this.c54_c53 = c54_c53;
		this.c52_c51 = c52_c51;
		this.c50_c49 = c50_c49;
		this.c48_c47 = c48_c47;
		this.c46_c45 = c46_c45;
		this.c44_c43 = c44_c43;
		this.c42_c41 = c42_c41;
		this.c40_c39 = c40_c39;
		this.c38_c37 = c38_c37;
		this.c36_c35 = c36_c35;
		this.c34_c33 = c34_c33;
		this.c32_c31 = c32_c31;
		this.c30_c29 = c30_c29;
		this.c28_c27 = c28_c27;
		this.c26_c25 = c26_c25;
		this.c24_c23 = c24_c23;
		this.c22_c21 = c22_c21;
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

	public double getC98() {
		return c98_c97;
	}

	public void setC98(double c98) {
		c98_c97 = c98;
	}

	public double getC96() {
		return c96_c95;
	}

	public void setC96(double c96) {
		c96_c95 = c96;
	}

	public double getC94() {
		return c94_c93;
	}

	public void setC94(double c94) {
		c94_c93 = c94;
	}

	public double getC92() {
		return c92_c91;
	}

	public void setC92(double c92) {
		c92_c91 = c92;
	}

	public double getC90() {
		return c90_c89;
	}

	public void setC90(double c90) {
		c90_c89 = c90;
	}

	public double getC88() {
		return c88_c87;
	}

	public void setC88(double c88) {
		c88_c87 = c88;
	}

	public double getC86() {
		return c86_c85;
	}

	public void setC86(double c86) {
		c86_c85 = c86;
	}

	public double getC84() {
		return c84_c83;
	}

	public void setC84(double c84) {
		c84_c83 = c84;
	}

	public double getC82() {
		return c82_c81;
	}

	public void setC82(double c82) {
		c82_c81 = c82;
	}

	public double getC80() {
		return c80_c79;
	}

	public void setC80(double c80) {
		c80_c79 = c80;
	}

	public double getC78() {
		return c78_c77;
	}

	public void setC78(double c78) {
		c78_c77 = c78;
	}

	public double getC76() {
		return c76_c75;
	}

	public void setC76(double c76) {
		c76_c75 = c76;
	}

	public double getC74() {
		return c74_c73;
	}

	public void setC74(double c74) {
		c74_c73 = c74;
	}

	public double getC72() {
		return c72_c71;
	}

	public void setC72(double c72) {
		c72_c71 = c72;
	}

	public double getC70() {
		return c70_c69;
	}

	public void setC70(double c70) {
		c70_c69 = c70;
	}

	public double getC68() {
		return c68_c67;
	}

	public void setC68(double c68) {
		c68_c67 = c68;
	}

	public double getC66() {
		return c66_c65;
	}

	public void setC66(double c66) {
		c66_c65 = c66;
	}

	public double getC64() {
		return c64_c63;
	}

	public void setC64(double c64) {
		c64_c63 = c64;
	}

	public double getC62() {
		return c62_c61;
	}

	public void setC62(double c62) {
		c62_c61 = c62;
	}

	public double getC60() {
		return c60_c59;
	}

	public void setC60(double c60) {
		c60_c59 = c60;
	}

	public double getC58() {
		return c58_c57;
	}

	public void setC58(double c58) {
		c58_c57 = c58;
	}

	public double getC56() {
		return c56_c55;
	}

	public void setC56(double c56) {
		c56_c55 = c56;
	}

	public double getC54() {
		return c54_c53;
	}

	public void setC54(double c54) {
		c54_c53 = c54;
	}

	public double getC52() {
		return c52_c51;
	}

	public void setC52(double c52) {
		c52_c51 = c52;
	}

	public double getC50() {
		return c50_c49;
	}

	public void setC50(double c50) {
		c50_c49 = c50;
	}

	public double getC48() {
		return c48_c47;
	}

	public void setC48(double c48) {
		c48_c47 = c48;
	}

	public double getC46() {
		return c46_c45;
	}

	public void setC46(double c46) {
		c46_c45 = c46;
	}

	public double getC44() {
		return c44_c43;
	}

	public void setC44(double c44) {
		c44_c43 = c44;
	}

	public double getC42() {
		return c42_c41;
	}

	public void setC42(double c42) {
		c42_c41 = c42;
	}

	public double getC40() {
		return c40_c39;
	}

	public void setC40(double c40) {
		c40_c39 = c40;
	}

	public double getC38() {
		return c38_c37;
	}

	public void setC38(double c38) {
		c38_c37 = c38;
	}

	public double getC36() {
		return c36_c35;
	}

	public void setC36(double c36) {
		c36_c35 = c36;
	}

	public double getC34() {
		return c34_c33;
	}

	public void setC34(double c34) {
		c34_c33 = c34;
	}

	public double getC32() {
		return c32_c31;
	}

	public void setC32(double c32) {
		c32_c31 = c32;
	}

	public double getC30() {
		return c30_c29;
	}

	public void setC30(double c30) {
		c30_c29 = c30;
	}

	public double getC28() {
		return c28_c27;
	}

	public void setC28(double c28) {
		c28_c27 = c28;
	}

	public double getC26() {
		return c26_c25;
	}

	public void setC26(double c26) {
		c26_c25 = c26;
	}

	public double getC24() {
		return c24_c23;
	}

	public void setC24(double c24) {
		c24_c23 = c24;
	}

	public double getC22() {
		return c22_c21;
	}

	public void setC22(double c22) {
		c22_c21 = c22;
	}

	@Override
	public String toString() {

		return " x : " + x + " y : " + y + " " + "Id_RP " + id_rp + "bssid : "
				+ bssid;
	}

}
