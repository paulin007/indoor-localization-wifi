package onlinePhase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import wifi.WifiView;
import android.util.Log;
import dataBase2.DatabaseHelper2;

public class SelectionPoints {
	private static final String Tag = SelectionPoints.class.getName();
	private static final int N = 4;
//	private static final int NumRegions = 10;
	private static final int row = 4;
	private static final int NUMBER_REGIONS = 29; //19

	private DatabaseHelper2 databaseHelper2;
	private int[][] table;
	private ArrayList<Integer> inlier;
	private int m;
	private ArrayList<Potenza> s ;
	private String[] columnsLevels;
	private ArrayList<String> listOfMacs;
	
	
	private int nPrimo;
	private double[] sops = new double[NUMBER_REGIONS];
	
	private int[][] regions = new int[row][NUMBER_REGIONS];
	
	private int SelectedRegion = 0;
	
	

	public SelectionPoints(DatabaseHelper2 databaseHelper2, int[][] table,
			ArrayList<Integer> inlier, int m, ArrayList<Potenza> s,String[] columnsLevels,ArrayList<String> listOfMacs) {
		super();
		this.databaseHelper2 = databaseHelper2;
		this.table = table;
		this.inlier = inlier;
		this.m = m;
		this.s = s;
		this.columnsLevels= columnsLevels;
		this.listOfMacs = listOfMacs;
		nPrimo = inlier.size();	
		
		fillRegion();
		sumOfPropability();
		findMaxSop();
		locationEstimation();
//		writeIntoFile();
	}

	
	public void fillRegion() {
		int offset = 0;
		int count = 1;
		
		for (int nColumn = 0; nColumn < NUMBER_REGIONS; nColumn++) {
			for (int n_row = 0; n_row < row; n_row++) {
			regions[n_row][nColumn] = count - offset;
			
			if((count-offset)==m){
				  break;
			 }			
			  count++;
			}
             offset = 2*(nColumn+1);
		}
		
		// just for logcat 
//		for (int nColumn = 0; nColumn < column; nColumn++) {
//			for (int n_row = 0; n_row < row; n_row++) {
//			Log.e(Tag + " fill region ", ""+regions[n_row][nColumn]);
//			}
//             
//		}	
	}
	
	
	public void sumOfPropability(){
		int currentId_RP;
		String currentMac;
		int currentLevel;
		double sop = 0;
		
		
		for (int nColumn = 0; nColumn < NUMBER_REGIONS; nColumn++) {
			
			for (int nRow = 0; nRow < N; nRow++) {
				currentId_RP = regions[nRow][nColumn];
				
				for (int i = 0; i < inlier.size(); i++) {
					currentMac = listOfMacs.get(inlier.get(i));
					currentLevel = findMacOnReceivedSignal(currentMac);
					String[] columns = new String[] { columnsLevels[Math.abs(currentLevel)] };
				sop +=	databaseHelper2.queryGetProbability(columns, currentId_RP, currentMac);				
					
				}
						
			}
			sops[nColumn] = sop;
			sop = 0;
			
		}
		
	}
	
	
	public void findMaxSop(){
		double max = sops[0];
		
		
		
		for (int i = 0; i < sops.length; i++) {
			Log.e(Tag+" findMaxSop", "sop["+i+"] = "+sops[i]);
			if(sops[i] > max){
				max = sops[i];
				SelectedRegion = i;
			}
			
		}
		
		Log.e(Tag, "region : "+SelectedRegion);
		
	}
	
	
	private int findMacOnReceivedSignal(String currentmac) {
		// TODO da provare
		for (int i = 0; i < s.size(); i++) {
			// Log.e(TAG+" currentmac", currentmac);
			// Log.e(TAG+" s("+i+") =", s.get(i).getMac());
			if (currentmac.equals(s.get(i).getMac())) {
				// Log.e(TAG + "  findMac ", " the tag see the mac " +
				// currentmac
				// + " with level = " + s.get(i).getLevel());

				return s.get(i).getLevel();
			}
		}
		// Log.e(TAG + "  findMac ", " the tag dn't see the mac " + currentmac);
		return 0;
	}
	
	public void locationEstimation(){
	
		Punto currentPunto;
		double wi ;
		Punto puntoEstimate = new Punto(0, 0);
		float estimateX = 0;
		float estimateY = 0;
		
		
		for (int i = 1; i < N + 1; i++) {
		currentPunto  =	databaseHelper2.queryPunto(regions[i-1][SelectedRegion]);
		    wi = calculateWi(i);
		
		    estimateX += currentPunto.getX()*wi;
		    estimateY += currentPunto.getY()*wi;
				
		}
		  puntoEstimate.setX(estimateX);
		  puntoEstimate.setY(estimateY);
		  
//		  WifiView.setXY(puntoEstimate.getX(), puntoEstimate.getY());
	
		  WifiView.estimateX = puntoEstimate.getX();
		  WifiView.estimateY = puntoEstimate.getY();
		  
		  
		  WifiView.isFind = true;
		  
		  Log.e(Tag+"-locationEstimation", " x = "+puntoEstimate.getX()+"  y = "+puntoEstimate.getY());	  
	}
	
	public double calculateWi(int i){
		double numeratore = 0;
		double denominatore = 0;
	
		int currentId_RP = regions[i-1][SelectedRegion];
		
		numeratore = calculatePi(currentId_RP);
		denominatore += numeratore;
		
		for (int j = 2; j < N +1; j++) {
		   currentId_RP = regions[i-1][SelectedRegion];
		   denominatore += calculatePi(currentId_RP);
		}
		Log.e(Tag, " i = "+i+" denominatore = "+denominatore);
		return numeratore/denominatore;
	}
	
	
	private double calculatePi(int currentId_RP) {
		double pi = 0;
		String currentMac;
		int currentLevel;
		for (int j = 0; j < inlier.size(); j++) {	
				currentMac   = listOfMacs.get(inlier.get(j));
				currentLevel = findMacOnReceivedSignal(currentMac);
				String[] columns = new String[] { columnsLevels[Math.abs(currentLevel)] };
			pi +=	databaseHelper2.queryGetProbability(columns, currentId_RP, currentMac);							
		}
		return pi;
	}
	
	
	private void writeIntoFile() {
		String text = "";
		int n = listOfMacs.size();
		ArrayList<Integer> listId_rpSorted = new ArrayList<Integer>();
		String[] columns = new String[] { "rp" };
		
		try {
			File fileW = new File("/mnt/sdcard/01122014/MacsList.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(fileW));

			for (int nColumn = 0; nColumn < n; nColumn++) {
				// Log.e(TAG+"map",
				// "column = "+column+" :"+listOfMacs.get(column));
				text += "(" + nColumn + ") " + listOfMacs.get(nColumn) + " : ";
			String	currentMac = listOfMacs.get(nColumn);
				
				listId_rpSorted = databaseHelper2.query(columns,
						listOfMacs.get(nColumn),
						"rp");
				
				for (int row = 0; row < listId_rpSorted.size(); row++) {
					
						text += listId_rpSorted.get(row) + " ";
					
				}
				
//				text += currentMac;
				output.write(text);
				text = "\n";
			}

			output.write(text);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
