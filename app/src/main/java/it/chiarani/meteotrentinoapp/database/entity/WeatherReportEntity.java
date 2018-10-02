package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import it.chiarani.meteotrentinoapp.models.WeatherForWeek;
import it.chiarani.meteotrentinoapp.models.WeatherReport;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherReport")
public class WeatherReportEntity implements WeatherReport {

  // #region private fields
  @PrimaryKey(autoGenerate = true)
  private int                  idWeatherReport;

  private String               dataPubblicazione;
  private int                  idPrevisione;
  private String               evoluzione;
  private String               evoluzioneBreve;
  private String               allerteList;
  private WeatherForWeekEntity previsione;
  private long                 dataInserimentoDb;
  // #endregion

  @Ignore
  public WeatherReportEntity() {}

  /**
   * full constructor
   */
  public WeatherReportEntity(String dataPubblicazione, int idPrevisione, String evoluzione, String evoluzioneBreve, String allerteList, WeatherForWeekEntity previsione, long dataInserimentoDb) {
    this.dataPubblicazione = dataPubblicazione;
    this.idPrevisione     = idPrevisione;
    this.evoluzione       = evoluzione;
    this.evoluzioneBreve  = evoluzioneBreve;
    this.allerteList      = allerteList;
    this.previsione       = previsione;
    this.dataInserimentoDb= dataInserimentoDb;
  }

  // #region GETTER & SETTER
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
  public String getAllerteList() {
    return allerteList;
  }
  @Override
  public void setAllerteList(String allerteList) {
    this.allerteList = allerteList;
  }
  @Override
  public WeatherForWeekEntity getPrevisione() {
    return previsione;
  }
  @Override
  public void setPrevisione(WeatherForWeekEntity previsione) {
    this.previsione = previsione;
  }
  @Override
  public int getIdWeatherReport() {
    return idWeatherReport;
  }
  @Override
  public void setIdWeatherReport(int idWeatherReport) {
    this.idWeatherReport = idWeatherReport;
  }
  @Override
  public long getDataInserimentoDb() {
    return dataInserimentoDb;
  }
  @Override
  public void setDataInserimentoDb(long dataInserimentoDb) {
    this.dataInserimentoDb = dataInserimentoDb;
  }

  // #endregion
}
