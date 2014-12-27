package onlinePhase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import wifi.WifiView;
import android.util.Log;
import dataBase2.DatabaseHelper2;

public class SelectionPoints {
	private static final String Tag = SelectionPoints.class.getName();
	private static final int N = 4;
	// private static final int NumRegions = 10;
	private static final int row = 4;
	private static final int NUMBER_REGIONS = 29; // 19

	private DatabaseHelper2 databaseHelper2;
	private int[][] table;
	private ArrayList<Integer> inlier;
	private int m;
	private ArrayList<Rssi> s;
	private String[] columnsLevels;
	private ArrayList<String> listOfMacs;

	private int nPrimo;
	private double[] sops = new double[NUMBER_REGIONS];

	private int[][] regions = new int[row][NUMBER_REGIONS];

	private int SelectedRegion = 0;

	private TreeSet<Integer> rp_Involved = new TreeSet<Integer>();

	private TreeSet<Integer> regionsInvolved = new TreeSet<Integer>();

	public SelectionPoints(DatabaseHelper2 databaseHelper2, int[][] table,
			ArrayList<Integer> inlier, int m, ArrayList<Rssi> s,
			String[] columnsLevels, ArrayList<String> listOfMacs) {
		super();
		this.databaseHelper2 = databaseHelper2;
		this.table = table;
		this.inlier = inlier;
		this.m = m;
		this.s = s;
		this.columnsLevels = columnsLevels;
		this.listOfMacs = listOfMacs;
		nPrimo = inlier.size();

		fillRegion();
		findRpInvolved();
		findRegionInvolved();
		sumOfPropability();
		findMaxSop();
		locationEstimation();
		// writeIntoFile();
	}

	public void fillRegion() {
		int offset = 0;
		int count = 1;

		for (int nColumn = 0; nColumn < NUMBER_REGIONS; nColumn++) {
			for (int n_row = 0; n_row < row; n_row++) {
				regions[n_row][nColumn] = count - offset;

				if ((count - offset) == m) {
					break;
				}
				count++;
			}
			offset = 2 * (nColumn + 1);
		}
	}

	public void findRpInvolved() {
		for (int i = 0; i < inlier.size(); i++) {
			for (int j = 0; j < 4; j++) {
				rp_Involved.add(table[j][inlier.get(i)]);
			}
		}
		Log.e(Tag + " rpIn ", rp_Involved.toString());
	}

	public void findRegionInvolved() {
		for (int nColumn = 0; nColumn < NUMBER_REGIONS; nColumn++) {
			for (int n_row = 0; n_row < row; n_row++) {
				if (rp_Involved.contains(regions[n_row][nColumn])) {
					regionsInvolved.add(nColumn);
				}
			}

		}
		Log.e(Tag + " regionsIn ", regionsInvolved.toString());
	}

	public void sumOfPropability() {
		int currentId_RP;
		String currentMac;
		int currentLevel;
		double sop = 0;

		for (int nColumn = 0; nColumn < NUMBER_REGIONS; nColumn++) {
			if(!regionsInvolved.contains(nColumn)){
				continue;
			}
			
			for (int nRow = 0; nRow < N; nRow++) {
				
				currentId_RP = regions[nRow][nColumn];
				
				for (int i = 0; i < inlier.size(); i++) {
					currentMac = listOfMacs.get(inlier.get(i));
					currentLevel = findMacOnReceivedSignal(currentMac);
					String[] columns = new String[] { columnsLevels[Math
							.abs(currentLevel)] };
					sop += databaseHelper2.queryGetProbability(columns,
							currentId_RP, currentMac);
				}

			}
			sops[nColumn] = sop;
			sop = 0;

		}

	}

	public void findMaxSop() {
		double max = sops[0];

		for (int i = 0; i < sops.length; i++) {
//			Log.e(Tag + " findMaxSop", "sop[" + i + "] = " + sops[i]);
			if (sops[i] > max) {
				max = sops[i];
				SelectedRegion = i;
			}
		}

		Log.e(Tag, "region : " + SelectedRegion);
	}

	private int findMacOnReceivedSignal(String currentmac) {
	
		for (int i = 0; i < s.size(); i++) {	
			if (currentmac.equals(s.get(i).getMac())) {
				return s.get(i).getLevel();
			}
		}
		return 0;
	}

	public void locationEstimation() {

		Point currentPunto;
		double denominatore = 0;
		double wi;
		Point puntoEstimate = new Point(0, 0);
		float estimateX = 0;
		float estimateY = 0;

		denominatore = denominatoreWi();
		for (int i = 1; i < N + 1; i++) {
			currentPunto = databaseHelper2
					.queryPunto(regions[i - 1][SelectedRegion]);

			wi = numeratoreWi(i) / denominatore;

			estimateX += currentPunto.getX() * wi;
			estimateY += currentPunto.getY() * wi;

		}
		puntoEstimate.setX(estimateX);
		puntoEstimate.setY(estimateY);

		WifiView.estimateX = puntoEstimate.getX();
		WifiView.estimateY = puntoEstimate.getY();

		WifiView.isFind = true;
		Log.e(Tag + "-locationEstimation", " x = " + puntoEstimate.getX()
				+ "  y = " + puntoEstimate.getY());
	}

	public double numeratoreWi(int i) {
		double numeratore = 0;
		int currentId_RP = regions[i - 1][SelectedRegion];

		numeratore = calculatePi(currentId_RP);
		return numeratore;
	}

	private double denominatoreWi() {
		int currentId_RP;
		double denominatore = 0;
		for (int j = 1; j < N + 1; j++) {
			currentId_RP = regions[j - 1][SelectedRegion];
			denominatore += calculatePi(currentId_RP);
		}
		return denominatore;
	}

	private double calculatePi(int currentId_RP) {
		double pi = 0;
		String currentMac;
		int currentLevel;
		for (int j = 0; j < inlier.size(); j++) {
			currentMac = listOfMacs.get(inlier.get(j));
			currentLevel = findMacOnReceivedSignal(currentMac);
			String[] columns = new String[] { columnsLevels[Math
					.abs(currentLevel)] };
			pi += databaseHelper2.queryGetProbability(columns, currentId_RP,
					currentMac);
		}
		return pi;
	}

	// just for test
	private void writeIntoFile() {
		String text = "";
		int n = listOfMacs.size();
		ArrayList<Integer> listId_rpSorted = new ArrayList<Integer>();
		String[] columns = new String[] { "rp" };

		try {
			File fileW = new File("/mnt/sdcard/01122014/MacsListCount.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(fileW));

			for (int nColumn = 0; nColumn < n; nColumn++) {
				// Log.e(TAG+"map",
				// "column = "+column+" :"+listOfMacs.get(column));
				text +=  nColumn +" ";
				String currentMac = listOfMacs.get(nColumn);

				listId_rpSorted = databaseHelper2.query(columns,
						listOfMacs.get(nColumn), "rp");

				text += listId_rpSorted.size();
				
				// text += currentMac;
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
