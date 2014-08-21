package dataBase;

import java.text.MessageFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "/mnt/sdcard/wifiLocation.db";
	private static final int SCHEMA_VERSION = 1;
	private SQLiteDatabase db=null;

	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
		/*
		 * CREATE TABLE nom_de_la_table (
nom_du_champ_1 type {contraintes},
nom_du_champ_2 type {contraintes},
…);

Pour SQLite, c'est simple, il n'existe que cinq types de données :
NULL pour les données NULL.
INTEGER pour les entiers (sans virgule).
REAL pour les nombres réels (avec virgule).
TEXT pour les chaînes de caractères.
BLOB pour les données brutes, par exemple si vous voulez mettre une image dans votre base de données
		 */
		String sql = "CREATE TABLE {0} ({1} INTEGER PRIMARY KEY AUTOINCREMENT, " +
		           "{2} REAL,"+
				   "{3} REAL,"+
		           "{4} TEXT NOT NULL,"+
		           "{5} INTEGER,"+
		           "{6} INTEGER,"+
		           "{7} INTEGER,"+
		           "{8} INTEGER);";
				
		db.execSQL(MessageFormat.format(sql, WifiTable.TABLE_NAME,WifiTable._ID,WifiTable.X,
				                              WifiTable.Y, WifiTable.BSSID,
				                              WifiTable.Frequency,
				                              WifiTable.Level,
				                              WifiTable.Timestamp,
				                              WifiTable.Channel));
	}

	public void inserisciDatiWifi(int x,int y,String bssid,int frequency,int level,
			Long timestamp,int channel)
	{
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
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
	}
}