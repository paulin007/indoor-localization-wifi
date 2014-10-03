package wifi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class WifiView extends View{
	
	public static float x=400;
	public static float y=400;
	private int r=20;
	private Paint mPaint;
	
	

//	public WifiView(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		init();
//	}

	public WifiView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public WifiView(Context context) {
		super(context);
   init();
	}
	
	private void init(){
		
		mPaint = new Paint(); // 3
		mPaint.setColor(0xffff0000);
		mPaint.setStrokeWidth(6);

		
		
		
OnTouchListener onTouchListener = new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
//				 Log.d(TAG, "onTouch!" + event);
//				 Log.d(TAG, "onTouch!" + event.getAction());
				 int action = event.getAction();
				 if(action== MotionEvent.ACTION_DOWN|| action == MotionEvent.ACTION_MOVE){
						x = event.getX();
						y = event.getY();
				Log.d("", x+"  "+y);		
						
					}
				return true;
			}
		};
		
		setOnTouchListener(onTouchListener);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		canvas.drawCircle(x, y, r, mPaint);
		
		postInvalidateDelayed(20);
	}
	
}
