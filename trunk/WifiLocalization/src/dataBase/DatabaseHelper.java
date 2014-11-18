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

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "/mnt/sdcard/wifiLocation.db";
	private static final int SCHEMA_VERSION = 1;
	private SQLiteDatabase db = null;
	private int count = 0;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		/*
		 * CREATE TABLE nom_de_la_table ( nom_du_champ_1 type {contraintes},
		 * nom_du_champ_2 type {contraintes}, …);
		 * 
		 * Pour SQLite, c'est simple, il n'existe que cinq types de données :
		 * NULL pour les données NULL. INTEGER pour les entiers (sans virgule).
		 * REAL pour les nombres réels (avec virgule). TEXT pour les chaînes de
		 * caractères. BLOB pour les données brutes, par exemple si vous voulez
		 * mettre une image dans votre base de données
		 */
		String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "{2} REAL,"
				+ "{3} REAL,"
				+ "{4} TEXT NOT NULL,"
				+ "{5} INTEGER,"
				+ "{6} INTEGER,"
				+ "{7} INTEGER,"
				+ "{8} INTEGER);";

		db.execSQL(MessageFormat.format(sql, WifiTable.TABLE_NAME,
				WifiTable._ID, WifiTable.X, WifiTable.Y, WifiTable.BSSID,
				WifiTable.Frequency, WifiTable.Level, WifiTable.Timestamp,
				WifiTable.Channel));
	}

	public void inserisciDatiWifi(float x, float y, String bssid,
			int frequency, int level, Long timestamp, int channel) {
		ContentValues v = new ContentValues();
		v.put(WifiTable.X, x);
		v.put(WifiTable.Y, y);
		v.put(WifiTable.BSSID, bssid);
		v.put(WifiTable.Frequency, frequency);
		v.put(WifiTable.Level, level);
		v.put(WifiTable.Timestamp, timestamp);
		v.put(WifiTable.Channel, channel);

		db = this.getWritableDatabase();

		db.insert(WifiTable.TABLE_NAME, null, v);
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
	private static final String KEY_ID = "_id";

	private static final String X = "x";

	private static final String Y = "y";

	private static final String BSSID = "bssid";

	private static final String Frequency = "frequency";

	private static final String Level = "level";

	private static final String Timestamp = "timestamp";

	private static final String Channel = "channel";

	String[] COLUMNS = new String[] { KEY_ID, X, Y, BSSID, Frequency, Level,
			Timestamp, Channel };

	public void addRow(RowDatabase row) {
		// Log.d("addBook", row.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(X, row.getX()); // get x
		values.put(Y, row.getY()); // get y
		values.put(BSSID, row.getBssid());
		values.put(Frequency, row.getFrequency());
		values.put(Level, row.getLevel());
		values.put(Timestamp, row.getTimestamp());
		values.put(Channel, row.getChannel());
		// 3. insert
		db.insert(TABLE_NAME, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
							// values
		// 4. close
		db.close();
	}

	public RowDatabase getRow(int id) {
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
		RowDatabase row = new RowDatabase();
		row.setId(Integer.parseInt(cursor.getString(0)));
		row.setX(Float.parseFloat(cursor.getString(1)));
		row.setY(Float.parseFloat(cursor.getString(2)));
		row.setBssid(cursor.getString(3));
		row.setFrequency(Integer.parseInt(cursor.getString(4)));
		row.setLevel(Integer.parseInt(cursor.getString(5)));
		row.setTimestamp(Long.parseLong(cursor.getString(6)));
		row.setChannel(Integer.parseInt(cursor.getString(7)));
//		 Log.d("getRow(" + id + ")", row.toString());

		// 5. return Row
		cursor.close();
		return row;
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
		// Log.d("getAllBooks()", rows.toString());
		// return books
		return rows;
	}

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

	// Updating single book
	public int updateRow(RowDatabase row) {
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(X, row.getX()); // get x
		values.put(Y, row.getY()); // get y
		values.put(BSSID, row.getBssid()); // get y
		values.put(Frequency, row.getFrequency()); // get y
		values.put(Level, row.getLevel()); // get y
		values.put(Timestamp, row.getTimestamp()); // get y
		values.put(Channel, row.getChannel()); // get y
		// 3. updating row
		int i = db.update(TABLE_NAME, // table
				values, // column/value
				KEY_ID + " = ?", // selections
				new String[] { String.valueOf(row.getId()) }); // selection
																// args
		// 4. close
		db.close();
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
		// Log.d("deleteBook", row.toString());
	}

}