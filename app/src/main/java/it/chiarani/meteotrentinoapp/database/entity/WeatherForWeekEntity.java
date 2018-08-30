package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;

import java.util.List;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherForWeek")
public class WeatherForWeekEntity {

  // #REGION PRIVATE FIELDS
  private String localita;
  private int quota;
  private List<WeatherForDay> giorni;
  // #ENDREGION

  /**
   * def. constructor
   */
  public WeatherForWeekEntity() {

  }

  /**
   * full constructor
   */
  public WeatherForWeekEntity(String localita, int quota, List<WeatherForDay> giorni) {
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
