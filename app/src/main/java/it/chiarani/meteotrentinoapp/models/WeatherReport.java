package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public interface WeatherReport {

  String getDataPubblicazione();

  void setDataPubblicazione(String dataPubblicazione);

  int getIdPrevisione();

  void setIdPrevisione(int idPrevisione);

  String getEvoluzione();

  void setEvoluzione(String evoluzione);

  String getEvoluzioneBreve();

  void setEvoluzioneBreve(String evoluzioneBreve);

  List<String> getAllerteList();

  void setAllerteList(List<String> allerteList);

  WeatherForWeek getPrevisione();

  void setPrevisione(WeatherForWeek previsione);

}
