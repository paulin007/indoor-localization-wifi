package model;

import java.util.ArrayList;

import android.util.Log;

public class ManagerDataBase {

	String TAG = "ManagerDataBase - ";
	private ArrayList<RowDatabase> elements_Of_RP = new ArrayList<RowDatabase>();
	private ArrayList<NewRowDatabase> macs_RP = new ArrayList<NewRowDatabase>();

	private final static int MAX = 100;
	private int[] levels = new int[MAX];

	private int id_rp = 1;

	int trainingData = 0;
	
	

	public ManagerDataBase() {
		super();
	}

	public ArrayList<RowDatabase> getElements_Of_RP() {
		return elements_Of_RP;
	}

	public void setElements_Of_RP(ArrayList<RowDatabase> elements_Of_RP) {
		macs_RP.clear();
		clean();
		this.elements_Of_RP = elements_Of_RP;
		ordina();
		manipolation();
	}

	private void ordina() {
		int size = elements_Of_RP.size();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				String mac1 = elements_Of_RP.get(i).getBssid();
				String mac2 = elements_Of_RP.get(j).getBssid();
				
				if (mac2.compareTo(mac1) < 0) {

					RowDatabase rowi = elements_Of_RP.get(i);
					RowDatabase rowj = elements_Of_RP.get(j);
					elements_Of_RP.set(i, rowj);
					elements_Of_RP.set(j, rowi);
				}
			}
		}

//		 Log.e("manager.ordina", elements_Of_RP.toString());
	}

	public void manipolation() {
		String currentMac = "";

		if (elements_Of_RP.size() != 0) {
			currentMac = elements_Of_RP.get(0).getBssid();
			trainingData = elements_Of_RP.size();
		}

		for (int i = 0; i < elements_Of_RP.size(); i++) {

			if (currentMac.equalsIgnoreCase(elements_Of_RP.get(i).getBssid())) {
				levels[Math.abs(elements_Of_RP.get(i).getLevel())]++;

		
				// caso il cui nell'arraylist c'è solo un indirizzo mac
				if (i == elements_Of_RP.size() - 1) {
					addToArraylist(levels, currentMac, i);
				}

			} else {
				addToArraylist(levels, currentMac, i);

				currentMac = elements_Of_RP.get(i).getBssid();

				clean();

				levels[Math.abs(elements_Of_RP.get(i).getLevel())]++;

			
				// caso in cui ci sono piu indirizzi mac nell'arraylist ma
				// l'ultimo indirizzo è unico
				if (i == elements_Of_RP.size() - 1) {
					addToArraylist(levels, currentMac, i);
				}
			}
		}
	}

	private void addToArraylist(int[] levels, String currentMac, int index) {

	
		//
//		 Log.e(TAG+"x= "+elements_Of_RP.get(index).getX()+" y= "+elements_Of_RP.get(index).getY(),
//		 "  trainingdata = "+trainingData);
		//
//		 Log.e(TAG+" macs_RP",
//		 macs_RP.toString()+"  "+(levels[90]+levels[89])/trainingData);
		

		if (trainingData != 0) {
			macs_RP.add(new NewRowDatabase(elements_Of_RP.get(index).getId(),elements_Of_RP.get(index).getX(),
					elements_Of_RP.get(index).getY(), id_rp, currentMac,
					(double) (levels[90] + levels[89]) / trainingData,
					(double) (levels[88] + levels[87]) / trainingData,
					(double) (levels[86] + levels[85]) / trainingData,
					(double) (levels[84] + levels[83]) / trainingData,
					(double) (levels[82] + levels[81]) / trainingData,
					(double) (levels[80] + levels[79]) / trainingData,
					(double) (levels[78] + levels[77]) / trainingData,
					(double) (levels[76] + levels[75]) / trainingData,
					(double) (levels[74] + levels[73]) / trainingData,
					(double) (levels[72] + levels[71]) / trainingData,
					(double) (levels[70] + levels[69]) / trainingData,
					(double) (levels[68] + levels[67]) / trainingData,
					(double) (levels[66] + levels[65]) / trainingData,
					(double) (levels[64] + levels[63]) / trainingData,
					(double) (levels[62] + levels[61]) / trainingData,
					(double) (levels[60] + levels[59]) / trainingData,
					(double) (levels[58] + levels[57]) / trainingData,
					(double) (levels[56] + levels[55]) / trainingData,
					(double) (levels[54] + levels[53]) / trainingData,
					(double) (levels[52] + levels[51]) / trainingData,
					(double) (levels[50] + levels[49]) / trainingData,
					(double) (levels[48] + levels[47]) / trainingData,
					(double) (levels[46] + levels[45]) / trainingData,
					(double) (levels[44] + levels[43]) / trainingData,
					(double) (levels[42] + levels[41]) / trainingData,
					(double) (levels[40] + levels[39]) / trainingData,
					(double) (levels[38] + levels[37]) / trainingData,
					(double) (levels[36] + levels[35]) / trainingData,
					(double) (levels[34] + levels[33]) / trainingData,
					(double) (levels[32] + levels[31]) / trainingData,
					(double) (levels[30] + levels[29]) / trainingData,
					(double) (levels[28] + levels[27]) / trainingData,
					(double) (levels[26] + levels[25]) / trainingData,
					(double) (levels[24] + levels[23]) / trainingData,
					(double) (levels[22] + levels[21]) / trainingData));
		}
	
		clean();

	}

	public void clean() {
		for (int i = 0; i < levels.length; i++) {
			levels[i] = 0;
		}
	}

	public ArrayList<NewRowDatabase> getMacs_RP() {
		return macs_RP;
	}

}
