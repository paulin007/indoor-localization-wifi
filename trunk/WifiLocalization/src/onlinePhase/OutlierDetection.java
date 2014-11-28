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
	private ArrayList<Potenza> s = new ArrayList<Potenza>(); // signal received
																// by the tag
	private String[] columnsLevels = new String[100];

	private int[][] table;
	private Punto[] ri;
	private ArrayList<Integer> inlier = new ArrayList<Integer>();

	private SelectionPoints selectionPoints;

	private ArrayList<Integer> id_si = new ArrayList<Integer>();

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

		// Log.e(TAG + " getNumMac, listOfMacs is sorted ?",
		// listOfMacs.toString());

		return nmacs.size();
	}

	public int getNumberOfRP() {
		return databaseHelper2.getRow2(databaseHelper2.getNumberOfRow())
				.getId_rp();
	}

	public void init() {
		// TODO da mettere in un altro posto
		n = getNumberOfMac();
		m = getNumberOfRP();
		Log.e(TAG, "numberMac = " + n + " numberRP = " + m);

		// wifi.startScan();
		// List<ScanResult> results = wifi.getScanResults();
		// for (ScanResult result : results) {
		// s.add(new Potenza(result.BSSID, result.level));
		// }

		// just for test with database a b c
//		 s.add(new Potenza("c", -87));
//		 s.add(new Potenza("a", -90));

		loadSample();

		ordinaMac();
		map();
		calculateTheCenter();
		algorithmOutlier();

		selectionPoints = new SelectionPoints(databaseHelper2, table, inlier,
				m, s, columnsLevels, listOfMacs);

	}

	// just for test
	public void loadSample() {
		CaricamentoFile file = new CaricamentoFile("/mnt/sdcard/26112014/sample/sample24.txt");
		for (int i = 0; i < file.getRisultato().size(); i++) {
			StringTokenizer st = new StringTokenizer(file.getRisultato().get(i));

			while (st.hasMoreTokens()) {
				Potenza potenza = new Potenza(st.nextToken(),
						Integer.parseInt(st.nextToken()));
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
					Potenza potenzai = s.get(i);
					Potenza potenzaj = s.get(j);
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

				// Log.e(TAG+"map",
				// "select rp where bssid = "+listOfMacs.get(nColumn)+"  orderby : "+columnsLevels[Math.abs(currentLevel)]);
				// Log.e(TAG+"map"+"list idrp sorted ",
				// listId_rpSorted.toString());

				// Log.e(TAG + " if map  column num "+nColumn,
				// listId_rpSorted.toString());

			} else {
				// TODO the mac (ap) currentMac is not visible by the Tag
				// Log.e(TAG + " else map", currentMac
				// + " are not visible by a tag");
				continue;
			}
			for (int n_row = 0; n_row < m; n_row++) {

				if (n_row < listId_rpSorted.size()) {
					table[n_row][nColumn] = listId_rpSorted.get(n_row);
					// Log.e(TAG, " "+listId_rpSorted.get(n_row));
				}

			}

			listId_rpSorted.clear();
		}

		// just for a Log
//		 writeIntoFile();

	}

	private void writeIntoFile() {
		String text = "";
		try {
			File fileW = new File("/mnt/sdcard/24112014/sample/table3.txt");
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

//		Log.e(TAG + "the map table", " the map table");
//		for (int column = 0; column < n; column++) {
//			Log.e(TAG + "map",
//					"column = " + column + " :" + listOfMacs.get(column));
//			for (int row = 0; row < m; row++) {
//				if (row == 0 && (table[row][column] == 0)) {
//					continue;
//				}
//
//				if ((table[row][column]) != 0) {
//
//					Log.e(TAG + "map", "" + table[row][column]);
//				}
//
//			}
//		}
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

	public void calculateTheCenter() {
		// TODO da testare
		ri = new Punto[n];
		Punto punto;
		float x = 0;
		float y = 0;

		for (int nColumn = 0; nColumn < n; nColumn++) {
			for (int n_row = 0; n_row < q; n_row++) {
				if (table[n_row][nColumn] != 0) {
					punto = databaseHelper2.queryPunto(table[n_row][nColumn]);
					// Log.e(TAG+" calculateCenter ",
					// "select x , y distinct where rp = "+table[n_row][nColumn]);
					// Log.e(TAG+" calculateCenter ", punto.toString());
					x += punto.getX();
					y += punto.getY();
				}
			}
			ri[nColumn] = new Punto(x / (float) q, y / (float) q);
			x = 0;
			y = 0;
		}

		// just for test
//		 for (int i = 0; i < ri.length; i++) {
//		 Log.e(TAG+" calculateCenter ",
//		 "for mac : "+listOfMacs.get(i)+"  ri is :"+ri[i].toString());
//		 }

	}

	public void algorithmOutlier() {
		int w;
		float d;
		int count = 0;
		int nn = s.size();

		if ((nn) % 2 == 0) {
			w = nn / 2;
		} else {
			w = (nn + 1) / 2;
		}
       
		Log.e(TAG + " algo", "w = " + w);

		for (int i = 0; i < n; i++) {
			
			// because 
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
//				 Log.e(TAG, " i = "+i+" j = "+j+" d = "+d);
				if (d <= T) {
					count++;
					// Log.e(TAG+"algoritmo	", "i = "+i+" count = "+count);
				}
			}

			if (count >= w) {
				inlier.add(i);
			}
			count = 0;
		}
		Log.e(TAG + "algoritmo idsi :", id_si.toString());
		Log.e(TAG + "algoritmo inlier :", inlier.toString());

	}

	public ArrayList<Potenza> getS() {
		return s;
	}

	public void setup() {
		columnsLevels[98] = "c98_97";
		columnsLevels[97] = "c98_97";
		columnsLevels[96] = "c96_95";
		columnsLevels[95] = "c96_95";
		columnsLevels[94] = "c94_93";
		columnsLevels[93] = "c94_93";
		columnsLevels[92] = "c92_91";
		columnsLevels[91] = "c92_91";
		columnsLevels[90] = "c90_89";
		columnsLevels[89] = "c90_89";
		columnsLevels[88] = "c88_87";
		columnsLevels[87] = "c88_87";
		columnsLevels[86] = "c86_85";
		columnsLevels[85] = "c86_85";
		columnsLevels[84] = "c84_83";
		columnsLevels[83] = "c84_83";
		columnsLevels[82] = "c82_81";
		columnsLevels[81] = "c82_81";

		columnsLevels[80] = "c80_79";
		columnsLevels[79] = "c80_79";
		columnsLevels[78] = "c78_77";
		columnsLevels[77] = "c78_77";
		columnsLevels[76] = "c76_75";
		columnsLevels[75] = "c76_75";
		columnsLevels[74] = "c74_73";
		columnsLevels[73] = "c74_73";
		columnsLevels[72] = "c72_71";
		columnsLevels[71] = "c72_71";

		columnsLevels[70] = "c70_69";
		columnsLevels[69] = "c70_69";
		columnsLevels[68] = "c68_67";
		columnsLevels[67] = "c68_67";
		columnsLevels[66] = "c66_65";
		columnsLevels[65] = "c66_65";
		columnsLevels[64] = "c64_63";
		columnsLevels[63] = "c64_63";
		columnsLevels[62] = "c62_61";
		columnsLevels[61] = "c62_61";

		columnsLevels[60] = "c60_59";
		columnsLevels[59] = "c60_59";
		columnsLevels[58] = "c58_57";
		columnsLevels[57] = "c58_57";
		columnsLevels[56] = "c56_55";
		columnsLevels[55] = "c56_55";
		columnsLevels[54] = "c54_53";
		columnsLevels[53] = "c54_53";
		columnsLevels[52] = "c52_51";
		columnsLevels[51] = "c52_51";

		columnsLevels[50] = "c50_49";
		columnsLevels[49] = "c50_49";
		columnsLevels[48] = "c48_47";
		columnsLevels[47] = "c48_47";
		columnsLevels[46] = "c46_45";
		columnsLevels[45] = "c46_45";
		columnsLevels[44] = "c44_43";
		columnsLevels[43] = "c44_43";
		columnsLevels[42] = "c42_41";
		columnsLevels[41] = "c42_41";

		columnsLevels[40] = "c40_39";
		columnsLevels[39] = "c40_39";
		columnsLevels[38] = "c38_37";
		columnsLevels[37] = "c38_37";
		columnsLevels[36] = "c36_35";
		columnsLevels[35] = "c36_35";
		columnsLevels[34] = "c34_33";
		columnsLevels[33] = "c34_33";
		columnsLevels[32] = "c32_31";
		columnsLevels[31] = "c32_31";

		columnsLevels[30] = "c30_29";
		columnsLevels[29] = "c30_29";
		columnsLevels[28] = "c28_27";
		columnsLevels[27] = "c28_27";
		columnsLevels[26] = "c26_25";
		columnsLevels[25] = "c26_25";
		columnsLevels[24] = "c24_23";
		columnsLevels[23] = "c24_23";
		columnsLevels[22] = "c22_21";
		columnsLevels[21] = "c22_21";

	}

}
