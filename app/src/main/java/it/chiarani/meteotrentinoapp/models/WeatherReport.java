package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public class WeatherReport {

  // #REGION PRIVATE FIELDS
  private String dataPubblicazione;
  private int idPrevisione;
  private String evoluzione;
  private String evoluzioneBreve;
  private String[] allerteList;
  private WeatherForWeek previsione;
  // #ENDREGION

  /**
   * def. constructor
   */
  public WeatherReport() {

  }

  /**
   * full constructor
   */
  public WeatherReport(String dataPubblicazione, int idPrevisione, String evoluzione, String evoluzioneBreve, String[] allerteList, WeatherForWeek previsione) {
    this.dataPubblicazione = dataPubblicazione;
    this.idPrevisione = idPrevisione;
    this.evoluzione = evoluzione;
    this.evoluzioneBreve = evoluzioneBreve;
    this.allerteList = allerteList;
    this.previsione = previsione;
  }

  public String getDataPubblicazione() {
    return dataPubblicazione;
  }

  public void setDataPubblicazione(String dataPubblicazione) {
    this.dataPubblicazione = dataPubblicazione;
  }

  public int getIdPrevisione() {
    return idPrevisione;
  }

  public void setIdPrevisione(int idPrevisione) {
    this.idPrevisione = idPrevisione;
  }

  public String getEvoluzione() {
    return evoluzione;
  }

  public void setEvoluzione(String evoluzione) {
    this.evoluzione = evoluzione;
  }

  public String getEvoluzioneBreve() {
    return evoluzioneBreve;
  }

  public void setEvoluzioneBreve(String evoluzioneBreve) {
    this.evoluzioneBreve = evoluzioneBreve;
  }

  public String[] getAllerteList() {
    return allerteList;
  }

  public void setAllerteList(String[] allerteList) {
    this.allerteList = allerteList;
  }

  public WeatherForWeek getPrevisione() {
    return previsione;
  }

  public void setPrevisione(WeatherForWeek previsione) {
    this.previsione = previsione;
  }
}
