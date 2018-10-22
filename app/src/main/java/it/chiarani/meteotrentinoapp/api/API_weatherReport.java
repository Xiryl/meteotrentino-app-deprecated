package it.chiarani.meteotrentinoapp.api;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.configuration.AppConfiguration;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForSlotEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;
import it.chiarani.meteotrentinoapp.helper.Converter;
import it.chiarani.meteotrentinoapp.repositories.OpenWeatherDataRepository;
import it.chiarani.meteotrentinoapp.repositories.WeatherReportRepository;

public class API_weatherReport extends AsyncTask<String, Integer, Integer> {

  // #REGION PRIVATE FIELDS
  private final static String CLASS_TAG = API_weatherReport.class.getSimpleName();
  private String URL_API;
  private String URL_API_OP;
  private Application mApp;
  private API_weatherReport_response delegate = null;
  private int response = 1;
  // #END REGION

  /**
   * Constructor
   * @param app Application
   * @param mContext Context
   * @param res response callback
   * @param location locality
   */
  public API_weatherReport(Application app, Context mContext, API_weatherReport_response res, String location, String lat, String lon) {
    this.mApp     = app; // TODO: replace mApp
    this.delegate = res;

    // build URL API call
    URL_API    = API_endpoint.ENDPOINT_TODAY_WEATHER + location;
    URL_API_OP = API_endpoint.ENDPOINT_OPENWEATHER_DATA + location +", IT";
    URL_API_OP += "&APPID=";
    URL_API_OP += AppConfiguration.openWeatherMapKey;

    if(lat != null && !lat.isEmpty()) {
      URL_API_OP += "&lat=" + lat;
      URL_API_OP += "&lon=" + lon;
    }
  }

  /**
   * Before execute the task create a dialog
   */
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  /**
   * After execute the task we call the interface callback
   * and send the data
   */
  @Override
  protected void onPostExecute(Integer integer) {
    delegate.processFinish(response);
  }

  /**
   * Execute in bakground the task:
   * Create a GET and call a REST API for get all locality and parse the JSON
   */
  @Override
  protected Integer doInBackground(String... s) {

    WeatherReportRepository reportRepository = new WeatherReportRepository(mApp);

    WeatherReportEntity       tmp_report  = new WeatherReportEntity();
    WeatherForWeekEntity      wfw         = new WeatherForWeekEntity();
    List<WeatherForDayEntity> a_wfd       = new ArrayList<>();

    HttpURLConnection connection;
    BufferedReader reader;

    try {
      URL url = new URL(URL_API);
      connection = (HttpURLConnection) url.openConnection();
      connection.connect();
      connection.setConnectTimeout(15000);
      connection.setReadTimeout(15000);

      InputStream stream = connection.getInputStream();
      reader = new BufferedReader(new InputStreamReader(stream));
      StringBuffer buffer = new StringBuffer();
      String line = "";

      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      String data = buffer.toString();

      JSONObject json_ob = new JSONObject(data);

      tmp_report.setDataPubblicazione(json_ob.optString("dataPubblicazione"));                // dataPubblicazione
      tmp_report.setIdPrevisione(json_ob.optInt("idPrevisione"));                     // idPrevisione
      tmp_report.setEvoluzione(json_ob.optString("evoluzione"));                       // evoluzione
      tmp_report.setEvoluzioneBreve(json_ob.optString("evoluzioneBreve"));                  // evoluzioneBreve

      JSONArray arr_previsioni = json_ob.getJSONArray("previsione");                          // previsione

      wfw.setLocalita(arr_previsioni.getJSONObject(0).optString("localita"));           // localita
      wfw.setQuota(arr_previsioni.getJSONObject(0).optInt("quota"));              // quota

      JSONArray arr_giorni = arr_previsioni.getJSONObject(0).getJSONArray("giorni");    // giorni

      // ciclo i giorni ( max 7 )
      for (int i = 0; i < arr_giorni.length(); i++) {

        WeatherForDayEntity wfd = new WeatherForDayEntity();

        wfd.setIdPrevisioneGiorno(arr_giorni.getJSONObject(i).optInt("idPrevisioneGiorno"));                      // idPrevisioneGiorno
        wfd.setGiorno            (arr_giorni.getJSONObject(i).optString("giorno"));                               // giorno
        wfd.setIdIcona           (arr_giorni.getJSONObject(i).optInt("idIcona"));                                 // idIcona
        wfd.setIcona             (Converter.convertIconUriToInt(arr_giorni.getJSONObject(i).optString("icona"))); // icona
        wfd.setDescIcona         (arr_giorni.getJSONObject(i).optString("descIcona"));                            // descIcona
        wfd.setIcoAllerte        (arr_giorni.getJSONObject(i).optString("icoAllerte"));                           // icoAllerte
        wfd.setDescIconaAllerte  (arr_giorni.getJSONObject(i).optString("descIconaAllerte"));                     // descIconaAllerte
        wfd.setColoreAllerte     (arr_giorni.getJSONObject(i).optString("coloreAllerte"));                        // coloreAllerte
        wfd.setTestoGiorno       (arr_giorni.getJSONObject(i).optString("testoGiorno"));                          // testoGiorno
        wfd.settMinGiorno        (arr_giorni.getJSONObject(i).optInt("tMinGiorno"));                              // tMinGiorno
        wfd.settMaxGiorno        (arr_giorni.getJSONObject(i).optInt("tMaxGiorno"));                              // tMaxGiorno

        JSONArray arr_fasce = arr_giorni.getJSONObject(i).getJSONArray("fasce");              // fasce

        List<WeatherForSlotEntity> a_wfs = new ArrayList<>();


        // ciclo le fasce giornaliere ( max 4 )
        for (int j = 0; j < arr_fasce.length(); j++) {

          WeatherForSlotEntity wfs = new WeatherForSlotEntity();

          wfs.setIdPrevisioneFascia(arr_fasce.getJSONObject(j).optInt("idPrevisioneFascia"));                       // idPrevisioneFascia
          wfs.setFascia            (arr_fasce.getJSONObject(j).optString("fascia"));                                // fascia
          wfs.setFasciaPer         (arr_fasce.getJSONObject(j).optString("fasciaPer"));                             // fasciaPer
          wfs.setFasciaOre         (arr_fasce.getJSONObject(j).optString("fasciaOre"));                             // fasciaOre
          wfs.setIcona             (Converter.convertIconUriToInt(arr_fasce.getJSONObject(j).optString("icona")));  // icona
          wfs.setDescIcona         (arr_fasce.getJSONObject(j).optString("descIcona"));                             // descIcona
          wfs.setIdPrecProb        (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idPrecProb")));          // idPrecProb


          switch (arr_fasce.getJSONObject(j).optString("descPrecProb")) {
            case "--":
              wfs.setDescPrecProb("0");
              break;
            case "molto bassa":
              wfs.setDescPrecProb("10");
              break;
            case "bassa":
              wfs.setDescPrecProb("30");
              break;
            case "media":
              wfs.setDescPrecProb("50");
              break;
            case "alta":
              wfs.setDescPrecProb("80");
              break;
          }

          if (arr_fasce.getJSONObject(j).optString("descPrecInten").equals("--"))
            wfs.setDescPrecInten("0");
          else
            wfs.setDescPrecInten(arr_fasce.getJSONObject(j).optString("descPrecInten"));                // descPrecInten

          if (arr_fasce.getJSONObject(j).optString("descTempProb").equals("--"))
            wfs.setDescTempProb("0");
          else
            wfs.setDescTempProb(arr_fasce.getJSONObject(j).optString("descTempProb"));


          wfs.setIdPrecInten      (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idPrecInten")));      // idPrecInten
          wfs.setIdTempProb       (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idTempProb")));       // idTempProb
          wfs.setIdVentoIntQuota  (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoIntQuota")));  // idVentoIntQuota
          wfs.setDescVentoIntQuota(arr_fasce.getJSONObject(j).optString("descVentoIntQuota"));                  // descVentoIntQuota
          wfs.setIdVentoDirQuota  (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoDirQuota")));  // idVentoDirQuota
          wfs.setDescVentoDirQuota(arr_fasce.getJSONObject(j).optString("descVentoDirQuota"));                  // descVentoDirQuota
          wfs.setIdVentoIntValle  (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoIntValle")));  // idVentoIntValle
          wfs.setDescVentoIntValle(arr_fasce.getJSONObject(j).optString("descVentoIntValle"));                  // descVentoIntValle
          wfs.setIdVentoDirValle  (Integer.parseInt(arr_fasce.getJSONObject(j).optString("idVentoDirValle")));  // idVentoDirValle
          wfs.setDescVentoDirValle(arr_fasce.getJSONObject(j).optString("descVentoDirValle"));                  // descVentoDirValle
          wfs.setZeroTermico      (arr_fasce.getJSONObject(j).optInt("zeroTermico"));                           // zeroTermico
          wfs.setLimiteNevicate   (arr_fasce.getJSONObject(j).optInt("limiteNevicate"));                        // limiteNevicate

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

      tmp_report.setDataInserimentoDb(System.currentTimeMillis());

      reportRepository.insert(tmp_report);

      response = 1;
      // --------------
      //      DONE
      // --------------

    } catch (JSONException jex) {
      jex.printStackTrace();
      Log.e(CLASS_TAG, "Json Exception: " + jex.toString());
      response = -2;
      return -1;
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(CLASS_TAG, "Errore Exception: " + e.toString());
      response = -1;
      return -1;
    }

    // --------------------------------
    // ----- OPENWEATHER DOWNLOAD -----
    // --------------------------------

    OpenWeatherDataRepository repository_op = new OpenWeatherDataRepository(mApp);

    try {

      URL url = new URL(URL_API_OP);
      connection = (HttpURLConnection) url.openConnection();
      connection.connect();

      InputStream stream = connection.getInputStream();

      reader = new BufferedReader(new InputStreamReader(stream));

      StringBuffer buffer = new StringBuffer();
      String line;
      publishProgress(20);
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }

      String data = buffer.toString();
      JSONObject ob = new JSONObject(data);
      JSONObject main_data = ob.getJSONObject("main");
      JSONObject wind_data = ob.getJSONObject("wind");
      JSONObject sys_data  = ob.getJSONObject("sys");


      int act_temp = main_data.optInt("temp") - 273;
      int humidity = main_data.optInt("humidity");
      int pressure = main_data.optInt("pressure");
      double wind  = wind_data.optDouble("speed");

      int time_sunrise      = sys_data.optInt("sunrise");
      int time_sunset       = sys_data.optInt("sunset");
      long time_ms_sunrise  = (long) time_sunrise * 1000;
      long time_ms_sunset   = (long) time_sunset * 1000;

      repository_op.insert(new OpenWeatherDataEntity(humidity + "", pressure + "", time_ms_sunrise, time_ms_sunset, act_temp + "", wind + ""));

      response = 1;
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(CLASS_TAG, "Errore Exception: " + e.toString());
      response = -1;
    } finally {
      if (connection != null) {
        connection.disconnect();
        if (response == -1)
          response = -1;
        else if (response == -2)
          response = -2;
        else
          response = 1;
      }
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
        Log.e(CLASS_TAG, "Errore IOException1: " + e.toString());
        response = -1;
      }
    }
    return -1;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
  }
}
