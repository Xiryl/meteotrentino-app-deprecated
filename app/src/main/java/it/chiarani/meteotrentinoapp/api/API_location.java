package it.chiarani.meteotrentinoapp.api;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.LocationEntity;
import it.chiarani.meteotrentinoapp.repositories.LocationRepository;

public class API_location extends AsyncTask<String, Integer, Integer>{

  // #region private fields
  private final static String   CLASS_TAG        = API_location.class.getSimpleName();
  private final static String   URL_API          = API_endpoint.ENDPOINT_LOCATION;
  private API_location_response delegate;
  private Context               context;
  private AlertDialog           builder;
  private Application           app;
  // #endregion

  /**
   * Main constructor
   * @param mContext app context
   * @param res callback interface for get content async
   */
  public API_location(Application app, Context mContext, API_location_response res) {
    this.app      = app;
    this.context  = mContext;
    this.delegate = res;
  }

  /**
   * Before execute the task create a dialog
   */
  @Override
  protected void onPreExecute() {
    super.onPreExecute();

    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setMessage(context.getResources().getString(R.string.API_locality_alert)).create();
    alert.setCancelable(false);
    builder = alert.show();
  }

  /**
   * After execute the task call the interface callback
   * and send the data
   */
  @Override
  protected void onPostExecute(Integer integer) {
    builder.dismiss();
    delegate.processFinish();
  }

  /**
   * Execute in bakground the task:
   * Create a GET and call a REST API for get all locality and parse the JSON
   */
  @Override
  protected Integer doInBackground(String... s) {
    LocationRepository repository = new LocationRepository(app);
    HttpURLConnection  connection = null;
    BufferedReader     reader     = null;

    try {
      URL url = new URL(URL_API);
      connection = (HttpURLConnection) url.openConnection();
      connection.connect();

      InputStream stream = connection.getInputStream();
      reader = new BufferedReader(new InputStreamReader(stream));

      StringBuffer buffer = new StringBuffer();
      String line = "";
      publishProgress(1);
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      String data   = buffer.toString();
      JSONObject jsonObject = new JSONObject(data);
      JSONArray arr_locality = jsonObject.getJSONArray("localita");

      // Cycle all localities
      for (int i = 0; i < arr_locality.length(); i++) {
        publishProgress(i); // Update %

        String locality    = arr_locality.getJSONObject(i).optString("localita");
        String location    = arr_locality.getJSONObject(i).optString("comune");
        int    altitude    = Integer.parseInt(arr_locality.getJSONObject(i).optString("quota"));
        String latitudine  = arr_locality.getJSONObject(i).optString("latitudine");
        String longitudine = arr_locality.getJSONObject(i).optString("longitudine");

        // + DB
        repository.insert(new LocationEntity(locality, location, altitude, latitudine, longitudine));
      }
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(CLASS_TAG, "Errore Exception: "+  e.toString());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
        Log.e(CLASS_TAG, "Errore IOException: "+  e.toString());
      }
    }
    return 1;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    TextView messageView = builder.findViewById(android.R.id.message);
    messageView.setText(String.format("%s %s/539", context.getResources().getText(R.string.API_locality_alert), values[0] ));
  }
}
