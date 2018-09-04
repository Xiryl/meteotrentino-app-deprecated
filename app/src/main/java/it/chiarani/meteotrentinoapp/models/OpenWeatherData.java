package it.chiarani.meteotrentinoapp.models;

public interface OpenWeatherData {

  int getIdOpenWeatherData();
  void setIdOpenWeatherData(int id);

  String getHumidity();
  void setHumidity(String humidity);

  String getPressure();
  void setPressure(String pressure);

  String getSunrise();
  void setSunrise(String sunrise);

  String getSunset();
  void setSunset(String sunset);
}
