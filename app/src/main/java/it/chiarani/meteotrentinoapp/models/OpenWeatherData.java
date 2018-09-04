package it.chiarani.meteotrentinoapp.models;

public interface OpenWeatherData {

  int getIdOpenWeatherData();
  void setIdOpenWeatherData(int id);

  String getHumidity();
  void setHumidity(String humidity);

  String getPressure();
  void setPressure(String pressure);

  String getSunsire();
  void setSunsire(String sunsire);

  String getSunset();
  void setSunset(String sunset);
}
