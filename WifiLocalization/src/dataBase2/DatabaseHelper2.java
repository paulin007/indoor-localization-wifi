package dataBase2;

import java.text.MessageFormat;
import java.util.ArrayList;

import model.NewRowDatabase;
import onlinePhase.Point;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {
	private static final String TAG = DatabaseHelper2.class.getName();
	private static final String DATABASE_NAME = "/mnt/sdcard/01122014/wifiLocation2.db";
	private static final int SCHEMA_VERSION = 1;
	private SQLiteDatabase db = null;
	private int count = 0;

	public DatabaseHelper2(Context context) {
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
				+ "{7} REAL,"
				+ "{8} REAL,"
				+ "{9} REAL,"
				+ "{10} REAL,"
				+ "{11} REAL,"
				+ "{12} REAL,"
				+ "{13} REAL,"
				+ "{14} REAL,"
				+ "{15} REAL,"
				+ "{16} REAL,"
				+ "{17} REAL,"
				+ "{18} REAL,"
				+ "{19} REAL,"
				+ "{20} REAL,"
				+ "{21} REAL,"
				+ "{22} REAL,"
				+ "{23} REAL,"
				+ "{24} REAL,"
				+ "{25} REAL,"
				+ "{26} REAL,"
				+ "{27} REAL,"
				+ "{28} REAL,"
				+ "{29} REAL,"
				+ "{30} REAL,"
				+ "{31} REAL,"
				+ "{32} REAL,"
				+ "{33} REAL,"
				+ "{34} REAL,"
				+ "{35} REAL,"
				+ "{36} REAL,"
				+ "{37} REAL,"
				+ "{38} REAL,"
				+ "{39} REAL,"
				+ "{40} REAL,"
				+ "{41} REAL,"
				+ "{42} REAL,"
				+ "{43} REAL,"
				+ "{44} REAL," + "{45} REAL);";

		db.execSQL(MessageFormat.format(sql, WifiTable2.TABLE_NAME,
				WifiTable2._ID, WifiTable2.X, WifiTable2.Y, WifiTable2.ID_RP,
				WifiTable2.SSID, WifiTable2.BSSID, WifiTable2.C98,
				WifiTable2.C96, WifiTable2.C94, WifiTable2.C92, WifiTable2.C90,
				WifiTable2.C88, WifiTable2.C86, WifiTable2.C84, WifiTable2.C82,
				WifiTable2.C80, WifiTable2.C78, WifiTable2.C76, WifiTable2.C74,
				WifiTable2.C72, WifiTable2.C70, WifiTable2.C68, WifiTable2.C66,
				WifiTable2.C64, WifiTable2.C62, WifiTable2.C60, WifiTable2.C58,
				WifiTable2.C56, WifiTable2.C54, WifiTable2.C52, WifiTable2.C50,
				WifiTable2.C48, WifiTable2.C46, WifiTable2.C44, WifiTable2.C42,
				WifiTable2.C40, WifiTable2.C38, WifiTable2.C36, WifiTable2.C34,
				WifiTable2.C32, WifiTable2.C30, WifiTable2.C28, WifiTable2.C26,
				WifiTable2.C24, WifiTable2.C22));

	}

	public void inserisciDatiWifi(float x, float y, int id_rp, String ssid,
			String bssid, double c98, double c96, double c94, double c92,
			double c90, double c88, double c86, double c84, double c82,
			double c80, double c78, double c76, double c74, double c72,
			double c70, double c68, double c66, double c64, double c62,
			double c60, double c58, double c56, double c54, double c52,
			double c50, double c48, double c46, double c44, double c42,
			double c40, double c38, double c36, double c34, double c32,
			double c30, double c28, double c26, double c24, double c22) {
		ContentValues v = new ContentValues();
		v.put(WifiTable2.X, x);
		v.put(WifiTable2.Y, y);
		v.put(WifiTable2.ID_RP, id_rp);
		v.put(WifiTable2.SSID, ssid);
		v.put(WifiTable2.BSSID, bssid);
		v.put(WifiTable2.C98, c98);
		v.put(WifiTable2.C96, c96);
		v.put(WifiTable2.C94, c94);
		v.put(WifiTable2.C92, c92);
		v.put(WifiTable2.C90, c90);
		v.put(WifiTable2.C88, c88);
		v.put(WifiTable2.C86, c86);
		v.put(WifiTable2.C84, c84);
		v.put(WifiTable2.C82, c82);
		v.put(WifiTable2.C80, c80);
		v.put(WifiTable2.C78, c78);
		v.put(WifiTable2.C76, c76);
		v.put(WifiTable2.C74, c74);
		v.put(WifiTable2.C72, c72);
		v.put(WifiTable2.C70, c70);
		v.put(WifiTable2.C68, c68);
		v.put(WifiTable2.C66, c66);
		v.put(WifiTable2.C64, c64);
		v.put(WifiTable2.C62, c62);
		v.put(WifiTable2.C60, c60);
		v.put(WifiTable2.C58, c58);
		v.put(WifiTable2.C56, c56);
		v.put(WifiTable2.C54, c54);
		v.put(WifiTable2.C52, c52);
		v.put(WifiTable2.C50, c50);
		v.put(WifiTable2.C48, c48);
		v.put(WifiTable2.C46, c46);
		v.put(WifiTable2.C44, c44);
		v.put(WifiTable2.C42, c42);
		v.put(WifiTable2.C40, c40);
		v.put(WifiTable2.C38, c38);
		v.put(WifiTable2.C36, c36);
		v.put(WifiTable2.C34, c34);
		v.put(WifiTable2.C32, c32);
		v.put(WifiTable2.C30, c30);
		v.put(WifiTable2.C28, c28);
		v.put(WifiTable2.C26, c26);
		v.put(WifiTable2.C24, c24);
		v.put(WifiTable2.C22, c22);

		db = this.getWritableDatabase();

		db.insert(WifiTable2.TABLE_NAME, null, v);
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
	private static final String TABLE_NAME = "wifiNew";

	// wifi Table Columns names
	private static final String KEY_ID = "id";

	private static final String X = "x";

	private static final String Y = "y";

	private static final String ID_RP = "rp";

	private static final String SSID = "ssid";

	private static final String BSSID = "bssid";

	String[] COLUMNS = new String[] { KEY_ID, X, Y, ID_RP, SSID, BSSID,
			"c98_97", "c96_95", "c94_93", "c92_91", "c90_89", "c88_87",
			"c86_85", "c84_83", "c82_81", "c80_79", "c78_77", "c76_75",
			"c74_73", "c72_71", "c70_69", "c68_67", "c66_65", "c64_63",
			"c62_61", "c60_59", "c58_57", "c56_55", "c54_53", "c52_51",
			"c50_49", "c48_47", "c46_45", "c44_43", "c42_41", "c40_39",
			"c38_37", "c36_35", "c34_33", "c32_31", "c30_29", "c28_27",
			"c26_25", "c24_23", "c22_21" };

	public int getNumberOfRow() {
		count = 0;
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

	public NewRowDatabase getRow2(int id) {

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
		// 4. build row object
		NewRowDatabase row2 = new NewRowDatabase();
		row2.setId(Integer.parseInt(cursor.getString(i++)));
		row2.setX(Float.parseFloat(cursor.getString(i++)));
		row2.setY(Float.parseFloat(cursor.getString(i++)));
		row2.setId_rp(Integer.parseInt(cursor.getString(i++)));
		row2.setSsid(cursor.getString(i++));
		row2.setBssid(cursor.getString(i++));
		row2.setC98(Double.parseDouble(cursor.getString(i++)));
		row2.setC96(Double.parseDouble(cursor.getString(i++)));
		row2.setC94(Double.parseDouble(cursor.getString(i++)));
		row2.setC92(Double.parseDouble(cursor.getString(i++)));
		row2.setC90(Double.parseDouble(cursor.getString(i++)));
		row2.setC88(Double.parseDouble(cursor.getString(i++)));
		row2.setC86(Double.parseDouble(cursor.getString(i++)));
		row2.setC84(Double.parseDouble(cursor.getString(i++)));
		row2.setC82(Double.parseDouble(cursor.getString(i++)));

		row2.setC80(Double.parseDouble(cursor.getString(i++)));
		row2.setC78(Double.parseDouble(cursor.getString(i++)));
		row2.setC76(Double.parseDouble(cursor.getString(i++)));
		row2.setC74(Double.parseDouble(cursor.getString(i++)));
		row2.setC72(Double.parseDouble(cursor.getString(i++)));

		row2.setC70(Double.parseDouble(cursor.getString(i++)));
		row2.setC68(Double.parseDouble(cursor.getString(i++)));
		row2.setC66(Double.parseDouble(cursor.getString(i++)));
		row2.setC64(Double.parseDouble(cursor.getString(i++)));
		row2.setC62(Double.parseDouble(cursor.getString(i++)));

		row2.setC60(Double.parseDouble(cursor.getString(i++)));
		row2.setC58(Double.parseDouble(cursor.getString(i++)));
		row2.setC56(Double.parseDouble(cursor.getString(i++)));
		row2.setC54(Double.parseDouble(cursor.getString(i++)));
		row2.setC52(Double.parseDouble(cursor.getString(i++)));

		row2.setC50(Double.parseDouble(cursor.getString(i++)));
		row2.setC48(Double.parseDouble(cursor.getString(i++)));
		row2.setC46(Double.parseDouble(cursor.getString(i++)));
		row2.setC44(Double.parseDouble(cursor.getString(i++)));
		row2.setC42(Double.parseDouble(cursor.getString(i++)));

		row2.setC40(Double.parseDouble(cursor.getString(i++)));
		row2.setC38(Double.parseDouble(cursor.getString(i++)));
		row2.setC36(Double.parseDouble(cursor.getString(i++)));
		row2.setC34(Double.parseDouble(cursor.getString(i++)));
		row2.setC32(Double.parseDouble(cursor.getString(i++)));

		row2.setC30(Double.parseDouble(cursor.getString(i++)));
		row2.setC28(Double.parseDouble(cursor.getString(i++)));
		row2.setC26(Double.parseDouble(cursor.getString(i++)));
		row2.setC24(Double.parseDouble(cursor.getString(i++)));
		row2.setC22(Double.parseDouble(cursor.getString(i++)));

		// Log.d("getRow2(" + id + ")", row2.toString());

		cursor.close();
		// 5. return Row
		return row2;
	}

	/**
	 * select rp from TABLE_NAME where bssid = currentMac order by the column
	 * which corrispond to the level
	 * 
	 * 
	 * @param column
	 * @param currentMac
	 * @param orderBy
	 * @return
	 */
	public ArrayList<Integer> query(String[] column, String currentMac,
			String orderBy) {

		ArrayList<Integer> id_rps = new ArrayList<Integer>();
		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		// 2. build query
		Cursor cursor = db.query(TABLE_NAME, // a. table
				column, // b. column names
				" bssid = ?", // c. selections
				new String[] { currentMac }, // d. selections args
				null, // e. group by
				null, // f. having
				orderBy + " DESC", // g. order by
				null); // h. limit

		if (cursor.moveToFirst()) {
			do {
				id_rps.add(Integer.parseInt(cursor.getString(0)));
			} while (cursor.moveToNext());
		}

		cursor.close();
		return id_rps;
	}

	public Point queryPunto(int idRP) {

		String[] columns = new String[] { X, Y };
		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		// 2. build query
		// Cursor cursor = db.query(distinct, table, columns, selection,
		// selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)

		Cursor cursor = db.query(true, TABLE_NAME, // a. table
				columns, // b. column names
				" rp = ?", // c. selections
				new String[] { String.valueOf(idRP) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		Point punto = new Point(Float.parseFloat(cursor.getString(0)),
				Float.parseFloat(cursor.getString(1)));
		cursor.close();
		return punto;
	}

	public double queryGetProbability(String[] column, int id_RP,
			String currentMac) {
		double result = 0;
		// Log.e(TAG,
		// "column : "+column[0]+" id_rp = "+id_RP+" currentMac : "+currentMac);

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		// 2. build query
		// Cursor cursor = db.query(distinct, table, columns, selection,
		// selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)

		Cursor cursor = db.query(TABLE_NAME, // a. table
				column, // b. column names
				"rp = ? AND bssid = ?", // c. selections
				new String[] { Integer.toString(id_RP), currentMac }, // d.
																		// selections
																		// args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null) {

			cursor.moveToFirst();

			if (cursor.getCount() != 0) {
				result = Double.parseDouble(cursor.getString(0));
			}

		}
		cursor.close();

		return result;
	}

}