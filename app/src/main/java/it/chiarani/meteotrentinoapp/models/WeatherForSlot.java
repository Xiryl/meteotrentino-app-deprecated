package it.chiarani.meteotrentinoapp.models;

public interface WeatherForSlot {

  int getIdPrevisioneFascia();

  void setIdPrevisioneFascia(int idPrevisioneFascia);

  String getFascia();

  void setFascia(String fascia);

  String getFasciaPer();

  void setFasciaPer(String fasciaPer);

  String getFasciaOre();

  void setFasciaOre(String fasciaOre);

  int getIcona();

  void setIcona(int icona);

  String getDescIcona();

  void setDescIcona(String descIcona);

  int getIdPrecProb();

  void setIdPrecProb(int idPrecProb);

  String getDescPrecProb();

  void setDescPrecProb(String descPrecProb);

  int getIdPrecInten();

  void setIdPrecInten(int idPrecInten);

  String getDescPrecInten();

  void setDescPrecInten(String descPrecInten);

  int getIdTempProb();

  void setIdTempProb(int idTempProb);

  String getDescTempProb();

  void setDescTempProb(String descTempProb);

  int getIdVentoIntQuota();

  void setIdVentoIntQuota(int idVentoIntQuota);

  String getDescVentoIntQuota();

  void setDescVentoIntQuota(String descVentoIntQuota);

  int getIdVentoDirQuota();

  void setIdVentoDirQuota(int idVentoDirQuota);

  String getDescVentoDirQuota();

  void setDescVentoDirQuota(String descVentoDirQuota);

  int getIdVentoIntValle();

  void setIdVentoIntValle(int idVentoIntValle);

  String getDescVentoIntValle();

  void setDescVentoIntValle(String descVentoIntValle);

  int getIdVentoDirValle();

  void setIdVentoDirValle(int idVentoDirValle);

  String getDescVentoDirValle();

  void setDescVentoDirValle(String descVentoDirValle);

  int getIconaVentoQuota();

  void setIconaVentoQuota(int iconaVentoQuota);

  int getZeroTermico();

  void setZeroTermico(int zeroTermico);

  int getLimiteNevicate();

  void setLimiteNevicate(int limiteNevicate);

}
