package it.chiarani.meteotrentinoapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import it.chiarani.meteotrentinoapp.models.OpenWeatherData;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "openWeatherData")
public class OpenWeatherDataEntity implements OpenWeatherData {

  // #region private fields
  @PrimaryKey(autoGenerate = true)
  private int idOpenWeatherData;

  private String humidity;
  private String pressure;
  private long   sunrise;
  private long   sunset;
  private String actualTemperature;
  private String windSpeed;
  // #endregion

  @Ignore
  public OpenWeatherDataEntity() {

  }

  /**
   * Full constructor
   */
  public OpenWeatherDataEntity(String humidity, String pressure, long sunrise, long sunset, String actualTemperature, String windSpeed) {
    this.humidity = humidity;
    this.pressure = pressure;
    this.sunset   = sunset;
    this.sunrise  = sunrise;
    this.actualTemperature = actualTemperature;
    this.windSpeed = windSpeed;
  }

  // #region GETTER & SETTER

  @Override
  public int getIdOpenWeatherData() {
    return idOpenWeatherData;
  }

  @Override
  public void setIdOpenWeatherData(int idOpenWeatherDataEntity) {
    this.idOpenWeatherData = idOpenWeatherDataEntity;
  }

  @Override
  public String getHumidity() {
    return humidity;
  }

  @Override
  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }

  @Override
  public String getPressure() {
    return pressure;
  }

  @Override
  public void setPressure(String pressure) {
    this.pressure = pressure;
  }

  @Override
  public long getSunrise() {
    return sunrise;
  }

  @Override
  public void setSunrise(long sunrise) {
    this.sunrise = sunrise;
  }

  @Override
  public long getSunset() {
    return sunset;
  }

  @Override
  public void setSunset(long sunset) {
    this.sunset = sunset;
  }

  public String getActualTemperature() {
    return actualTemperature;
  }

  public void setActualTemperature(String actualTemperature) {
    this.actualTemperature = actualTemperature;
  }

  @Override
  public String getWindSpeed() {
    return windSpeed;
  }

  @Override
  public void setWindSpeed(String windSpeed) {
    this.windSpeed = windSpeed;
  }

  // #endregion
}
