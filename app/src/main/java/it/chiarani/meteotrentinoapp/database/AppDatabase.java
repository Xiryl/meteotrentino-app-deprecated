package it.chiarani.meteotrentinoapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import it.chiarani.meteotrentinoapp.database.dao.LocalityDao;
import it.chiarani.meteotrentinoapp.database.dao.WeatherReportDao;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;

@Database(entities = {LocalityEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  public abstract LocalityDao localityDao();
  public abstract WeatherReportDao weatherReportDao();
}
