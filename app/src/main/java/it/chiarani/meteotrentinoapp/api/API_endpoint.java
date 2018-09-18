package it.chiarani.meteotrentinoapp.api;

public class API_endpoint {
  public static final String ENDPOINT_LOCALITY = "https://www.meteotrentino.it/protcivtn-meteo/api/front/localitaOpenData";
  public static final String ENDPOINT_XML_STATION_DATA = "http://dati.meteotrentino.it/service.asmx/ultimiDatiStazione?codice=";
  public static final String ENDPOINT_TODAY_WEATHER  = "https://www.meteotrentino.it/protcivtn-meteo/api/front/previsioneOpenDataLocalita?localita=";
  public static final String ENDPOINT_OPENWEATHER_DATA = "https://api.openweathermap.org/data/2.5/weather?q=";
}
