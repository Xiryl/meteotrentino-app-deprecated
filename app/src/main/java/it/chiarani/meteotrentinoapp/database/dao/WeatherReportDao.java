package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

@Dao
public interface WeatherReportDao {
  @Query("SELECT * FROM weatherReport")
  LiveData<List<WeatherReportEntity>> getAll();

  @Insert
  void insert(WeatherReportEntity entity);
}
