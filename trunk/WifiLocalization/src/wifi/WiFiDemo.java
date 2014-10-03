package wifi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import paulin.tchonin.wifilocalization.R;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
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
		

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Setup UI
		textStatus = (TextView) findViewById(R.id.textStatus);
		buttonScan = (Button) findViewById(R.id.buttonScan);
		buttonScan.setOnClickListener(this);
		
	
		
		      

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				
		
//		View v = new View(this){
//			@Override
//			protected void onDraw(Canvas canvas) {			
//				canvas.drawCircle(x, y, r, mPaint);
//				postInvalidateDelayed(20);
////				super.onDraw(canvas);
//			}
//		};
//		v.setBackgroundResource(R.drawable.piano);
//		Log.d("fuori", "lala");
//		setContentView(v);
//		OnTouchListener onTouchListener = new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
////				 Log.d(TAG, "onTouch!" + event);
////				 Log.d(TAG, "onTouch!" + event.getAction());
//				 int action = event.getAction();
//				 if(action== MotionEvent.ACTION_DOWN|| action == MotionEvent.ACTION_MOVE){
//						x = event.getX();
//						y = event.getY();
//				Log.d(TAG, x+"  "+y);		
//						
//					}
//				return true;
//			}
//		};
//		
//		v.setOnTouchListener(onTouchListener);
		
	}
	


//	@Override
//	public void onStop() {
////		unregisterReceiver(receiver);
//	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

	public void onClick(View view) {
		
		if (view.getId() == R.id.buttonScan) {
			Log.d(TAG, "onClick() wifi.startScan()");
			
			wifi.startScan();
			ResultOfScan();
			
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
}