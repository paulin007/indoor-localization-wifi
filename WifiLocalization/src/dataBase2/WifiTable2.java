package dataBase2;

import android.content.Loader.ForceLoadContentObserver;
import android.provider.BaseColumns;

public interface WifiTable2 extends BaseColumns {
	String TABLE_NAME = "wifiNew";
	
	
	
	String X = "x";

	String Y = "y";
	
	String ID_RP = "rp";

	String BSSID = "bssid";
	
    String C90 = "c90_89"; 
    String C88 = "c88_87";
    String C86 = "c86_85";
    String C84 = "c84_83";
    String C82 = "c82_81";
    
    String C80 = "c80_79"; 
    String C78 = "c78_77";
    String C76 = "c76_75";
    String C74 = "c74_73";
    String C72 = "c72_71";
    
    String C70 = "c70_69"; 
    String C68 = "c68_67";
    String C66 = "c66_65";
    String C64 = "c64_63";
    String C62 = "c62_61";
    
    String C60 = "c60_59"; 
    String C58 = "c58_57";
    String C56 = "c56_55";
    String C54 = "c54_53";
    String C52 = "c52_51";
    
    String C50 = "c50_49"; 
    String C48 = "c48_47";
    String C46 = "c46_45";
    String C44 = "c44_43";
    String C42 = "c42_41";

    String C40 = "c40_39"; 
    String C38 = "c38_37";
    String C36 = "c36_35";
    String C34 = "c34_33";
    String C32 = "c32_31";
    
    String C30 = "c30_29"; 
    String C28 = "c28_27";
    String C26 = "c26_25";
    String C24 = "c24_23";
    String C22 = "c22_21";
    
	


	String[] COLUMNS = new String[] { _ID, X, Y,ID_RP, BSSID,
			                           C90,C88,C86,C84,C82,
			                           C80,C78,C76,C74,C72,
			                           C70,C68,C66,C64,C62,
			                           C60,C58,C56,C54,C52,
			                           C50,C48,C46,C44,C42,
			                           C40,C38,C36,C34,C32,
			                           C30,C28,C26,C24,C22};
}
