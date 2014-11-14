package wifi;

import java.util.ArrayList;

import dataBase.DatabaseHelper;
import dataBase2.DatabaseHelper2;

import model.ManagerDataBase;
import model.NewRowDatabase;
import model.RowDatabase;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonFixListener implements OnClickListener{

    NewRowDatabase row2 = new NewRowDatabase();
	
	ManagerDataBase manager = new ManagerDataBase();
	
	RowDatabase row = new RowDatabase();
	
	DatabaseHelper databaseHelper ;
	
	DatabaseHelper2 databaseHelper2;
	
	int id_ir = 1;
	
	public ButtonFixListener(DatabaseHelper databaseHelper,
			DatabaseHelper2 databaseHelper2) {
		super();
		this.databaseHelper = databaseHelper;
		this.databaseHelper2 = databaseHelper2;
	}

	@Override
	public void onClick(View v) {
		int numberRow = databaseHelper.getNumberOfRow();
		float currentX=-1;
		float currentY=-1;
		
		
		if(numberRow!=0){
		     currentX=databaseHelper.getRow(1).getX();
			 currentY=databaseHelper.getRow(1).getY();    	
		}
		
		
		ArrayList<RowDatabase> rowsCurrentRP = new ArrayList<RowDatabase>();
		
		for (int i = 1; i < numberRow + 1; i++) {
			row = databaseHelper.getRow(i);
			
			if(row.getX()!=currentX && row.getY()!=currentY){
				manager.setElements_Of_RP(rowsCurrentRP);
					
				putDataOnNewDataBase(manager.getMacs_RP());			
				
				
				rowsCurrentRP.clear();
				rowsCurrentRP.add(row);
				currentX = row.getX();
				currentY = row.getY();				
				
				if(i==numberRow){	
					
					manager.setElements_Of_RP(rowsCurrentRP);
					putDataOnNewDataBase(manager.getMacs_RP());	
				}
				
			}else{
				rowsCurrentRP.add(row);
				
			}	
		}
		
	}
	
  public void putDataOnNewDataBase (ArrayList<NewRowDatabase> macs_rp){
		
		
		for (int i = 0; i < macs_rp.size(); i++) {
			row2 = macs_rp.get(i);
			databaseHelper2.inserisciDatiWifi(row2.getX(), row2.getY(), id_ir,
					row2.getBssid(), row2.getMedia(), row2.getVarianza());
		}
		id_ir++;
		
	}

}
