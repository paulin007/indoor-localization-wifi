package wifi;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class buttonListener implements OnClickListener{

	private int flag =0;
	
	public buttonListener() {
		
	}
	
	@Override
	public void onClick(View v) {
	Log.e("buttonListe", "button");
		if(flag==0){
			WifiView.isCircle=true;
			flag = 1;
		}else if(flag==1){
			WifiView.isCircle=false;
			flag=0;
		}
		
	}

}
