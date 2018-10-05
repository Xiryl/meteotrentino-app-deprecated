package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;

@Dao
public interface CustomAlertDao {

  /**
   * Get all alert form database
   * @return list of alerts
   */
  @Query("SELECT * FROM alert")
  LiveData<List<CustomAlertEntity>> getAll();

  /**
   * Delete all alerts from database
   */
  @Query("DELETE FROM alert")
  void deleteAll();

  /**
   * Insert a new alert to database
   * @param entity CustomAlertEntity to add
   */
  @Insert
  void insert(CustomAlertEntity entity);
}
