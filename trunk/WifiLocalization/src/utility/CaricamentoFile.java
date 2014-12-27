package utility;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
 
public class CaricamentoFile {
 
    private ArrayList<String> risultato = new ArrayList<String>();
    private BufferedReader reader;
    private String nomeFile; 
 
    public CaricamentoFile(String nomeFile) {
		super();
		this.nomeFile = nomeFile;
		setSorgente(nomeFile);
	}


	public void setSorgente(String nomeFile) {
        risultato.clear();
        try {
            reader = new BufferedReader(new FileReader(new File(nomeFile)));
            String line=reader.readLine();
            while (line!=null){
                if(!line.isEmpty()){
                risultato.add(line);
                line=reader.readLine();
                }else{
                    line=reader.readLine();
                }
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
                 
        }
 
   
    public ArrayList<String> getRisultato() {
         
        return risultato;
    }
 
}