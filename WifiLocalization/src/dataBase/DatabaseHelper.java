package dataBase;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import model.RowDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "/mnt/sdcard/wifiLocation.db";
	private static final int SCHEMA_VERSION = 1;
	private SQLiteDatabase db = null;
    
	private int id_rp = 20;
	private float currentX = -1;
	private float currentY = -1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "{2} REAL,"
				+ "{3} REAL,"
				+ "{4} INTEGER,"
				+ "{5} TEXT NOT NULL,"
				+ "{6} TEXT NOT NULL,"
				+ "{7} INTEGER,"
				+ "{8} INTEGER,"
				+ "{9} INTEGER," + "{10} INTEGER);";

		db.execSQL(MessageFormat.format(sql, WifiTable.TABLE_NAME,
				WifiTable._ID, WifiTable.X, WifiTable.Y,WifiTable.ID_RP, WifiTable.SSID,
				WifiTable.BSSID, WifiTable.Level, WifiTable.Frequency,
				WifiTable.Timestamp, WifiTable.Channel));
	}

	public void inserisciDatiWifi(float x, float y, String ssid, String bssid, int level,
			int frequency, Long timestamp, int channel) {
		      if(currentX!=x || currentY!=y){
		    	    id_rp++;
		    	    currentX = x;
		    	    currentY = y;
		      }
		      
		if (ssid.equalsIgnoreCase("eduroam")|| ssid.equalsIgnoreCase("UNIPV-WIFI")) {
			
			ContentValues v = new ContentValues();
			v.put(WifiTable.X, x);
			v.put(WifiTable.Y, y);
			v.put(WifiTable.ID_RP, id_rp);
			v.put(WifiTable.SSID, ssid);
			v.put(WifiTable.BSSID, bssid);
			v.put(WifiTable.Level, level);
			v.put(WifiTable.Frequency, frequency);
			v.put(WifiTable.Timestamp, timestamp);
			v.put(WifiTable.Channel, channel);

			db = this.getWritableDatabase();

			db.insert(WifiTable.TABLE_NAME, null, v);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	// ---------------------------------------------------------------------
	/**
	 * CRUD operations (create "add", read "get", update, delete) book + get all
	 * books + delete all books
	 */

	// wifi table name
	private static final String TABLE_NAME = "wifi";

	// wifi Table Columns names
	private static final String KEY_ID = "id";

	private static final String X = "x";

	private static final String Y = "y";
	
	private static final String ID_RP = "rp";

	private static final String SSID = "ssid";

	private static final String BSSID = "bssid";
	
	private static final String Level = "level";

	private static final String Frequency = "frequency";

	private static final String Timestamp = "timestamp";

	private static final String Channel = "channel";

	String[] COLUMNS = new String[] { KEY_ID, X, Y,ID_RP, SSID, BSSID,Level, Frequency,
			 Timestamp, Channel };

	public RowDatabase getRow(int id) {
		int i = 0;
		
		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		// 2. build query

		Cursor cursor = db.query(TABLE_NAME, // a. table
				COLUMNS, // b. column names
				" id = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit
		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		// 4. build book object
		RowDatabase row = new RowDatabase();
		row.setId(Integer.parseInt(cursor.getString(i++)));
		row.setX(Float.parseFloat(cursor.getString(i++)));
		row.setY(Float.parseFloat(cursor.getString(i++)));
		row.setId_rp(Integer.parseInt(cursor.getString(i++)));
		row.setSsid(cursor.getString(i++));
		row.setBssid(cursor.getString(i++));
		row.setLevel(Integer.parseInt(cursor.getString(i++)));
		row.setFrequency(Integer.parseInt(cursor.getString(i++)));
		row.setTimestamp(Long.parseLong(cursor.getString(i++)));
		row.setChannel(Integer.parseInt(cursor.getString(i++)));
		// Log.d("getRow(" + id + ")", row.toString());

		// 5. return Row
		cursor.close();
		return row;
	}

	public int getNumberOfRow() {
	 int count = 0;
		// 1. build the query
		String query = "SELECT * FROM " + TABLE_NAME;
		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. go over each row, build book and add it to list

		if (cursor.moveToFirst()) {
			do {
				count++;
			} while (cursor.moveToNext());
		}
		cursor.close();

		return count;
	}

}