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

  long getSunrise();
  void setSunrise(long sunrise);

  long getSunset();
  void setSunset(long sunset);

  String getActualTemperature();
  void setActualTemperature(String actualTemperature);
}
