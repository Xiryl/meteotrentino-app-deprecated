package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;

@Dao
public interface OpenWeatherDataDao {
  @Query("SELECT * FROM openWeatherData")
  LiveData<List<OpenWeatherDataEntity>> getAll();

  @Query("DELETE FROM openWeatherData")
  void deleteAll();

  @Insert
  void insert(OpenWeatherDataEntity entity);
}
