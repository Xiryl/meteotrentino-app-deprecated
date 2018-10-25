package it.chiarani.meteotrentinoapp.api;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import it.chiarani.meteotrentinoapp.R;

public class API_protezioneCivileAvvisiAllerte extends AsyncTask<String, Integer, Integer> {

  // #region private fields
  private final static String                       CLASS_TAG = API_protezioneCivileAvvisiAllerte.class.getSimpleName();
  private API_protezioneCivileAvvisiAllerte_response delegate = null;
  private ArrayList<String>                          data     = new ArrayList<>();
  private Context mContext;
  private AlertDialog builder;
  // #endregion

  /**
   * Main constructor
   * @param mContext app context
   * @param res callback interface for get content async
   */
  public API_protezioneCivileAvvisiAllerte(Context mContext, API_protezioneCivileAvvisiAllerte_response res) {
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
    alert.setMessage(mContext.getResources().getString(R.string.API_protezionecivile)).create();
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
   * Create a GET and call a REST API for get all location and parse the JSON
   */
  @Override
  protected Integer doInBackground(String... s) {
    try {
      Document doc = Jsoup.connect(API_endpoint.ENDPOINT_ALERTS).get();

      // get data from DOM
      Elements links = doc.select("ul li span a");
      Elements dates = doc.select("ul li span span");

      int x = 0;
      for (Element link : links) {

        String onclick_attr  = link.attr("onclick");
        String sub_onclick   = onclick_attr.substring(13);
        String sub_link      = sub_onclick.split(",")[0];
        String document_link = sub_link.substring(0, sub_link.length()-1);
        String final_link    = API_endpoint.ENDPOINT_PROTEZIONE_CIV + document_link;
        String date_pt1      = dates.get(x).text().split(" ")[1];
        String date_pt2      = dates.get(x).text().split(" ")[2];
        String date_pt3      = dates.get(x).text().split(" ")[3];

        // add element to list
        data.add(String.format("%s;%s %s %s;%s",link.text(),date_pt1,date_pt2, date_pt3, final_link));

        x++;
        if(x >= links.size())
          break;
      }
    } catch (IOException e) {
      Log.e(CLASS_TAG, e.getMessage());
    }
    return 1;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    TextView messageView = builder.findViewById(android.R.id.message);
    messageView.setText(String.format("%s %s%%", mContext.getResources().getString(R.string.API_protezionecivile), values[0]));
  }
}
