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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.configuration.AppConfiguration;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.Converter;
import it.chiarani.meteotrentinoapp.models.Locality;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherForSlot;
import it.chiarani.meteotrentinoapp.models.WeatherForWeek;
import it.chiarani.meteotrentinoapp.models.WeatherReport;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class API_weatherReport extends AsyncTask<String, Integer, Integer> {

  // #REGION PRIVATE FIELDS
  private final static String API_LOCALITY_TAG = "API_WEATHERREPORT";
  private String URL_API;
  private String URL_API_OP;
  private Context mContext;
  private AlertDialog builder;
  private WeatherReportEntity tmp_report;
  private Application _app;
  private AlertDialog.Builder alert;
  private API_weatherReport_response delegate = null;
  // #END REGION

  /**
   * Constructor
   * @param app Application
   * @param mContext Context
   * @param res response callback
   * @param location locality
   */
  public API_weatherReport(Application app, Context mContext, API_weatherReport_response res, String location) {
    this.mContext = mContext;
    this._app = app;
    this.delegate = res;
    URL_API = API_endpoint.ENDPOINT_TODAY_WEATHER + location;
    URL_API_OP = API_endpoint.ENDPOINT_OPENWEATHER_DATA + location;
    URL_API_OP += "&APPID=";
    URL_API_OP += AppConfiguration.openWeatherMapKey;
  }

  /**
   * Before execute the task create a dialog
   */
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    alert = new AlertDialog.Builder(mContext);
    alert.setMessage(mContext.getResources().getText(R.string.API_weatherReport_zero_data)).create();
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
    delegate.processFinish();
  }

  /**
   * Execute in bakground the task:
   * Create a GET and call a REST API for get all locality and parse the JSON
   */
  @Override
  protected Integer doInBackground(String... s) {

    WeatherReportRepository reportRepository = new WeatherReportRepository(_app);
    publishProgress(1);
    tmp_report = new WeatherReportEntity();
    WeatherForWeekEntity wfw = new WeatherForWeekEntity();
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    try {
      publishProgress(2);
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

      List<WeatherForDayEntity> a_wfd = new ArrayList<>();

      // ciclo i giorni ( max 7 )
      for(int i = 0; i < arr_giorni.length(); i++) {
        publishProgress(i*10);
        WeatherForDayEntity wfd = new WeatherForDayEntity();

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

        List<WeatherForSlotEntity> a_wfs = new ArrayList<>();
        // ciclo le fasce ( max 4 )
        for(int j = 0; j < arr_fasce.length(); j++) {
          WeatherForSlotEntity wfs = new WeatherForSlotEntity();

          wfs.setIdPrevisioneFascia(arr_fasce.getJSONObject(j).optInt("idPrevisioneFascia"));         // idPrevisioneFascia
          wfs.setFascia(arr_fasce.getJSONObject(j).optString("fascia"));                              // fascia
          wfs.setFasciaPer(arr_fasce.getJSONObject(j).optString("fasciaPer"));                        // fasciaPer
          wfs.setFasciaOre(arr_fasce.getJSONObject(j).optString("fasciaOre"));                        // fasciaOre
          wfs.setIcona(Converter.convertIconUriToInt(arr_fasce.getJSONObject(j).optString("icona"))); // icona
          wfs.setDescIcona(arr_fasce.getJSONObject(j).optString("descIcona"));                        // descIcona
          wfs.setIdPrecProb(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idPrecProb")));    // idPrecProb

          if(arr_fasce.getJSONObject(j).optString("descPrecProb").equals("--"))
            wfs.setDescPrecProb("zero");                  // descPrecProb
          else
            wfs.setDescPrecProb(arr_fasce.getJSONObject(j).optString("descPrecProb"));

          wfs.setIdPrecInten(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idPrecInten")));  // idPrecInten

          if(arr_fasce.getJSONObject(j).optString("descPrecInten").equals("--"))
            wfs.setDescPrecInten("zero");
          else
            wfs.setDescPrecInten(arr_fasce.getJSONObject(j).optString("descPrecInten"));                // descPrecInten

          wfs.setIdTempProb(Integer.parseInt(arr_fasce.getJSONObject(j).optString("idTempProb")));    // idTempProb

          if(arr_fasce.getJSONObject(j).optString("descTempProb").equals("--"))
            wfs.setDescTempProb("zero");
          else
            wfs.setDescTempProb(arr_fasce.getJSONObject(j).optString("descTempProb"));

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

      reportRepository.insert(tmp_report);

      // --------------
      //      DONE
      // --------------

    } catch (Exception e) {
      e.printStackTrace();
      Log.e(API_LOCALITY_TAG, "Errore Exception: "+  e.toString());
      tmp_report = null;
    }



    // ----- OPENWEATHER DOWNLOAD -----



    OpenWeatherDataRepository repository_op= new OpenWeatherDataRepository(_app);
    try {

      URL url = new URL(URL_API_OP);
      connection = (HttpURLConnection) url.openConnection();
      connection.connect();

      InputStream stream = connection.getInputStream();

      reader = new BufferedReader(new InputStreamReader(stream));

      StringBuffer buffer = new StringBuffer();
      String line = "";
      publishProgress(20);
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }

      String data = buffer.toString();
      JSONObject ob = new JSONObject(data);
      JSONObject main_data = ob.getJSONObject("main");

      int act_temp = main_data.optInt("temp") - 273;
      int humidity = main_data.optInt("humidity");
      int pressure = main_data.optInt("pressure");

      repository_op.insert(new OpenWeatherDataEntity(humidity + "", pressure + "", "", "", act_temp + ""));
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
    return -1;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    TextView messageView = (TextView)builder.findViewById(android.R.id.message);
    messageView.setText("Ottengo i dati meteo.. "+ values[0] +"%");
  }
}
