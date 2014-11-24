package onlinePhase;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;
import dataBase2.DatabaseHelper2;

public class SelectionPoints {
	private static final String Tag = SelectionPoints.class.getName();
	private static final int N = 4;
	private static final int NumRegions = 10;
	private static final int row = 4;
	private static final int column = 18;

	private DatabaseHelper2 databaseHelper2;
	private int[][] table;
	private ArrayList<Integer> inlier = new ArrayList<Integer>();
	private int m;

	private Integer[] rpGroup;
	// private HashMap<Integer, Integer[]> regions = new HashMap<Integer,
	// Integer[]>();

	private int[][] regions = new int[row][column];

	public SelectionPoints(DatabaseHelper2 databaseHelper2, int[][] table,
			ArrayList<Integer> inlier, int m) {
		super();
		this.databaseHelper2 = databaseHelper2;
		this.table = table;
		this.inlier = inlier;
		this.m = m;
		fillRegion();
	}

	// TODO da implementare dopo
	public void fillRegion() {
		// rpGroup = new Integer[N];
		// int offset = 0;
		// int j = 0;
		// int index=0;
		//
		// for (int i = 1; i < m+1; i++) {
		// rpGroup[j] = i - offset;
		// j++;
		//
		// if (j == N) {
		// regions.put(++index, rpGroup);
		// j = 0;
		// offset = -2;
		// }
		// }
		// //TODO migliorare
		// if(m%3==0){
		// rpGroup[j]=m-1;
		// rpGroup[j+1]=m;
		// regions.put(++index, rpGroup);
		//
		// }
		int offset = 0;
		int count = 1;
		
		for (int nColumn = 0; nColumn < column; nColumn++) {
			for (int n_row = 0; n_row < row; n_row++) {
			regions[n_row][nColumn] = count - offset;
			  count++;
			}
             offset = -2;
		}
		
		// just for logcat 
//		for (int nColumn = 0; nColumn < column; nColumn++) {
//			for (int n_row = 0; n_row < row; n_row++) {
//			Log.e(Tag + " fill region ", ""+regions[n_row][nColumn]);
//			}
//             
//		}
		
		
		
		
	}

}
