package it.chiarani.meteotrentinoapp.api;

import it.chiarani.meteotrentinoapp.models.WeatherReport;

/**
 * interface used from API_weatherreport for callback for async task
 */
public interface API_weatherReport_response {
  void processFinish(int response);
}
