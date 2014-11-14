package model;

import java.util.ArrayList;

import android.util.Log;

public class ManagerDataBase {
	
	private ArrayList<RowDatabase> elements_Of_RP = new ArrayList<RowDatabase>();
	private ArrayList<NewRowDatabase> macs_RP = new ArrayList<NewRowDatabase>();
	private ArrayList<Double> deviations = new ArrayList<Double>();
	
	
	private double varianza;
	
	private int id_rp = 1;
	
	
	

	public ManagerDataBase() {
		super();
	}

	public ArrayList<RowDatabase> getElements_Of_RP() {
		return elements_Of_RP;
	}

	public void setElements_Of_RP(ArrayList<RowDatabase> elements_Of_RP) {

		macs_RP.clear();
		deviations.clear();
		this.elements_Of_RP = elements_Of_RP;
		ordina();
		manipolation();
	}

	private void ordina() {
	//TODO da provare 
		
		int size = elements_Of_RP.size();
		for (int i = 0; i < size; i++) {
			for (int j = i+1; j < size; j++) {
				
				String mac1 = elements_Of_RP.get(i).getBssid();
				String mac2 = elements_Of_RP.get(j).getBssid();
				
				if(mac2.compareTo(mac1)<0){
					
					RowDatabase rowi=elements_Of_RP.get(i);
					RowDatabase rowj=elements_Of_RP.get(j);
								
					elements_Of_RP.set(i, rowj);
					elements_Of_RP.set(j, rowi);
				}
				
			}
		}
		Log.e("ordina", ""+size);
		Log.e("manager.ordina", elements_Of_RP.toString());
	}

	public void manipolation() {
        int sum = 0;
        int count=0;
        String currentMac="";
       
        
        if(elements_Of_RP.size()!=0){
        	currentMac = elements_Of_RP.get(0).getBssid();
        }
        
        for (int i = 0; i < elements_Of_RP.size(); i++) {
        	
        	if(currentMac.equalsIgnoreCase(elements_Of_RP.get(i).getBssid())){
        		sum += elements_Of_RP.get(i).getLevel();
        		count++;
        		deviations.add((double)elements_Of_RP.get(i).getLevel());
        		//TODO duplicato
        		if(i==elements_Of_RP.size()-1){
        			addToArraylist(sum, count, currentMac, i);
        		}
        		
        	}else{
        		addToArraylist(sum, count, currentMac, i);
        		
        		currentMac = elements_Of_RP.get(i).getBssid();
        		sum=0;
        		count=0;
        		deviations.clear();
        		sum += elements_Of_RP.get(i).getLevel();
        		count++;
        		deviations.add((double)elements_Of_RP.get(i).getLevel());
        		
        		if(i==elements_Of_RP.size()-1){
        			//TODO codice dublicato 
        			deviations.add((double)elements_Of_RP.get(i).getLevel());
        			
        			addToArraylist(sum, count, currentMac, i);
        		}
        		
        	}
			
		}
        
      
	}

	private void addToArraylist(int sum, int count, String currentMac, int i) {
		
		Log.e(currentMac, deviations.toString());
		macs_RP.add(new NewRowDatabase(elements_Of_RP.get(i).getX(), 
				elements_Of_RP.get(i).getY(), id_rp, currentMac, sum/count,
				getStandarDeviation(deviations, sum/count)));
	}
	
	public double getStandarDeviation(ArrayList<Double> deviations, double mean){
	//TODO test  OK
		float sum = 0;
		
		for (int i = 0; i < deviations.size(); i++) {
			
			deviations.set(i, (deviations.get(i) - mean)*(deviations.get(i) - mean));
			Log.e("step 2", deviations.toString());
		    sum+=deviations.get(i);
		    Log.e("sum =", ""+sum);
		}
		
		return Math.sqrt(sum/deviations.size());
	}

	public double getVarianza() {
		return varianza;
	}
	
	public ArrayList<NewRowDatabase> getMacs_RP() {
		
		return macs_RP;
	}

}
