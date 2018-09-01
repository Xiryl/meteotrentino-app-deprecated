package it.chiarani.meteotrentinoapp.models;

import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;

public interface WeatherReport {

  int getIdWeatherReport();

  void setIdWeatherReport(int idWeatherReport);

  String getDataPubblicazione();

  void setDataPubblicazione(String dataPubblicazione);

  int getIdPrevisione();

  void setIdPrevisione(int idPrevisione);

  String getEvoluzione();

  void setEvoluzione(String evoluzione);

  String getEvoluzioneBreve();

  void setEvoluzioneBreve(String evoluzioneBreve);

  String getAllerteList();

  void setAllerteList(String allerteList);

  WeatherForWeekEntity getPrevisione();

  void setPrevisione(WeatherForWeekEntity previsione);

}
