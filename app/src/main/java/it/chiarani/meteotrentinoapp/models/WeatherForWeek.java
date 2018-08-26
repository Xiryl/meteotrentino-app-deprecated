package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public class WeatherForWeek {

  // #REGION PRIVATE FIELDS
  private String localita;
  private int quota;
  private List<WeatherForDay> giorni;
  // #ENDREGION

  /**
   * def. constructor
   */
  public WeatherForWeek() {

  }

  /**
   * full constructor
   */
  public WeatherForWeek(String localita, int quota, List<WeatherForDay> giorni) {
    this.localita = localita;
    this.quota = quota;
    this.giorni = giorni;
  }

  public String getLocalita() {
    return localita;
  }

  public void setLocalita(String localita) {
    this.localita = localita;
  }

  public int getQuota() {
    return quota;
  }

  public void setQuota(int quota) {
    this.quota = quota;
  }

  public List<WeatherForDay> getGiorni() {
    return giorni;
  }

  public void setGiorni(List<WeatherForDay> giorni) {
    this.giorni = giorni;
  }
}
