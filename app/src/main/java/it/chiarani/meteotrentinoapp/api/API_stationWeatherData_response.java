package it.chiarani.meteotrentinoapp.api;

import it.chiarani.meteotrentinoapp.xml_parser.XmlDatiOggi;

/**
 * Interface used from API_stationWeatherData for callback for asynctask
 */
public interface API_stationWeatherData_response {
  void processFinish(XmlDatiOggi data);
}