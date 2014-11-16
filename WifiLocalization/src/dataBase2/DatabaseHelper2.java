package dataBase2;

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

public class DatabaseHelper2 extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "/mnt/sdcard/wifiLocation2.db";
	private static final int SCHEMA_VERSION = 1;
	private SQLiteDatabase db=null;

	public DatabaseHelper2(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		

		String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, " +
		           "{2} REAL,"+
				   "{3} REAL,"+
		           "{4} INTEGER,"+
		           "{5} TEXT NOT NULL,"+
		           "{6} REAL,"+"{7} REAL,"+"{8} REAL,"+"{9} REAL,"+"{10} REAL,"+
		           "{11} REAL,"+"{12} REAL,"+"{13} REAL,"+"{14} REAL,"+"{15} REAL,"+
		           "{16} REAL,"+"{17} REAL,"+"{18} REAL,"+"{19} REAL,"+"{20} REAL,"+
		           "{21} REAL,"+"{22} REAL,"+"{23} REAL,"+"{24} REAL,"+"{25} REAL,"+
		           "{26} REAL,"+"{27} REAL,"+"{28} REAL,"+"{29} REAL,"+"{30} REAL,"+
		           "{31} REAL,"+"{32} REAL,"+"{33} REAL,"+"{34} REAL,"+"{35} REAL,"+
		           "{36} REAL,"+"{37} REAL,"+"{38} REAL,"+"{39} REAL,"+"{40} REAL);";
		           
		          
				
		db.execSQL(MessageFormat.format(sql, WifiTable2.TABLE_NAME,WifiTable2._ID,WifiTable2.X,
				                              WifiTable2.Y,WifiTable2.ID_RP, WifiTable2.BSSID,
				                              WifiTable2.C90,WifiTable2.C88,WifiTable2.C86,WifiTable2.C84,WifiTable2.C82,
				                              WifiTable2.C80,WifiTable2.C78,WifiTable2.C76,WifiTable2.C74,WifiTable2.C72,
				                              WifiTable2.C70,WifiTable2.C68,WifiTable2.C66,WifiTable2.C64,WifiTable2.C62,
				                              WifiTable2.C60,WifiTable2.C58,WifiTable2.C56,WifiTable2.C54,WifiTable2.C52,
				                              WifiTable2.C50,WifiTable2.C48,WifiTable2.C46,WifiTable2.C44,WifiTable2.C42,
				                              WifiTable2.C40,WifiTable2.C38,WifiTable2.C36,WifiTable2.C34,WifiTable2.C32,
				                              WifiTable2.C30,WifiTable2.C28,WifiTable2.C26,WifiTable2.C24,WifiTable2.C22));
				                             
				                              
	}

	public void inserisciDatiWifi(float x,float y,int id_rp,String bssid,
			double c90,double c88,double c86,double c84,double c82,
			double c80,double c78,double c76,double c74,double c72,
			double c70,double c68,double c66,double c64,double c62,
			double c60,double c58,double c56,double c54,double c52,
			double c50,double c48,double c46,double c44,double c42,
			double c40,double c38,double c36,double c34,double c32,
			double c30,double c28,double c26,double c24,double c22)
	{
		ContentValues v = new ContentValues();
		v.put(WifiTable2.X, x);
		v.put(WifiTable2.Y, y);
		v.put(WifiTable2.ID_RP, id_rp);
		v.put(WifiTable2.BSSID, bssid);
		v.put(WifiTable2.C90, c90); v.put(WifiTable2.C88, c88); v.put(WifiTable2.C86, c86); v.put(WifiTable2.C84, c84); v.put(WifiTable2.C82, c82);
		v.put(WifiTable2.C80, c80); v.put(WifiTable2.C78, c78); v.put(WifiTable2.C76, c76); v.put(WifiTable2.C74, c74); v.put(WifiTable2.C72, c72);
		v.put(WifiTable2.C70, c70); v.put(WifiTable2.C68, c68); v.put(WifiTable2.C66, c66); v.put(WifiTable2.C64, c64); v.put(WifiTable2.C62, c62);
		v.put(WifiTable2.C60, c60); v.put(WifiTable2.C58, c58); v.put(WifiTable2.C56, c56); v.put(WifiTable2.C54, c54); v.put(WifiTable2.C52, c52);
		v.put(WifiTable2.C50, c50); v.put(WifiTable2.C48, c48); v.put(WifiTable2.C46, c46); v.put(WifiTable2.C44, c44); v.put(WifiTable2.C42, c42);
		v.put(WifiTable2.C40, c40); v.put(WifiTable2.C38, c38); v.put(WifiTable2.C36, c36); v.put(WifiTable2.C34, c34); v.put(WifiTable2.C32, c32);
		v.put(WifiTable2.C30, c30); v.put(WifiTable2.C28, c28); v.put(WifiTable2.C26, c26); v.put(WifiTable2.C24, c24); v.put(WifiTable2.C22, c22);
	
		
		db = this.getWritableDatabase();

		db.insert(WifiTable2.TABLE_NAME, null, v);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
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

		private static final String BSSID = "bssid";

		private static final String Frequency = "frequency";

		private static final String Level = "level";

		private static final String Timestamp = "timestamp";

		private static final String Channel = "channel";

		String[] COLUMNS = new String[] { KEY_ID, X, Y, BSSID, Frequency, Level,
				Timestamp, Channel };

		
		
		public void addRow(RowDatabase row) {
			Log.d("addBook", row.toString());
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

		public RowDatabase getBook(int id) {
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
			row.setId(Integer.parseInt(cursor.getString(0)));
			row.setX(Float.parseFloat(cursor.getString(1)));
			row.setY(Float.parseFloat(cursor.getString(2)));
			row.setBssid(cursor.getString(3));
			row.setFrequency(Integer.parseInt(cursor.getString(4)));
			row.setLevel(Integer.parseInt(cursor.getString(5)));
			row.setTimestamp(Long.parseLong(cursor.getString(6)));
			row.setChannel(Integer.parseInt(cursor.getString(7)));
			Log.d("getRow(" + id + ")", row.toString());
			
			// 5. return Row
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
			Log.d("getAllBooks()", rows.toString());
			// return books
			return rows;
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
			Log.d("deleteBook", row.toString());
		}	
	
}