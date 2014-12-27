package button;

import java.util.ArrayList;

import model.ManagerDataBase;
import model.NewRowDatabase;
import model.RowDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import dataBase.DatabaseHelper;
import dataBase2.DatabaseHelper2;

/**
 * this class elaborate the data from the old database to make the new one with
 * the probability for every possibility RSS for each reference point
 * 
 * @author paulintchonin
 * 
 */

public class ButtonFixListener implements OnClickListener {

	String Tag = ButtonFixListener.class.getName()+" - ";
	NewRowDatabase row2 = new NewRowDatabase();
	ManagerDataBase manager = new ManagerDataBase();
	RowDatabase row = new RowDatabase();
	DatabaseHelper databaseHelper;
	DatabaseHelper2 databaseHelper2;
	int id_rp = 1;
	ProgressBar mProgressBar;
	ImageView im;

	public ButtonFixListener(DatabaseHelper databaseHelper,
			DatabaseHelper2 databaseHelper2, ProgressBar progressBar) {
		super();
		this.databaseHelper = databaseHelper;
		this.databaseHelper2 = databaseHelper2;
		this.mProgressBar = progressBar;

	}

	@Override
	public void onClick(View v) {
		new FixTask().execute();
	}

	/**
	 * put data on new database for each RP
	 * 
	 * @param macs_rp
	 *            : list di tutti gli AP visti in un RP
	 */

	public void putDataOnNewDataBase(ArrayList<NewRowDatabase> macs_rp) {

		for (int i = 0; i < macs_rp.size(); i++) {
			row2 = macs_rp.get(i);
			databaseHelper2.inserisciDatiWifi(row2.getX(), row2.getY(), id_rp,
					row2.getSsid(), row2.getBssid(), row2.getC98(),
					row2.getC96(), row2.getC94(), row2.getC92(), row2.getC90(),
					row2.getC88(), row2.getC86(), row2.getC84(), row2.getC82(),
					row2.getC80(), row2.getC78(), row2.getC76(), row2.getC74(),
					row2.getC72(), row2.getC70(), row2.getC68(), row2.getC66(),
					row2.getC64(), row2.getC62(), row2.getC60(), row2.getC58(),
					row2.getC56(), row2.getC54(), row2.getC52(), row2.getC50(),
					row2.getC48(), row2.getC46(), row2.getC44(), row2.getC42(),
					row2.getC40(), row2.getC38(), row2.getC36(), row2.getC34(),
					row2.getC32(), row2.getC30(), row2.getC28(), row2.getC26(),
					row2.getC24(), row2.getC22());

		}

		id_rp++;

	}

	/**
	 * l'obbietivo di questa classe è quella di ciclare su tutte le righe del
	 * vecchio database per produre uno nuovo. per fare ciò viene calcolato le
	 * probabilità di ciascun RP dal vecchio database poi viene salvato questi
	 * valori in un nuovo database.
	 * 
	 * @author paulintchonin
	 * 
	 */

	class FixTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			int numberRow = databaseHelper.getNumberOfRow();
			float currentX = -1;
			float currentY = -1;

			if (numberRow != 0) {
				currentX = databaseHelper.getRow(1).getX();
				currentY = databaseHelper.getRow(1).getY();
			}

			ArrayList<RowDatabase> rowsCurrentRP = new ArrayList<RowDatabase>();

			for (int i = 1; i < numberRow + 1; i++) {
				row = databaseHelper.getRow(i);

				if (row.getX() != currentX || row.getY() != currentY) {

					manager.setRowsCurrentRP(rowsCurrentRP);

					putDataOnNewDataBase(manager.getRowsRPforNewDataBase());

					rowsCurrentRP.clear();
					rowsCurrentRP.add(row);
					currentX = row.getX();
					currentY = row.getY();

					if (i == numberRow) {
						manager.setRowsCurrentRP(rowsCurrentRP);
						putDataOnNewDataBase(manager.getRowsRPforNewDataBase());
					}

				} else {
					rowsCurrentRP.add(row);
					if (i == numberRow) {
						manager.setRowsCurrentRP(rowsCurrentRP);
						putDataOnNewDataBase(manager.getRowsRPforNewDataBase());
					}
				}

				mProgressBar
						.setProgress((int) (((float) i / (float) numberRow) * mProgressBar
								.getMax()));
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

		@Override
		protected void onPostExecute(Void result) {
			mProgressBar.setVisibility(ProgressBar.INVISIBLE);
		}
	}
}
