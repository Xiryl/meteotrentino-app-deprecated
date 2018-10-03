package it.chiarani.meteotrentinoapp.api;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;
import it.chiarani.meteotrentinoapp.repositories.LocalityRepository;

public class API_protezioneCivileAvvisiAllerte extends AsyncTask<String, Integer, Integer> {

  // #region private fields
  private final static String API_LOCALITY_TAG = "API_LOCALITY";
  private final static String URL_API = API_endpoint.ENDPOINT_LOCALITY;
  private Context mContext;
  private AlertDialog builder;
  private Application _app;
  private API_protezioneCivileAvvisiAllerte_response delegate = null;
  private ArrayList<String> data = new ArrayList<>();
  // #endregion

  /**
   * Main constructor
   * @param mContext app context
   * @param res callback interface for get content async
   */
  public API_protezioneCivileAvvisiAllerte(Application app, Context mContext, API_protezioneCivileAvvisiAllerte_response res) {
    this._app     = app;
    this.mContext = mContext;
    this.delegate = res;
  }

  /**
   * Before execute the task create a dialog
   */
  @Override
  protected void onPreExecute() {
    super.onPreExecute();

    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
    alert.setMessage("Ottengo le allerte..").create();
    alert.setCancelable(false);
    builder = alert.show();
  }

  /**
   * After execute the task we call the interface callback
   * and send the data
   */
  @Override
  protected void onPostExecute(Integer integer) {
    builder.dismiss();
    delegate.processFinish(data);
  }

  /**
   * Execute in bakground the task:
   * Create a GET and call a REST API for get all locality and parse the JSON
   */
  @Override
  protected Integer doInBackground(String... s) {
    try {
      Document doc = Jsoup.connect("http://avvisi.protezionecivile.tn.it/elencoavvisi.aspx").get();
      String title = doc.title();
      Elements links = doc.select("ul li span a");

      int x = 7;
      for (Element link : links) {
        String onclick = link.attr("onclick");
        String sub_onclick = onclick.substring(13);
        String sub_link = sub_onclick.split(",")[0];
        String my_l = sub_link.substring(0, sub_link.length()-1);
        String y = "http://avvisi.protezionecivile.tn.it" + my_l;
        data.add(link.text()+ ";" + y);
        x--;
        if(x <= 0)
          return 1;
      }
    } catch (IOException e) {
      Log.d("d", e.getMessage());
    }
    return 1;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    TextView messageView = (TextView)builder.findViewById(android.R.id.message);
    messageView.setText("Ottengo le allerte.. "+ values[0] +"%");
  }
}
