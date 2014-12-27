package onlinePhase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import utility.CaricamentoFile;
import android.net.wifi.WifiManager;
import android.util.Log;
import dataBase2.DatabaseHelper2;

public class OutlierDetection {

	private final static String TAG = OutlierDetection.class.getName();
	private final static int q = 2;
	private final static float T = 250; // set to 5 meter

	private DatabaseHelper2 databaseHelper2;
	private int n;// number of access point
	private int m; // number of references points
	private WifiManager wifi;

	private TreeSet<String> nmacs = new TreeSet<String>();
	private ArrayList<String> listOfMacs = new ArrayList<String>(); // list of
																	// all mac
																	// present
	private ArrayList<Rssi> s = new ArrayList<Rssi>(); // signal received
														// by the tag
	private String[] columnsLevels = new String[100];

	private int[][] table;
	private Point[] ri;
	private ArrayList<Integer> inlier = new ArrayList<Integer>();

	private SelectionPoints selectionPoints;

	private ArrayList<Integer> id_si = new ArrayList<Integer>();

	private String nameLoad = "/mnt/sdcard/01122014/sample2/i17.txt";
	private String nameTabble = "/mnt/sdcard/04122014/tableb6.txt";

	private int[] counts;

	public OutlierDetection(DatabaseHelper2 databaseHelper2, WifiManager wifi) {
		super();
		this.databaseHelper2 = databaseHelper2;
		this.wifi = wifi;
		setup();

	}

	public int getNumberOfMac() {
		int n = databaseHelper2.getNumberOfRow();
		Log.e(TAG, " numRowDataBase2 = " + n);
		for (int i = 1; i < n + 1; i++) {
			nmacs.add(databaseHelper2.getRow2(i).getBssid());
		}

		Iterator iter = nmacs.iterator();

		while (iter.hasNext()) {
			listOfMacs.add((String) iter.next());
		}

		return nmacs.size();

		// just for test

		// listOfMacs = loadMacs();
		// return listOfMacs.size();

	}

	// just for test
	private ArrayList<String> loadMacs() {
		CaricamentoFile file = new CaricamentoFile(
				"/mnt/sdcard/01122014/MacsList.txt");
		return file.getRisultato();
	}

	public int getNumberOfRP() {
		return databaseHelper2.getRow2(databaseHelper2.getNumberOfRow())
				.getId_rp();

	}

	public void init() {

		n = getNumberOfMac();
		counts = new int[n];
		m = getNumberOfRP();
		Log.e(TAG, "numberMac = " + n + " numberRP = " + m);

		s.clear();
		inlier.clear();
		id_si.clear();

//		wifi.startScan();
//		List<ScanResult> results = wifi.getScanResults();
//		for (ScanResult result : results) {
//			if (result.SSID.equalsIgnoreCase("eduroam")
//					|| result.SSID.equalsIgnoreCase("UNIPV-WIFI")) {
//				s.add(new Rssi(result.BSSID, result.level));
//			}
//		}

		// just for test
		loadSample();

		
		
		ordinaMac();
		map();

		// just for a Test
		// writeIntoFile();

		calculateTheCenter();
		algorithmOutlier();

		if (inlier.size() != 0) {
			selectionPoints = new SelectionPoints(databaseHelper2, table,
					inlier, m, s, columnsLevels, listOfMacs);
		} else {
			selectionPoints = new SelectionPoints(databaseHelper2, table,
					id_si, m, s, columnsLevels, listOfMacs);
		}

	}

	// just for test
	public void loadSample() {
		CaricamentoFile file = new CaricamentoFile(nameLoad);
		for (int i = 0; i < file.getRisultato().size(); i++) {
			StringTokenizer st = new StringTokenizer(file.getRisultato().get(i));

			while (st.hasMoreTokens()) {
				Rssi potenza = new Rssi(st.nextToken(), Integer.parseInt(st
						.nextToken()));
				s.add(potenza);
			}

		}

		// Log.e(TAG+" loadSample no order", s.toString());
	}

	public void ordinaMac() {
		int size = s.size();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				String mac1 = s.get(i).getMac();
				String mac2 = s.get(j).getMac();

				if (mac2.compareTo(mac1) < 0) {
					Rssi potenzai = s.get(i);
					Rssi potenzaj = s.get(j);
					s.set(i, potenzaj);
					s.set(j, potenzai);
				}
			}
		}

		// Log.e(TAG + "  ordinaMac	", s.toString());
	}

	public void map() {
		table = new int[m][n];

		ArrayList<Integer> listId_rpSorted = new ArrayList<Integer>();

		int currentLevel;
		String currentMac;
		String[] columns = new String[] { "rp" };

		for (int nColumn = 0; nColumn < n; nColumn++) {
			currentMac = listOfMacs.get(nColumn);
			currentLevel = findMacOnReceivedSignal(currentMac);
			if (currentLevel != 0) {

				// Log.e(TAG + "  map ", " the tag see the mac id= " + nColumn
				// + " : " + currentMac + " with level = " + currentLevel);

				id_si.add(nColumn);
				listId_rpSorted = databaseHelper2.query(columns,
						listOfMacs.get(nColumn),
						columnsLevels[Math.abs(currentLevel)]);

			} else {
				// the mac (ap) currentMac is not visible by the Tag
				
				continue;
			}
			for (int n_row = 0; n_row < m; n_row++) {

				if (n_row < listId_rpSorted.size()) {
					table[n_row][nColumn] = listId_rpSorted.get(n_row);
					
				}
			}

			listId_rpSorted.clear();
		}
	}

	// just for a test 
	private void writeIntoFile() {
		String text = "";
		try {
			File fileW = new File(nameTabble);
			BufferedWriter output = new BufferedWriter(new FileWriter(fileW));

			for (int column = 0; column < n; column++) {
				// Log.e(TAG+"map",
				// "column = "+column+" :"+listOfMacs.get(column));
				text += "(" + column + ") " + listOfMacs.get(column) + " : ";
				for (int row = 0; row < m; row++) {
					if (row == 0 && (table[row][column] == 0)) {
						continue;
					}

					if ((table[row][column]) != 0) {
						text += table[row][column] + " ";
					}
				}
				output.write(text);
				text = "\n";
			}

			output.write(text);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method find if the online macs received by a Tag exist on the
	 * database
	 * 
	 * @param currentmac
	 * @return the level of the mac if there exist or 0 if not.
	 * 
	 *         we can see that we this method is not necessary to sort the Macs
	 *         received by a tag but we do it to optimize this method
	 */
	private int findMacOnReceivedSignal(String currentmac) {

		for (int i = 0; i < s.size(); i++) {
			if (currentmac.equals(s.get(i).getMac())) {
				return s.get(i).getLevel();
			}
		}

		return 0;
	}

	public void calculateTheCenter() {
		
		ri = new Point[n];
		Point punto;
		float x = 0;
		float y = 0;

		for (int nColumn = 0; nColumn < n; nColumn++) {
			for (int n_row = 0; n_row < q; n_row++) {
				if (table[n_row][nColumn] != 0) {
					punto = databaseHelper2.queryPunto(table[n_row][nColumn]);
					x += punto.getX();
					y += punto.getY();
				}
			}
			ri[nColumn] = new Point(x / (float) q, y / (float) q);
			x = 0;
			y = 0;
		}

	}

	public void algorithmOutlier() {
		int w;
		float d;
		int count = 0;
		int nn = id_si.size();

		if ((nn) % 2 == 0) {
			w = nn / 2;
		} else {
			w = (nn + 1) / 2;
		}
	
		Log.e(TAG + " algo",
				"size s = " + s.size() + " size id_si = " + id_si.size());
		Log.e(TAG + " algo", "w = " + w);
		Log.e(TAG + " algo", "T = " + T);

		for (int i = 0; i < n; i++) {

			if (ri[i].getX() == 0 && ri[i].getY() == 0) {
				continue;
			}

			for (int j = 0; j < n; j++) {
				if (j == i) {
					continue;
				}

				if (ri[j].getX() == 0 && ri[j].getY() == 0) {
					continue;
				}

				d = (float) Math
						.sqrt(((ri[i].getX() - ri[j].getX()) * (ri[i].getX() - ri[j]
								.getX()))
								+ ((ri[i].getY() - ri[j].getY()) * (ri[i]
										.getY() - ri[j].getY())));
				// Log.e(TAG, " i = "+i+" j = "+j+" d = "+d);
				if (d <= T) {
					count++;

				}
			}
			Log.e(TAG + "algoritmo	", "i = " + i + " count = " + count);

			counts[i] = count;

			if (count >= w) {
				inlier.add(i);
			}
			count = 0;
		}

		while (inlier.size() == 0) {
			w -= 1;
			for (int i = 0; i < n; i++) {
				if (counts[i] > w) {
					inlier.add(i);
				}

			}
		}

		Log.e(TAG + " algoritmo idsi :", id_si.toString());
		Log.e(TAG + " algoritmo inlier :", inlier.toString());

	}

	public ArrayList<Rssi> getS() {
		return s;
	}

	public void setup() {
		for (int i = 98; i >= 21 ; i-=2) {
			columnsLevels[i] = "c"+i+"_"+(i-1);
			columnsLevels[i-1] = "c"+i+"_"+(i-1);
		}
	}

}