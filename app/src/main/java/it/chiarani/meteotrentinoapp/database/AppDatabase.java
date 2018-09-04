package it.chiarani.meteotrentinoapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import it.chiarani.meteotrentinoapp.database.converters.WeatherForWeekConverter;
import it.chiarani.meteotrentinoapp.database.dao.LocalityDao;
import it.chiarani.meteotrentinoapp.database.dao.OpenWeatherDataDao;
import it.chiarani.meteotrentinoapp.database.dao.WeatherReportDao;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

@Database(entities = {LocalityEntity.class, WeatherReportEntity.class, OpenWeatherDataEntity.class}, version = 1, exportSchema = false)
@TypeConverters({WeatherForWeekConverter.class})
public abstract class AppDatabase extends RoomDatabase {
  public abstract LocalityDao localityDao();
  public abstract WeatherReportDao weatherReportDao();
  public abstract OpenWeatherDataDao openWeatherDataDao();
}
