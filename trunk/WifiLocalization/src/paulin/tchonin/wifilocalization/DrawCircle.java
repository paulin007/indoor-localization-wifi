//package paulin.tchonin.wifilocalization;
//
//public class DrawCircle {
//
//}
//
//public class MyImageView extends ImageView {
//
//    List<Point> points = new ArrayList<Point>();
//    Paint paint = new Paint();
//
//    public MyImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    public void onDraw(Canvas canvas) {
//    	 super.onDraw(canvas);
//    	for (Point point : points) {
//            canvas.drawCircle(point.x, point.y, 5, paint);
//            // Log.d(TAG, "Painting: "+point);
//        }
//       
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
//        Point point = new Point();
//        point.x = event.getX();
//        point.y = event.getY();
//        points.add(point);
//        invalidate();
//        Log.d("", "point: " + point);
//        return true;
//    }
//
//    class Point {
//        float x, y;
//        @Override
//        public String toString() {
//            return x + ", " + y;
//        }
//    }
//}
//
//
//
//I used:
//
//MyImageView ivPic = (MyImageView) dialog.findViewById(R.id.ivPic);
//ivPic.setImageBitmap(picture);
