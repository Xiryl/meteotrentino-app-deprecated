package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;

@Dao
public interface OpenWeatherDataDao {

  /**
   * Get all openweathermap data from database
   * @return list of weatherdata
   */
  @Query("SELECT * FROM openWeatherData")
  LiveData<List<OpenWeatherDataEntity>> getAll();

  /**
   * Delete all data from database
   */
  @Query("DELETE FROM openWeatherData")
  void deleteAll();

  /**
   * Insert new item on database
   * @param entity
   */
  @Insert
  void insert(OpenWeatherDataEntity entity);
}
