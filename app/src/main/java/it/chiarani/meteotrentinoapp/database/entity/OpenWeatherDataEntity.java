package it.chiarani.meteotrentinoapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import it.chiarani.meteotrentinoapp.models.OpenWeatherData;

@Entity(tableName = "openWeatherData")
public class OpenWeatherDataEntity implements OpenWeatherData {

  @PrimaryKey(autoGenerate = true)
  private int idOpenWeatherData;
  private String humidity;
  private String pressure;
  private String sunrise;
  private String sunset;

  public OpenWeatherDataEntity(String humidity, String pressure, String sunrise, String sunset) {
    this.humidity = humidity;
    this.pressure = pressure;
    this.sunset = sunset;
    this.sunrise = sunrise;
  }

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
  public String getSunrise() {
    return sunrise;
  }

  @Override
  public void setSunrise(String sunrise) {
    this.sunrise = sunrise;
  }

  @Override
  public String getSunset() {
    return sunset;
  }

  @Override
  public void setSunset(String sunset) {
    this.sunset = sunset;
  }
}
