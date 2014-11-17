package wifi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class WifiView extends ImageView implements OnTouchListener{
	
	public static float x=-400;
	public static float y=-400;
	private int r=20;
	private Paint mPaint;

	
	 static float  relativeX ;
     static float  relativeY ;
	
     private static final String TAG = "View";
	   // These matrices will be used to move and zoom image
	   Matrix matrix = new Matrix();
	   Matrix savedMatrix = new Matrix();

	   // We can be in one of these 3 states
	   static final int NONE = 0;
	   static final int DRAG = 1;
	   static final int ZOOM = 2;
	   static boolean isCircle = false;
	   int mode = NONE;

	   // Remember some things for zooming
	   PointF start = new PointF();
	   PointF mid = new PointF();
	   float oldDist = 1f;
	
	

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
	
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		if(isCircle){
	        canvas.drawCircle(x, y, r, mPaint);
	      			
			postInvalidateDelayed(20);
			}
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		 // Dump touch event to log
	      dumpEvent(event);      
	     
	/*
	 * The matrix variable will be calculated inside the switch statement when
	we implement the gestures.

	A drag gesture starts when the first finger is pressed to the screen
	(ACTION_DOWN) and ends when it is removed (ACTION_UP or ACTION_
	POINTER_UP).

	 */
	      // Handle touch events here...
	      switch (event.getAction() & MotionEvent.ACTION_MASK) {
	      case MotionEvent.ACTION_DOWN:
	         savedMatrix.set(matrix);
	         start.set(event.getX(), event.getY());
	    //     Log.d(TAG, "mode=DRAG");
	         
	         mode = DRAG;
	       
//	         logImageViewMatrixInfos(matrix, this);
         logRelativeXYInfos(matrix, event);
	        
	         
	         
	         x = event.getX();
			 y = event.getY();
			 invalidate();
	        
	         break;
	      case MotionEvent.ACTION_POINTER_DOWN:
	         oldDist = spacing(event);
	      //   Log.d(TAG, "oldDist=" + oldDist);
	         if (oldDist > 10f) {
	            savedMatrix.set(matrix);
	            midPoint(mid, event);
	            mode = ZOOM;
	        //    Log.d(TAG, "mode=ZOOM");
	            
	         }
//	         logImageViewMatrixInfos(matrix, this);
	         break;
	      case MotionEvent.ACTION_UP:
	    	  x = event.getX();
			  y = event.getY();
			  invalidate();
	      case MotionEvent.ACTION_POINTER_UP:
	         mode = NONE;
	   //      Log.d(TAG, "mode=NONE");
//	         logImageViewMatrixInfos(matrix, this);
	         break;
	      case MotionEvent.ACTION_MOVE:
	         if (mode == DRAG && !isCircle) {
	            // ...
	            matrix.set(savedMatrix);
	            matrix.postTranslate(event.getX() - start.x,
	                  event.getY() - start.y);
	         }
	         else if (mode == ZOOM && !isCircle) {
	            float newDist = spacing(event);
//	            Log.d(TAG, "newDist=" + newDist);
	            if (newDist > 10f) {
	               matrix.set(savedMatrix);
	               float scale = newDist / oldDist;
	               matrix.postScale(scale, scale, mid.x, mid.y);
	            }
	         }
	         
	         if(isCircle){
	        	 x = event.getX();
				 y = event.getY();
				 invalidate();
	         }
//	         logImageViewMatrixInfos(matrix, this);
	         break;
	      }

	      
	      setImageMatrix(matrix);
	      
//	      soluzione3(this);
	      return true; // indicate event was handled
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event); // indicate event was handled
	}
	
	
	 private void dumpEvent(MotionEvent event) {
	      String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
	            "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
	      StringBuilder sb = new StringBuilder();
	      int action = event.getAction();
	      int actionCode = action & MotionEvent.ACTION_MASK;
	      sb.append("event ACTION_").append(names[actionCode]);
	      if (actionCode == MotionEvent.ACTION_POINTER_DOWN
	            || actionCode == MotionEvent.ACTION_POINTER_UP) {
	         sb.append("(pid ").append(
	               action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
	         sb.append(")");
	      }
	      sb.append("[");
	      for (int i = 0; i < event.getPointerCount(); i++) {
	         sb.append("#").append(i);
	         sb.append("(pid ").append(event.getPointerId(i));
	         sb.append(")=").append((int) event.getX(i));
	         sb.append(",").append((int) event.getY(i));
	         if (i + 1 < event.getPointerCount())
	            sb.append(";");
	      }
	      sb.append("]");
	   //   Log.d(TAG, sb.toString());
	   }


	   /** Determine the space between the first two fingers */
	   private float spacing(MotionEvent event) {
	      float x = event.getX(0) - event.getX(1);
	      float y = event.getY(0) - event.getY(1);
	      return FloatMath.sqrt(x * x + y * y);
	   }

	   /** Calculate the mid point of the first two fingers */
	   private void midPoint(PointF point, MotionEvent event) {
	      float x = event.getX(0) + event.getX(1);
	      float y = event.getY(0) + event.getY(1);
	      point.set(x / 2, y / 2);
	   }
	   
	   
	   private void logRelativeXYInfos(Matrix matrix, MotionEvent event) {
		    
	       
		   
		      // Get the values of the matrix
		         float[] values = new float[9];
		         matrix.getValues(values);

		         // values[2] and values[5] are the x,y coordinates of the top left corner of the drawable image, regardless of the zoom factor.
		         // values[0] and values[4] are the zoom factors for the image's width and height respectively. If you zoom at the same factor, these should both be the same value.

		         // event is the touch event for MotionEvent.ACTION_UP
		          relativeX = (event.getX() - values[2]) / values[0];
		          relativeY = (event.getY() - values[5]) / values[4];
		         
		         /*
		          * Nota bene: relativeX e relativeY sono i valori da salvare dentro il data base
		          *            e event.geX e event.getY che useremo per disegnare la posizione predetto.
		          *            quindi bisogno risolvere l'equazione averli.
		          */
		  
//		     Log.e("Log value", "position Details: xPos: " + relativeX + " yPos: " + relativeY );
		  }
	   
	

}
