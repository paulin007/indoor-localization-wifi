package wifi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import paulin.tchonin.wifilocalization.R;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import dataBase.DatabaseHelper;

public class WiFiDemo extends Activity implements OnClickListener {
	private static final String TAG = "WiFiDemo";
	WifiManager wifi;
	//BroadcastReceiver receiver;
	
	DatabaseHelper databaseHelper = new DatabaseHelper(this);
	private final static ArrayList<Integer> channelsFrequency = new ArrayList<Integer>(
	        Arrays.asList(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447,
	                2452, 2457, 2462, 2467, 2472, 2484));

	TextView textStatus;
	Button buttonScan;
	Button buttonCircle;
	private int mDelay = 1000;
		

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Setup UI
		textStatus = (TextView) findViewById(R.id.textStatus);
		buttonScan = (Button) findViewById(R.id.buttonScan);
		buttonCircle = (Button) findViewById(R.id.buttonCircle);
		buttonScan.setOnClickListener(this);
		buttonCircle.setOnClickListener(new buttonListener());
			      

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
	}

	
	@Override
	protected void onStop() {
		super.onStop();
	}

	public void onClick(View view) {
		
		if (view.getId() == R.id.buttonScan) {
			Log.d(TAG, "onClick() wifi.startScan()");
			
			new ScanTask().execute();
			
//			wifi.startScan();
//			ResultOfScan();
			
		}

	}
	
	public void ResultOfScan(){
		
	
		List<ScanResult> results = wifi.getScanResults();
//		ScanResult bestSignal = null;
		for (ScanResult result : results) {
//			textStatus.append("\n\n"+"SSID :"+result.SSID+"\n"+"BSSID :"+result.BSSID+
//					"\ncapabilities :"+result.capabilities+
//					"\nfrequency :"+result.frequency+
//					"\nlevel :"+result.level+
//					"\ntimestamp : "+result.timestamp /* working only for api min 17*/ +
//					"\nchannel : "+getChannelFromFrequency(result.frequency)); 
			//Log.e("SSID", result.SSID);
			
			databaseHelper.inserisciDatiWifi(WifiView.x, WifiView.y, result.BSSID, result.frequency,
					result.level, result.timestamp, getChannelFromFrequency(result.frequency));
		}

	}
	
	public static int getChannelFromFrequency(int frequency) {
	    return channelsFrequency.indexOf(Integer.valueOf(frequency));
	}
	
	
	
	class ScanTask extends AsyncTask<Integer, Integer, Integer>{

		@Override
		protected Integer doInBackground(Integer... params) {
		
						for (int i = 1; i < 11; i++) {
							wifi.startScan();
							ResultOfScan();
							sleep();
						}						
			return null;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			Toast.makeText(WiFiDemo.this, "scan complete",
					Toast.LENGTH_LONG).show();
			
		}
		
		private void sleep() {
			try {
				
				Thread.sleep(mDelay);
			} catch (InterruptedException e) {
				Log.e(TAG, e.toString());
			}
		}
		
	}

}