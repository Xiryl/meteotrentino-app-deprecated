package it.chiarani.meteotrentinoapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import it.chiarani.meteotrentinoapp.database.converters.WeatherForWeekConverter;
import it.chiarani.meteotrentinoapp.database.dao.CustomAlertDao;
import it.chiarani.meteotrentinoapp.database.dao.LocalityDao;
import it.chiarani.meteotrentinoapp.database.dao.OpenWeatherDataDao;
import it.chiarani.meteotrentinoapp.database.dao.WeatherReportDao;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

/**
 * Link entities
 */
@Database(entities = {
    LocalityEntity.class,
    WeatherReportEntity.class,
    OpenWeatherDataEntity.class,
    CustomAlertEntity.class},
    version = 1,
    exportSchema = false)

/**
 * Link Type converter
 */
@TypeConverters({WeatherForWeekConverter.class})

/**
 * App database
 */
public abstract class AppDatabase extends RoomDatabase {

  /**
   * Locality DAO
   * @return locality table
   */
  public abstract LocalityDao localityDao();

  /**
   * WeatherReport DAO
   * @return weather data table
   */
  public abstract WeatherReportDao weatherReportDao();

  /**
   * OpenWeatherData DAO
   * @return openweathermap data table
   */
  public abstract OpenWeatherDataDao openWeatherDataDao();

  /**
   * Alert DAO
   * @return alerts
   */
  public abstract CustomAlertDao customAlertDao();
}
