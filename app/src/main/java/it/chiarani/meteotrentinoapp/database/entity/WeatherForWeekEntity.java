package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.util.List;
import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherForWeek;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherForWeek")
public class WeatherForWeekEntity implements WeatherForWeek {

  // #REGION PRIVATE FIELDS
  private String localita;
  private int quota;
  private List<WeatherForDayEntity> giorni;
  // #ENDREGION

  /**
   * def. constructor
   */
  @Ignore
  public WeatherForWeekEntity() {

  }

  /**
   * full constructor
   */
  public WeatherForWeekEntity(String localita, int quota, List<WeatherForDayEntity> giorni) {
    this.localita = localita;
    this.quota = quota;
    this.giorni = giorni;
  }

  @Override
  public String getLocalita() {
    return localita;
  }
  @Override
  public void setLocalita(String localita) {
    this.localita = localita;
  }
  @Override
  public int getQuota() {
    return quota;
  }
  @Override
  public void setQuota(int quota) {
    this.quota = quota;
  }
  @Override
  public List<WeatherForDayEntity> getGiorni() {
    return giorni;
  }
  @Override
  public void setGiorni(List<WeatherForDayEntity> giorni) {
    this.giorni = giorni;
  }
}
