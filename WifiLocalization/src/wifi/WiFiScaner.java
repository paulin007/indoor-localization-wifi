package wifi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class WiFiScaner extends BroadcastReceiver {
	private static final String TAG = "WiFiScanReceiver";
	WiFiDemo wifiDemo;
	
	private final static ArrayList<Integer> channelsFrequency = new ArrayList<Integer>(
	        Arrays.asList(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447,
	                2452, 2457, 2462, 2467, 2472, 2484));

	public WiFiScaner(WiFiDemo wifiDemo) {
		super();
		this.wifiDemo = wifiDemo;
	}

	@Override
	public void onReceive(Context c, Intent intent) {
		List<ScanResult> results = wifiDemo.wifi.getScanResults();
		ScanResult bestSignal = null;
		for (ScanResult result : results) {
			wifiDemo.textStatus.append("\n\n"+"SSID :"+result.SSID+"\n"+"BSSID :"+result.BSSID+
					"\ncapabilities :"+result.capabilities+
					"\nfrequency :"+result.frequency+
					"\nlevel :"+result.level+
					"\ntimestamp : "+result.timestamp /* working only for api min 17*/ +
					"\nchannel : "+getChannelFromFrequency(result.frequency)); 
			//Log.e("SSID", result.SSID);
			if (bestSignal == null
					|| WifiManager.compareSignalLevel(bestSignal.level,
							result.level) < 0)
				bestSignal = result;
			
			/*
			 * BSSID : The address of the access point. 
			 * SSID : The network name. 
			 * capabilities : Describes the authentication, key management, 
			 *               and encryption schemes supported by the access point.
			 * frequency : The frequency in MHz of the channel over which the client
			 *             is communicating with the access point.    
			 * level : The detected signal level in dBm. At least those are the units used by the TI driver. 
			 * TimeStamp : Time Synchronization Function (tsf) timestamp in microseconds when
			 *                this result was last seen.                        
			 */
		}

		String message = String.format(
				"%s networks found. %s is the strongest.", results.size(),
				bestSignal.SSID);
		Toast.makeText(wifiDemo, message, Toast.LENGTH_LONG).show();

		Log.d(TAG, "onReceive() message: " + message);
	}
	

	public static int getChannelFromFrequency(int frequency) {
	    return channelsFrequency.indexOf(Integer.valueOf(frequency));
	}
	

}