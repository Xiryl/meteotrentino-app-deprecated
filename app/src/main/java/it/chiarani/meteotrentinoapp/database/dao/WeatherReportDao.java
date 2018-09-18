package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

@Dao
public interface WeatherReportDao {

  /**
   * Return all weather reports stored in database
   * @return List of weatherreport
   */
  @Query("SELECT * FROM weatherReport")
  LiveData<List<WeatherReportEntity>> getAll();

  /**
   * Add new report to database
   * @param entity item to add
   */
  @Insert
  void insert(WeatherReportEntity entity);

  /**
   * Remove all data from database
   */
  @Query("DELETE FROM weatherReport")
  void deleteAll();
}
