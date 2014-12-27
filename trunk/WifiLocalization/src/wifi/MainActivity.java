package wifi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import onlinePhase.OutlierDetection;
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
import android.widget.ProgressBar;
import android.widget.Toast;
import button.ButtonCircle;
import button.ButtonFindListener;
import button.ButtonFixListener;
import dataBase.DatabaseHelper;
import dataBase2.DatabaseHelper2;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "WiFiDemo";
	WifiManager wifi;
	// BroadcastReceiver receiver;

	DatabaseHelper databaseHelper = new DatabaseHelper(this);

	DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);

	private final static ArrayList<Integer> channelsFrequency = new ArrayList<Integer>(
			Arrays.asList(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447,
					2452, 2457, 2462, 2467, 2472, 2484));

	Button buttonScan;
	private Button buttonCircle;
	Button buttonFix;
	Button buttonFind;
	Button buttonSample;
	private int mDelay = 100;

	private ProgressBar mProgressBar;

	private OutlierDetection outlierDetection;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Setup UI

		buttonScan = (Button) findViewById(R.id.buttonScan);
		buttonScan.setOnClickListener(this);
		buttonCircle = (Button) findViewById(R.id.buttonCircle);
		buttonCircle.setOnClickListener(new ButtonCircle());
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		
		buttonSample  = (Button) findViewById(R.id.buttonSample);
		buttonSample.setOnClickListener(this);

		buttonFix = (Button) findViewById(R.id.buttonFix);
		buttonFix.setOnClickListener(new ButtonFixListener(databaseHelper,
				databaseHelper2, mProgressBar));

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		outlierDetection = new OutlierDetection(databaseHelper2, wifi);

		buttonFind = (Button) findViewById(R.id.buttonFind);
		buttonFind.setOnClickListener(new ButtonFindListener(outlierDetection));

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public void onClick(View view) {

		if (view.getId() == R.id.buttonScan) {
			new ScanTask().execute();
		}

		if(view.getId()==R.id.buttonSample){
			wifi.startScan();
			ResultOfScan();
		}
		
	}

	public void ResultOfScan() {

		List<ScanResult> results = wifi.getScanResults();

		for (ScanResult result : results) {

			databaseHelper.inserisciDatiWifi(WifiView.relativeX,
					WifiView.relativeY, result.SSID, result.BSSID,
					result.level, result.frequency, result.timestamp,
					getChannelFromFrequency(result.frequency));
		}

	}

	public static int getChannelFromFrequency(int frequency) {
		return channelsFrequency.indexOf(Integer.valueOf(frequency));
	}

	
	/**
	 * Make a scan every mDelay ms and make NUMBER_SCAN for every RP
	 * @author paulintchonin
	 *
	 */
	class ScanTask extends AsyncTask<Void, Void, Void> {

		private static final int NUMBER_SCAN = 401;

		@Override
		protected Void doInBackground(Void... params) {
          	
			for (int i = 1; i < NUMBER_SCAN; i++) {
				wifi.startScan();
				ResultOfScan();
				sleep();
				
				mProgressBar.setProgress((int)(((float)i/(float)NUMBER_SCAN)*mProgressBar.getMax()));
			
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

		@Override
		protected void onPostExecute(Void result) {
			mProgressBar.setVisibility(ProgressBar.INVISIBLE);
			Toast.makeText(MainActivity.this, "scan complete", Toast.LENGTH_LONG)
					.show();

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