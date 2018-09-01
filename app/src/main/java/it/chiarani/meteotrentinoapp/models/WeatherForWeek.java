package it.chiarani.meteotrentinoapp.models;
import java.util.List;
import it.chiarani.meteotrentinoapp.database.entity.WeatherForDayEntity;

public interface WeatherForWeek {

  String getLocalita();

  void setLocalita(String localita);

  int getQuota();

  void setQuota(int quota);

  List<WeatherForDayEntity> getGiorni();

  void setGiorni(List<WeatherForDayEntity> giorni);
}
