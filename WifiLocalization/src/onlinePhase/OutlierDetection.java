package onlinePhase;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import wifi.WifiView;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import model.NewRowDatabase;

import dataBase2.DatabaseHelper2;

public class OutlierDetection {

	private final static String TAG = OutlierDetection.class.getName();
	private DatabaseHelper2 databaseHelper2;
	private int n;// number of access point
	private int m; // number of references points
	private NewRowDatabase row2;
	private WifiManager wifi;

    private TreeSet<String> listOfMacs = new TreeSet<String>();
    private ArrayList<Potenza> s = new ArrayList<Potenza>(); // signal received by the tag
    
	public OutlierDetection(DatabaseHelper2 databaseHelper2,WifiManager wifi) {
		super();
		this.databaseHelper2 = databaseHelper2;
		this.wifi = wifi;
		row2 = new NewRowDatabase();
		n = getNumberOfMac();
		m = getNumberOfRP();
	}

	public int getNumberOfMac() {
       int n = databaseHelper2.getNumberOfRow();
		Log.e(TAG, " num ="+databaseHelper2.getNumberOfRow()+"  "+n );
		
		for (int i = 1; i < n+1; i++) {

			listOfMacs.add(databaseHelper2.getRow2(i).getBssid());
		}
		Log.e(TAG, " numMac ="+listOfMacs.size());
		return listOfMacs.size();
	}

	public int getNumberOfRP() {
		return databaseHelper2.getRow2(databaseHelper2.getNumberOfRow())
				.getId_rp();
	}
	
	public void init(){
		//TODO da mettere in un altro posto
		
		wifi.startScan();
		List<ScanResult> results = wifi.getScanResults();
		for (ScanResult result : results) {
			s.add(new Potenza(result.BSSID, result.level));			
		}
		
		Log.e(TAG+"  n = ", ""+n);
		Log.e(TAG+"  m = ", ""+m);
		Log.e(TAG+"  s ", s.toString());
		
	}

	
	public ArrayList<Potenza> getS() {
		return s;
	}
	
}
