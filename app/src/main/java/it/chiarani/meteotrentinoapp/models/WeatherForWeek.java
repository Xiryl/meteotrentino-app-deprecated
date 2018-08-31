package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public interface WeatherForWeek {

  String getLocalita();

  void setLocalita(String localita);

  int getQuota();

  void setQuota(int quota);

  List<WeatherForDay> getGiorni();

  void setGiorni(List<WeatherForDay> giorni);
}
