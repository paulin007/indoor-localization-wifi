package dataBase;

import android.provider.BaseColumns;

public interface WifiTable extends BaseColumns {
	String TABLE_NAME = "wifi";
	
	String X = "x";

	String Y = "y";

	String BSSID = "bssid";

	String Frequency = "frequency";

	String Level = "level";

	String Timestamp = "timestamp";

	String Channel = "channel";

	String[] COLUMNS = new String[] { _ID, X, Y, BSSID, Frequency, Level,
			Timestamp, Channel };
}
