package button;

import onlinePhase.OutlierDetection;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonFindListener implements OnClickListener{

	private OutlierDetection outlierDetection;

	
	public ButtonFindListener(OutlierDetection outlierDetection) {
		super();
		this.outlierDetection = outlierDetection;
	}

	@Override
	public void onClick(View v) {
		outlierDetection.init();
		
	}

}
