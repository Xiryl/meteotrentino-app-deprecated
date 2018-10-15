package it.chiarani.meteotrentinoapp.api;

/**
 * Endpoint used inside application
 */
public class API_endpoint {
  public static final String ENDPOINT_LOCALITY         = "https://www.meteotrentino.it/protcivtn-meteo/api/front/localitaOpenData";
  public static final String ENDPOINT_XML_STATION_DATA = "http://dati.meteotrentino.it/service.asmx/ultimiDatiStazione?codice=";
  public static final String ENDPOINT_TODAY_WEATHER    = "https://www.meteotrentino.it/protcivtn-meteo/api/front/previsioneOpenDataLocalita?localita=";
  public static final String ENDPOINT_OPENWEATHER_DATA = "https://api.openweathermap.org/data/2.5/weather?q=";
  public static final String ENDPOINT_RADAR            = "https://content.meteotrentino.it/dati-meteo/radar/home/lastRadar4mobile.aspx";
  public static final String ENDPOINT_INFRAROSSI       = "http://api.sat24.com/animated/ALPS/infraPolair/1/Central%20European%20Standard%20Time/493234";
  public static final String GOOGLE_DOCS_BASE          = "http://docs.google.com/gview?embedded=true&url=";
}
