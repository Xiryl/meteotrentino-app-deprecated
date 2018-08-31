package it.chiarani.meteotrentinoapp.models;

public interface WeatherReport {

  String getDataPubblicazione();

  void setDataPubblicazione(String dataPubblicazione);

  int getIdPrevisione();

  void setIdPrevisione(int idPrevisione);

  String getEvoluzione();

  void setEvoluzione(String evoluzione);

  String getEvoluzioneBreve();

  void setEvoluzioneBreve(String evoluzioneBreve);

  String[] getAllerteList();

  void setAllerteList(String[] allerteList);

  WeatherForWeek getPrevisione();

  void setPrevisione(WeatherForWeek previsione);

}
