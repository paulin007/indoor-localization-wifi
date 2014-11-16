package wifi;

import java.util.ArrayList;
import model.ManagerDataBase;
import model.NewRowDatabase;
import model.RowDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import dataBase.DatabaseHelper;
import dataBase2.DatabaseHelper2;

public class ButtonFixListener implements OnClickListener {

	String Tag="ButtonFixListener - ";
	
	NewRowDatabase row2 = new NewRowDatabase();

	ManagerDataBase manager = new ManagerDataBase();

	RowDatabase row = new RowDatabase();

	DatabaseHelper databaseHelper;

	DatabaseHelper2 databaseHelper2;

	int id_ir = 1;

	ProgressBar mProgressBar;

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

	public void putDataOnNewDataBase(ArrayList<NewRowDatabase> macs_rp) {

		for (int i = 0; i < macs_rp.size(); i++) {
			row2 = macs_rp.get(i);
			databaseHelper2.inserisciDatiWifi(row2.getX(), row2.getY(), id_ir,
					row2.getBssid(), 
					row2.getC90(),row2.getC88(),row2.getC86(),row2.getC84(),row2.getC82(),
					row2.getC80(),row2.getC78(),row2.getC76(),row2.getC74(),row2.getC72(),
					row2.getC70(),row2.getC68(),row2.getC66(),row2.getC64(),row2.getC62(),
					row2.getC60(),row2.getC58(),row2.getC56(),row2.getC54(),row2.getC52(),
					row2.getC50(),row2.getC48(),row2.getC46(),row2.getC44(),row2.getC42(),
					row2.getC40(),row2.getC38(),row2.getC36(),row2.getC34(),row2.getC32(),
					row2.getC30(),row2.getC28(),row2.getC26(),row2.getC24(),row2.getC22()  );
		}
		id_ir++;

	}

	class FixTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {

			int numberRow = databaseHelper.getNumberOfRow();
			float currentX = -1;
			float currentY = -1;

			if (numberRow != 0) {
				Log.e(Tag+"numberRow = ", "" + numberRow);
				currentX = databaseHelper.getRow(1).getX();
				currentY = databaseHelper.getRow(1).getY();
			}

			ArrayList<RowDatabase> rowsCurrentRP = new ArrayList<RowDatabase>();

			for (int i = 1; i < numberRow + 1; i++) {
				row = databaseHelper.getRow(i);
				// Log.e("i = ", ""+i);

				if (row.getX() != currentX || row.getY() != currentY) {
					// Log.e("fix get ",
					// "getx = "+row.getX()+" gety= "+row.getY());
					// Log.e("fix current", "x = "+currentX+" y= "+currentY);

					manager.setElements_Of_RP(rowsCurrentRP);

					putDataOnNewDataBase(manager.getMacs_RP());

					rowsCurrentRP.clear();
					rowsCurrentRP.add(row);
					currentX = row.getX();
					currentY = row.getY();

					if (i == numberRow) {

						manager.setElements_Of_RP(rowsCurrentRP);
						putDataOnNewDataBase(manager.getMacs_RP());
					}

				} else {
					// Log.e("fix get else ",
					// "getx = "+row.getX()+" gety= "+row.getY());
					// Log.e("fix current else ",
					// "x = "+currentX+" y= "+currentY);
					rowsCurrentRP.add(row);
					if (i == numberRow) {
						manager.setElements_Of_RP(rowsCurrentRP);
						putDataOnNewDataBase(manager.getMacs_RP());
					}

				}
				publishProgress(i * numberRow);
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Integer result) {
			mProgressBar.setVisibility(ProgressBar.INVISIBLE);
		}
	}

}
