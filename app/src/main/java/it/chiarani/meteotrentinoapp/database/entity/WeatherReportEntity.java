package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.util.List;

import it.chiarani.meteotrentinoapp.models.WeatherForWeek;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherReport")
public class WeatherReportEntity implements WeatherReport {

  // #REGION PRIVATE FIELDS
  private String dataPubblicazione;
  private int idPrevisione;
  private String evoluzione;
  private String evoluzioneBreve;
  private List<String> allerteList;
  private WeatherForWeek previsione;
  // #ENDREGION

  /**
   * def. constructor
   */
  @Ignore
  public WeatherReportEntity() {

  }

  /**
   * full constructor
   */
  public WeatherReportEntity(String dataPubblicazione, int idPrevisione, String evoluzione, String evoluzioneBreve, List<String> allerteList, WeatherForWeek previsione) {
    this.dataPubblicazione = dataPubblicazione;
    this.idPrevisione = idPrevisione;
    this.evoluzione = evoluzione;
    this.evoluzioneBreve = evoluzioneBreve;
    this.allerteList = allerteList;
    this.previsione = previsione;
  }

  @Override
  public String getDataPubblicazione() {
    return dataPubblicazione;
  }
  @Override
  public void setDataPubblicazione(String dataPubblicazione) {
    this.dataPubblicazione = dataPubblicazione;
  }
  @Override
  public int getIdPrevisione() {
    return idPrevisione;
  }
  @Override
  public void setIdPrevisione(int idPrevisione) {
    this.idPrevisione = idPrevisione;
  }
  @Override
  public String getEvoluzione() {
    return evoluzione;
  }
  @Override
  public void setEvoluzione(String evoluzione) {
    this.evoluzione = evoluzione;
  }
  @Override
  public String getEvoluzioneBreve() {
    return evoluzioneBreve;
  }
  @Override
  public void setEvoluzioneBreve(String evoluzioneBreve) {
    this.evoluzioneBreve = evoluzioneBreve;
  }
  @Override
  public List<String> getAllerteList() {
    return allerteList;
  }
  @Override
  public void setAllerteList(List<String> allerteList) {
    this.allerteList = allerteList;
  }
  @Override
  public WeatherForWeek getPrevisione() {
    return previsione;
  }
  @Override
  public void setPrevisione(WeatherForWeek previsione) {
    this.previsione = previsione;
  }
}
