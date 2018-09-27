package it.chiarani.meteotrentinoapp.models;

/**
 * Describe the openweather data
 */
public interface OpenWeatherData {

  int getIdOpenWeatherData();
  void setIdOpenWeatherData(int id);

  String getHumidity();
  void setHumidity(String humidity);

  String getPressure();
  void setPressure(String pressure);

  String getWindSpeed();
  void setWindSpeed(String wind);

  String getSunrise();
  void setSunrise(String sunrise);

  String getSunset();
  void setSunset(String sunset);

  String getActualTemperature();
  void setActualTemperature(String actualTemperature);
}
