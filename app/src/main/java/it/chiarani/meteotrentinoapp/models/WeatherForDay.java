package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public interface WeatherForDay {

  int getIdPrevisioneGiorno();

  void setIdPrevisioneGiorno(int idPrevisioneGiorno);

  String getGiorno();

  void setGiorno(String giorno);

  int getIdIcona();

  void setIdIcona(int idIcona);

  int getIcona();

  void setIcona(int icona);

  String getDescIcona();

  void setDescIcona(String descIcona);

  String getIcoAllerte();

  void setIcoAllerte(String icoAllerte);

  String getColoreAllerte() ;

  void setColoreAllerte(String coloreAllerte);

  String getDescIconaAllerte();

  void setDescIconaAllerte(String descIconaAllerte);

  String getTestoGiorno();

  void setTestoGiorno(String testoGiorno);

  int gettMinGiorno();

  void settMinGiorno(int tMinGiorno);

  int gettMaxGiorno();

  void settMaxGiorno(int tMaxGiorno);

  List<WeatherForSlot> getFasce();

  void setFasce(List<WeatherForSlot> fasce);
}
