package dataBase2;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.NewRowDatabase;
import model.RowDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper2 extends SQLiteOpenHelper {
	private static final String TAG="DatabaseHelper2";
	private static final String DATABASE_NAME = "/mnt/sdcard/wifiLocation2.db";
	private static final int SCHEMA_VERSION = 1;
	private SQLiteDatabase db = null;

	private int count = 0;
	
	int i =0;

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
				+ "{6} REAL,"
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
				+ "{40} REAL);";

		db.execSQL(MessageFormat.format(sql, WifiTable2.TABLE_NAME,
				WifiTable2._ID, WifiTable2.X, WifiTable2.Y, WifiTable2.ID_RP,
				WifiTable2.BSSID, WifiTable2.C90, WifiTable2.C88,
				WifiTable2.C86, WifiTable2.C84, WifiTable2.C82, WifiTable2.C80,
				WifiTable2.C78, WifiTable2.C76, WifiTable2.C74, WifiTable2.C72,
				WifiTable2.C70, WifiTable2.C68, WifiTable2.C66, WifiTable2.C64,
				WifiTable2.C62, WifiTable2.C60, WifiTable2.C58, WifiTable2.C56,
				WifiTable2.C54, WifiTable2.C52, WifiTable2.C50, WifiTable2.C48,
				WifiTable2.C46, WifiTable2.C44, WifiTable2.C42, WifiTable2.C40,
				WifiTable2.C38, WifiTable2.C36, WifiTable2.C34, WifiTable2.C32,
				WifiTable2.C30, WifiTable2.C28, WifiTable2.C26, WifiTable2.C24,
				WifiTable2.C22));

	}

	public void inserisciDatiWifi(float x, float y, int id_rp, String bssid,
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
		v.put(WifiTable2.BSSID, bssid);
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
	private static final String KEY_ID = "_id";

	private static final String X = "x";

	private static final String Y = "y";

	private static final String ID_RP = "rp";

	private static final String BSSID = "bssid";

	//TODO da eleminare 
	
	private static final String C90 = "c90_89";
	private static final String C88 = "c88_87";
	private static final String C86 = "c86_85";
	private static final String C84 = "c84_83";
	private static final String C82 = "c82_81";

	private static final String C80 = "c80_79";
	private static final String C78 = "c78_77";
	private static final String C76 = "c76_75";
	private static final String C74 = "c74_73";
	private static final String C72 = "c72_71";

	private static final String C70 = "c70_69";
	private static final String C68 = "c68_67";
	private static final String C66 = "c66_65";
	private static final String C64 = "c64_63";
	private static final String C62 = "c62_61";

	private static final String C60 = "c60_59";
	private static final String C58 = "c58_57";
	private static final String C56 = "c56_55";
	private static final String C54 = "c54_53";
	private static final String C52 = "c52_51";

	private static final String C50 = "c50_49";
	private static final String C48 = "c48_47";
	private static final String C46 = "c46_45";
	private static final String C44 = "c44_43";
	private static final String C42 = "c42_41";

	private static final String C40 = "c40_39";
	private static final String C38 = "c38_37";
	private static final String C36 = "c36_35";
	private static final String C34 = "c34_33";
	private static final String C32 = "c32_31";

	private static final String C30 = "c30_29";
	private static final String C28 = "c28_27";
	private static final String C26 = "c26_25";
	private static final String C24 = "c24_23";
	private static final String C22 = "c22_21";

	// String[] COLUMNS = new String[] { KEY_ID, X, Y, BSSID, Frequency, Level,
	// Timestamp, Channel };

	String[] COLUMNS = new String[] { KEY_ID, X, Y, ID_RP, BSSID, C90, C88,
			C86, C84, C82, C80, C78, C76, C74, C72, C70, C68, C66, C64, C62,
			C60, C58, C56, C54, C52, C50, C48, C46, C44, C42, C40, C38, C36,
			C34, C32, C30, C28, C26, C24, C22 };

	public int getNumberOfRow() {
		count=0;
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
		//TODO da testare 
		
		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		// 2. build query
		Cursor cursor = db.query(TABLE_NAME, // a. table
				COLUMNS, // b. column names
				" _id = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit
		
		
		
		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		// 4. build book object
		NewRowDatabase row2 = new NewRowDatabase();
		row2.setId(Integer.parseInt(cursor.getString(i++)));
		row2.setX(Float.parseFloat(cursor.getString(i++)));
		row2.setY(Float.parseFloat(cursor.getString(i++)));
		row2.setId_rp(Integer.parseInt(cursor.getString(i++)));
		row2.setBssid(cursor.getString(i++));
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
		
		
//		Log.d("getRow2(" + id + ")", row2.toString());

		
		i=0;
		cursor.close();
		// 5. return Row
		return row2;
	}

	
	public ArrayList<Integer> query(String[] column, String currentMac,String orderBy ){
		//TODO da provare 
		
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
				orderBy+" DESC", // g. order by
				null); // h. limit
		
		if (cursor.moveToFirst()) {
			do {
				id_rps.add(Integer.parseInt(cursor.getString(0)));				
			} while (cursor.moveToNext());
		}
		
	   cursor.close();
	   return id_rps;   	
	}
	
	
	
	
	public void addRow(RowDatabase row) {
		// Log.d("addBook", row.toString());
		// // 1. get reference to writable DB
		// SQLiteDatabase db = this.getWritableDatabase();
		// // 2. create ContentValues to add key "column"/value
		// ContentValues values = new ContentValues();
		// values.put(X, row.getX()); // get x
		// values.put(Y, row.getY()); // get y
		// values.put(BSSID, row.getBssid());
		// values.put(Frequency, row.getFrequency());
		// values.put(Level, row.getLevel());
		// values.put(Timestamp, row.getTimestamp());
		// values.put(Channel, row.getChannel());
		// // 3. insert
		// db.insert(TABLE_NAME, // table
		// null, // nullColumnHack
		// values); // key/value -> keys = column names/ values = column
		// // values
		// // 4. close
		// db.close();
	}

	// Get All Rows
	public List<RowDatabase> getAllBooks() {
		List<RowDatabase> rows = new LinkedList<RowDatabase>();
		// 1. build the query
		String query = "SELECT * FROM " + TABLE_NAME;
		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. go over each row, build book and add it to list
		RowDatabase row = null;
		if (cursor.moveToFirst()) {
			do {
				row = new RowDatabase();
				row.setId(Integer.parseInt(cursor.getString(0)));
				row.setX(Float.parseFloat(cursor.getString(1)));
				row.setY(Float.parseFloat(cursor.getString(2)));
				row.setBssid(cursor.getString(3));
				row.setFrequency(Integer.parseInt(cursor.getString(4)));
				row.setLevel(Integer.parseInt(cursor.getString(5)));
				row.setTimestamp(Long.parseLong(cursor.getString(6)));
				row.setChannel(Integer.parseInt(cursor.getString(7)));

				// Add book to books
				rows.add(row);
			} while (cursor.moveToNext());
		}
		Log.d("getAllBooks()", rows.toString());
		// return books
		return rows;
	}

	// Updating single book
	public int updateRow(RowDatabase row) {
//		// 1. get reference to writable DB
//		SQLiteDatabase db = this.getWritableDatabase();
//		// 2. create ContentValues to add key "column"/value
//		ContentValues values = new ContentValues();
//		values.put(X, row.getX()); // get x
//		values.put(Y, row.getY()); // get y
//		values.put(BSSID, row.getBssid()); // get y
//		values.put(Frequency, row.getFrequency()); // get y
//		values.put(Level, row.getLevel()); // get y
//		values.put(Timestamp, row.getTimestamp()); // get y
//		values.put(Channel, row.getChannel()); // get y
//		// 3. updating row
//		int i = db.update(TABLE_NAME, // table
//				values, // column/value
//				KEY_ID + " = ?", // selections
//				new String[] { String.valueOf(row.getId()) }); // selection
//																// args
//		// 4. close
//		db.close();
		return i;
	}

	// Deleting single book
	public void deleteRow(RowDatabase row) {
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		// 2. delete
		db.delete(TABLE_NAME, KEY_ID + " = ?",
				new String[] { String.valueOf(row.getId()) });
		// 3. close
		db.close();
		Log.d("deleteBook", row.toString());
	}

}