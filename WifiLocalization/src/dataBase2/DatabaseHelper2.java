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
		           "{6} REAL,"+
		           "{7} REAL);";
		          
				
		db.execSQL(MessageFormat.format(sql, WifiTable2.TABLE_NAME,WifiTable2._ID,WifiTable2.X,
				                              WifiTable2.Y,WifiTable2.ID_RP, WifiTable2.BSSID,
				                              WifiTable2.Media,
				                              WifiTable2.SD));
				                              
	}

	public void inserisciDatiWifi(float x,float y,int id_rp,String bssid,double media,double sd)
	{
		ContentValues v = new ContentValues();
		v.put(WifiTable2.X, x);
		v.put(WifiTable2.Y, y);
		v.put(WifiTable2.ID_RP, id_rp);
		v.put(WifiTable2.BSSID, bssid);
		v.put(WifiTable2.Media, media);
		v.put(WifiTable2.SD, sd);
		
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