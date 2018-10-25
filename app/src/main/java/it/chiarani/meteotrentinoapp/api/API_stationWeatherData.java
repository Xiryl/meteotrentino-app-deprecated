package it.chiarani.meteotrentinoapp.api;

import android.os.AsyncTask;
import android.util.Log;

import com.thoughtworks.xstream.XStream;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;
import it.chiarani.meteotrentinoapp.xml_parser.XmlPrecipitazione;
import it.chiarani.meteotrentinoapp.xml_parser.XmlPrecipitazioni;
import it.chiarani.meteotrentinoapp.xml_parser.XmlTemperaturaAria;
import it.chiarani.meteotrentinoapp.xml_parser.XmlTemperature;

public class API_stationWeatherData extends AsyncTask<Void, Void, Void> {

  // #region private fields
  private final static String             CLASS_TAG    = "API_stationWeatherData";
  private final int                       HTTP_TIMEOUT = 15000;
  private API_stationWeatherData_response delegate;
  private XmlDatiOggi data;
  private String API_URL;
  // #endregion

  /**
   * Main constructor
   * @param delegate callback delegate
   */
  public API_stationWeatherData(API_stationWeatherData_response delegate, String station_code) {
    this.delegate = delegate;
    this.API_URL  = API_endpoint.ENDPOINT_XML_STATION_DATA + station_code;
  }

  @Override
  protected Void doInBackground(Void... voids) {
    try {
      XStream xstream = new XStream();
      xstream.ignoreUnknownElements();

      // aliasing xml
      xstream.autodetectAnnotations(true);
      xstream.alias("datiOggi",         XmlDatiOggi.class);
      xstream.alias("temperature",      XmlTemperature.class);
      xstream.alias("temperatura_aria", XmlTemperaturaAria.class);
      xstream.alias("precipitazione",   XmlPrecipitazione.class);
      xstream.alias("precipitazioni",   XmlPrecipitazioni.class);

      data = loadXml(API_URL, xstream);

    } catch (Exception e) {
      Log.e(CLASS_TAG, e.getMessage());
    }
    return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    delegate.processFinish(data);
  }

  // call API
  private InputStream getInputStreamFromURL(String stringUrl) throws Exception {
    URL input = new URL(stringUrl);
    HttpURLConnection conn = (HttpURLConnection) input.openConnection();

    conn.setConnectTimeout(HTTP_TIMEOUT);
    conn.setReadTimeout(HTTP_TIMEOUT);

    return conn.getInputStream();
  }

  // load xml from stream
  private <T> T loadXml(String xmlUrl, XStream xStream) {
    T result = null;
    try {
      InputStream inputStream = getInputStreamFromURL(xmlUrl);
      result = (T) xStream.fromXML(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
