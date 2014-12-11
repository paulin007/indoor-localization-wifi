package wifi;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonCircleListener implements OnClickListener{

	private int flag =0;
	
	private boolean isDrawable = true;
	
	public ButtonCircleListener() {
		
	}
	
	@Override
	public void onClick(View v) {
//	Log.e("buttonListe", "button");
		if(isDrawable){
			WifiView.isCircle=true;
			isDrawable = false;
		}else {
			WifiView.isCircle=false;
			isDrawable = true;
			
		}
		
	}

}
