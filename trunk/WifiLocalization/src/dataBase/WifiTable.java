package dataBase;

import android.provider.BaseColumns;

public interface WifiTable extends BaseColumns {
	String TABLE_NAME = "wifi";

	String _ID = "id";

	String X = "x";

	String Y = "y";

	String ID_RP = "rp";

	String SSID = "ssid";

	String BSSID = "bssid";

	String Level = "level";

	String Frequency = "frequency";

	String Timestamp = "timestamp";

	String Channel = "channel";

	String[] COLUMNS = new String[] { _ID, X, Y, ID_RP, SSID, BSSID, Level,
			Frequency, Timestamp, Channel };
}
