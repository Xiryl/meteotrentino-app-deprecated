package it.chiarani.meteotrentinoapp.api;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.helper.Converter;
import it.chiarani.meteotrentinoapp.models.Locality;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherForSlot;
import it.chiarani.meteotrentinoapp.models.WeatherForWeek;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

public class API_weatherReport extends AsyncTask<String, Integer, Integer> {

  // #REGION PRIVATE FIELDS
  private final static String API_LOCALITY_TAG = "API_WEATHERREPORT";
  private String URL_API = "https://www.meteotrentino.it/protcivtn-meteo/api/front/previsioneOpenDataLocalita?localita=";
  Context mContext;
  AlertDialog builder;
  public WeatherReport tmp_report;
  public API_weatherReport_response delegate = null;
  // #END REGION

  public API_weatherReport(Context mContext, API_weatherReport_response res, String location) {
    this.mContext = mContext;
    this.delegate = res;
    URL_API += location;
  }

  /**
   * Before execute the task create a dialog
   */
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
    alert.setMessage(mContext.getResources().getString(R.string.API_locality_alert)).create();
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
    delegate.processFinish(tmp_report);
  }

  /**
   * Execute in bakground the task:
   * Create a GET and call a REST API for get all locality and parse the JSON
   */
  @Override
  protected Integer doInBackground(String... s) {
    tmp_report = new WeatherReport();
    WeatherForWeek wfw = new WeatherForWeek();

    HttpURLConnection connection = null;
    BufferedReader reader = null;

    try {
      Thread.sleep(2000);
      URL url = new URL(URL_API);
      connection = (HttpURLConnection) url.openConnection();
      connection.connect();

      InputStream stream = connection.getInputStream();

      reader = new BufferedReader(new InputStreamReader(stream));

      StringBuffer buffer = new StringBuffer();
      String line = "";

      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      Log.d("ops", "id: " +11);
      String data = buffer.toString();
      JSONObject ob = new JSONObject(data);

      tmp_report.setDataPubblicazione(ob.optString("dataPubblicazione"));                   // dataPubblicazione
      tmp_report.setIdPrevisione(ob.optInt("idPrevisione"));                                // idPrevisione
      tmp_report.setEvoluzione(ob.optString("evoluzione"));                                 // evoluzione
      tmp_report.setEvoluzioneBreve(ob.optString("evoluzioneBreve"));                       // evoluzioneBreve

      JSONArray allerte = ob.getJSONArray("AllerteList");                                   // allerteList

      JSONArray arr_previsione = ob.getJSONArray("previsione");                             // previsione

      wfw.setLocalita(arr_previsione.getJSONObject(0).optString("localita"));         // localita
      wfw.setQuota(arr_previsione.getJSONObject(0).optInt("quota"));                  // quota

      JSONArray arr_giorni = arr_previsione.getJSONObject(0).getJSONArray("giorni");  // giorni

      List<WeatherForDay> a_wfd = new ArrayList<>();

      // ciclo i giorni ( max 7 )
      for(int i = 0; i < arr_giorni.length(); i++) {
        WeatherForDay wfd = new WeatherForDay();

        wfd.setIdPrevisioneGiorno(arr_giorni.getJSONObject(i).optInt("idPrevisioneGiorno"));          // idPrevisioneGiorno
        wfd.setGiorno(arr_giorni.getJSONObject(i).optString("giorno"));                               // giorno
        wfd.setIdIcona(arr_giorni.getJSONObject(i).optInt("idIcona"));                                // idIcona
        wfd.setIcona(Converter.convertIconUriToInt(arr_giorni.getJSONObject(i).optString("icona")));  // icona
        wfd.setDescIcona(arr_giorni.getJSONObject(i).optString("descIcona"));                         // descIcona
        wfd.setIcoAllerte(arr_giorni.getJSONObject(i).optString("icoAllerte"));                       // icoAllerte
        wfd.setDescIconaAllerte(arr_giorni.getJSONObject(i).optString("descIconaAllerte"));           // descIconaAllerte
        wfd.setTestoGiorno(arr_giorni.getJSONObject(i).optString("testoGiorno"));                     // testoGiorno
        wfd.settMinGiorno(arr_giorni.getJSONObject(i).optInt("tMinGiorno"));                          // tMinGiorno
        wfd.settMaxGiorno(arr_giorni.getJSONObject(i).optInt("tMaxGiorno"));                          // tMaxGiorno

        JSONArray arr_fasce = arr_giorni.getJSONObject(i).getJSONArray("fasce");                      // fasce

        List<WeatherForSlot> a_wfs = new ArrayList<>();
        // ciclo le fasce ( max 4 )
        for(int j = 0; j < arr_fasce.length(); j++) {
          WeatherForSlot wfs = new WeatherForSlot();

          wfs.setIdPrevisioneFascia(arr_fasce.getJSONObject(j).optInt("idPrevisioneFascia"));         // idPrevisioneFascia
          wfs.setFascia(arr_fasce.getJSONObject(j).optString("fascia"));                              // fascia
          wfs.setFasciaPer(arr_fasce.getJSONObject(j).optString("fasciaPer"));                        // fasciaPer
          wfs.setFasciaOre(arr_fasce.getJSONObject(j).optString("fasciaOre"));                        // fasciaOre
          wfs.setIcona(Converter.convertIconUriToInt(arr_fasce.getJSONObject(j).optString("icona"))); // icona
          wfs.setDescIcona(arr_fasce.getJSONObject(j).optString("descIcona"));                        // descIcona
          wfs.setIdPrecProb(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idPrecProb")));    // idPrecProb
          wfs.setDescPrecProb(arr_fasce.getJSONObject(j).optString("descPrecProb"));                  // descPrecProb
          wfs.setIdPrecInten(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idPrecInten")));  // idPrecInten
          wfs.setDescPrecInten(arr_fasce.getJSONObject(j).optString("descPrecInten"));                // descPrecInten
          wfs.setIdTempProb(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idTempProb")));    // idTempProb
          wfs.setDescTempProb(arr_fasce.getJSONObject(j).optString("descTempProb"));                  // descTempProb
          wfs.setIdVentoIntQuota(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoIntQuota"))); // idVentoIntQuota
          wfs.setDescVentoIntQuota(arr_fasce.getJSONObject(j).optString("descVentoIntQuota"));        // descVentoIntQuota
          wfs.setIdVentoDirQuota(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoDirQuota")));  // idVentoDirQuota
          wfs.setDescVentoDirQuota(arr_fasce.getJSONObject(j).optString("descVentoDirQuota"));        // descVentoDirQuota
          wfs.setIdVentoIntValle(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoIntValle")));  // idVentoIntValle
          wfs.setDescVentoIntValle(arr_fasce.getJSONObject(j).optString("descVentoIntValle"));        // descVentoIntValle
          wfs.setIdVentoDirValle(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoDirValle")));  // idVentoDirValle
          wfs.setDescVentoDirValle(arr_fasce.getJSONObject(j).optString("descVentoDirValle"));        // descVentoDirValle
          // iconaVentoQuota
          wfs.setZeroTermico(arr_fasce.getJSONObject(j).optInt("zeroTermico"));                       // zeroTermico
          wfs.setLimiteNevicate(arr_fasce.getJSONObject(j).optInt("limiteNevicate"));                 // limiteNevicate

          // aggiungo lo slot alla lista di fasce
          a_wfs.add(wfs);
        }

        // aggiungo le fasce al giorno
        wfd.setFasce(a_wfs);

        // aggiungo il giorno alla lista
        a_wfd.add(wfd);
      }

      // aggiungo la lista di giorni alla settimana
      wfw.setGiorni(a_wfd);

      // aggiungo la lista di settimana alla previsione
      tmp_report.setPrevisione(wfw);

      // --------------
      //      DONE
      // --------------

    } catch (MalformedURLException e) {
      e.printStackTrace();
      Log.e(API_LOCALITY_TAG, "Errore MalformedURLException: "+  e.toString());
      tmp_report = null;
    } catch (IOException e) {
      e.printStackTrace();
      Log.e(API_LOCALITY_TAG, "Errore IOException: "+  e.toString());
      tmp_report = null;
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(API_LOCALITY_TAG, "Errore Exception: "+  e.toString());
      tmp_report = null;
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
        Log.e(API_LOCALITY_TAG, "Errore IOException1: "+  e.toString());

      }
    }
    Log.d(API_LOCALITY_TAG, "DATI locality Correttamente scaricati");
    return -1;
  }
}
