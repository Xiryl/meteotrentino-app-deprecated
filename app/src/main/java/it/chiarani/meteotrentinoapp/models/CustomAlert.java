package it.chiarani.meteotrentinoapp.models;

public interface CustomAlert {
  long getAlertTime();
  void setAlertTime(long alertTime);

  int getAlertId();
  void setAlertId(int alertId);

  String getAlertDescription();
  void setAlertDescription(String alertDescription);
}
