package wifi;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Debug {
	
	public Debug() {
		
	}

	
	   
    // prima soluzione per vedere la (x,y) dove inizia l'immagine , 
    // e per vedere anche le dimensioni dell'immagine 
  
  private void logImageViewMatrixInfos(Matrix matrix, ImageView imageView) {
      float[] values = new float[9];
         matrix.getValues(values);
         float globalX = values[2];
         float globalY = values[5];
         float width = values[0]* imageView.getWidth();
         float height = values[4] * imageView.getHeight();
         float height2 = values[4] * imageView.getDrawable().getIntrinsicHeight();
        // float height = matrixValues[4]*((ImageView)currentView).getDrawable().getIntrinsicHeight();
//         Log.e("Log value", "Image Details: xPos: " + globalX + " yPos: " + globalY + "\nwidth: " + width + " height: " + height+ " \n height2: " + height2);
  }

  private float getXValueFromMatrix(Matrix matrix) {

      float[] values = new float[9];
         matrix.getValues(values);
         float globalX = values[2];

         return globalX;
  }

  private float getYValueFromMatrix(Matrix matrix) {

      float[] values = new float[9];
         matrix.getValues(values);
         float globalY = values[5];

         return globalY;
  }

  private float getWidthFromMatrix(Matrix matrix, ImageView imageview) {

      float[] values = new float[9];
         matrix.getValues(values);

         float width = values[0]* imageview.getWidth();

         return width;
  }

  private float getHeightFromMatrix(Matrix matrix, ImageView imageview) {

      float[] values = new float[9];
         matrix.getValues(values);

         float height = values[4] * imageview.getHeight();
         

         return height;
  }
  
  
  // soluzione 2  good 
  
  private void logRelativeXYInfos(Matrix matrix, MotionEvent event) {
    
       
  
      // Get the values of the matrix
         float[] values = new float[9];
         matrix.getValues(values);

         // values[2] and values[5] are the x,y coordinates of the top left corner of the drawable image, regardless of the zoom factor.
         // values[0] and values[4] are the zoom factors for the image's width and height respectively. If you zoom at the same factor, these should both be the same value.

         // event is the touch event for MotionEvent.ACTION_UP
         float relativeX = (event.getX() - values[2]) / values[0];
         float relativeY = (event.getY() - values[5]) / values[4];
         
         /*
          * Nota bene: relativeX e relativeY sono i valori da salvare dentro il data base
          *            e event.geX e event.getY che useremo per disegnare la posizione predetto.
          *            quindi bisogno risolvere l'equazione averli.
          */
  
     Log.e("Log value", "position Details: xPos: " + relativeX + " yPos: " + relativeY );
     
     
  
  
  }
  

  // soluzione 3  NO
  
  private void soluzione3(ImageView imageView){
	   
	   
	   Drawable drawable = imageView.getDrawable();
	   Rect imageBounds = drawable.getBounds();

	   //original height and width of the bitmap
	   int intrinsicHeight = drawable.getIntrinsicHeight();
	   int intrinsicWidth = drawable.getIntrinsicWidth();

	   //height and width of the visible (scaled) image
	   int scaledHeight = imageBounds.height();
	   int scaledWidth = imageBounds.width();

	   //Find the ratio of the original image to the scaled image
	   //Should normally be equal unless a disproportionate scaling
	   //(e.g. fitXY) is used.
	   float heightRatio = intrinsicHeight / scaledHeight;
	   float widthRatio = intrinsicWidth / scaledWidth;

	   //do whatever magic to get your touch point
	   //MotionEvent event;

	   //get the distance from the left and top of the image bounds
//	   float scaledImageOffsetX = event.getX() - imageBounds.left;
//	   float scaledImageOffsetY = event.getY() - imageBounds.top;

	   //scale these distances according to the ratio of your scaling
	   //For example, if the original image is 1.5x the size of the scaled
	   //image, and your offset is (10, 20), your original image offset
	   //values should be (15, 30). 
//	   float originalImageOffsetX = scaledImageOffsetX * widthRatio;
//	   float originalImageOffsetY = scaledImageOffsetY * heightRatio;
	   
	 Log.e("Log value", "original height of the bitmap: " + intrinsicHeight + "\noriginal width of the bitmap: " + intrinsicWidth+
			    "\n height and width of the visible (scaled) image \n " + "height :"+scaledHeight+"\n width :"+scaledWidth+"\n");
  }


	
}
