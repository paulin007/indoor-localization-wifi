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
import button.ButtonFindListener;
import dataBase.DatabaseHelper;
import dataBase2.DatabaseHelper2;

public class WiFiDemo extends Activity implements OnClickListener {
	private static final String TAG = "WiFiDemo";
	WifiManager wifi;
	// BroadcastReceiver receiver;

	DatabaseHelper databaseHelper = new DatabaseHelper(this);

	DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);

	private final static ArrayList<Integer> channelsFrequency = new ArrayList<Integer>(
			Arrays.asList(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447,
					2452, 2457, 2462, 2467, 2472, 2484));

	Button buttonScan;
	Button buttonCircle;
	Button buttonFix;
	Button buttonFind;
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
		buttonCircle.setOnClickListener(new buttonListener());
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

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

	class ScanTask extends AsyncTask<Integer, Integer, Integer> {

		
		
		@Override
		protected Integer doInBackground(Integer... params) {
          	
			for (int i = 1; i < 201; i++) {
				wifi.startScan();
				ResultOfScan();
				sleep();
				// publishProgress(i * 200);
				mProgressBar.setProgress((int)(((float)i/(float)200)*mProgressBar.getMax()));
			
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// mProgressBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Integer result) {
			mProgressBar.setVisibility(ProgressBar.INVISIBLE);
			Toast.makeText(WiFiDemo.this, "scan complete", Toast.LENGTH_LONG)
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