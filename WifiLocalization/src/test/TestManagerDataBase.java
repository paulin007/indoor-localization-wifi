package test;

import java.util.ArrayList;

import model.ManagerDataBase;
import model.NewRowDatabase;
import model.RowDatabase;

public class TestManagerDataBase {
	
	public static void main(String[] args) {
		
		
		ArrayList<RowDatabase> rowsCurrentRP = new ArrayList<RowDatabase>();
		ArrayList<NewRowDatabase> rowsRPforNewDataBase = new ArrayList<NewRowDatabase>();
		int i = 1;
		ManagerDataBase managerDataBase = new ManagerDataBase();
		
		rowsCurrentRP.add(new RowDatabase(i++, 0, 0, 1, "uni",      "a", -90));
		rowsCurrentRP.add(new RowDatabase(i++, 0, 0, 1, "ba",       "c", -87));
		rowsCurrentRP.add(new RowDatabase(i++, 0, 0, 1, "ujkfhsni", "a", -86));
		rowsCurrentRP.add(new RowDatabase(i++, 0, 0, 1, "snm",      "a", -90));
		rowsCurrentRP.add(new RowDatabase(i++, 0, 0, 1, "usnm",     "e", -81));
		rowsCurrentRP.add(new RowDatabase(i++, 0, 0, 1, "usdi",     "a", -90));
		
		rowsCurrentRP.add(new RowDatabase(i++, 3, 3, 2, "uni",      "c", -88));
		rowsCurrentRP.add(new RowDatabase(i++, 3, 3, 2, "ba",       "n", -84));
		rowsCurrentRP.add(new RowDatabase(i++, 3, 4, 3, "ujkfhsni", "adb", -80));
		rowsCurrentRP.add(new RowDatabase(i++, 3, 4, 3, "snm",      "aaa", -43));
		rowsCurrentRP.add(new RowDatabase(i++, 4, 4, 4, "usnm",     "a", -60));
		rowsCurrentRP.add(new RowDatabase(i++, 4, 4, 4, "usdi",     "z", -77));
		rowsCurrentRP.add(new RowDatabase(i++, 4, 4, 4, "usnm",     "a", -89));
		rowsCurrentRP.add(new RowDatabase(i++, 5, 5, 5, "usdi",     "a", -90));
		
		System.out.println("TEST MANAGER -- ORDINA ");
        System.out.println(managerDataBase.ordina(rowsCurrentRP).toString());	
        
		
	}

}
