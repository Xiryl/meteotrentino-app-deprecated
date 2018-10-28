package it.chiarani.meteotrentinoapp.api;

/**
 * Endpoint used inside application
 */
public class API_endpoint {
  public static final String ENDPOINT_LOCATION         = "https://www.meteotrentino.it/protcivtn-meteo/api/front/localitaOpenData";
  public static final String ENDPOINT_XML_STATION_DATA = "http://dati.meteotrentino.it/service.asmx/ultimiDatiStazione?codice=";
  public static final String ENDPOINT_TODAY_WEATHER    = "https://www.meteotrentino.it/protcivtn-meteo/api/front/previsioneOpenDataLocalita?localita=";
  public static final String ENDPOINT_OPENWEATHER_DATA = "https://api.openweathermap.org/data/2.5/weather?q=";
  public static final String ENDPOINT_RADAR            = "https://content.meteotrentino.it/dati-meteo/radar/home/lastRadar4mobile.aspx";
  public static final String ENDPOINT_INFRARED         = "http://api.sat24.com/animated/ALPS/infraPolair/1/Central%20European%20Standard%20Time/493234";
  public static final String GOOGLE_DOCS_BASE          = "http://docs.google.com/gview?embedded=true&url=";
  public static final String ENDPOINT_ALERTS           = "http://avvisi.protezionecivile.tn.it/elencoavvisi.aspx";
  public static final String ENDPOINT_PROTEZIONE_CIV   = "http://avvisi.protezionecivile.tn.it";
  public static final String URL_ALLERTE_METEOTT       = "https://www.meteotrentino.it/#!/content?menuItemDesktop=44";
  public static final String URL_ALLERTE_PROVINCIA     = "http://www.protezionecivile.tn.it/news_comunicati_stampa/";
  public static final String ENDPOINT_IMG_ZONA_ALPINA  = "https://api.sat24.com/mostrecent/ALPS/visual5hdcomplete";
  public static final String ENDPOINT_IMG_RADAR_NEVE   = "https://api.sat24.com/animated/EU/snow/1/Central%20European%20Standard%20Time";
  public static final String ENDPOINT_IMG_EUROPA       = "https://api.sat24.com/animated/EU/infraPolair/1/Central%20European%20Standard%20Time";
}
