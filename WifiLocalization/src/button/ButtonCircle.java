package button;

import wifi.WifiView;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonCircle implements OnClickListener {

	private boolean isDrawable = true;

	public ButtonCircle() {

	}

	@Override
	public void onClick(View v) {

		if (isDrawable) {
			WifiView.isCircle = true;
			isDrawable = false;
		} else {
			WifiView.isCircle = false;
			isDrawable = true;
		}
	}
}
