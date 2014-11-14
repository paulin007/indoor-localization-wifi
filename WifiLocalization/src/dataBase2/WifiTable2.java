package dataBase2;

import android.provider.BaseColumns;

public interface WifiTable2 extends BaseColumns {
	String TABLE_NAME = "wifiNew";
	
	
	
	String X = "x";

	String Y = "y";
	
	String ID_RP = "rp";

	String BSSID = "bssid";



	String Media = "media";

	String SD = "sd";


	String[] COLUMNS = new String[] { _ID, X, Y,ID_RP, BSSID, Media, SD };
}
