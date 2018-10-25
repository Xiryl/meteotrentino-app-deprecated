package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import it.chiarani.meteotrentinoapp.database.entity.LocationEntity;

@Dao
public interface LocationDao {

  /**
   * Get all localities from database
   * @return list of locality
   */
  @Query ("SELECT * FROM location")
  LiveData<List<LocationEntity>> getAll();

  /**
   * Delete all localities from database
   */
  @Query("DELETE FROM location")
  void deleteAll();

  /**
   * Insert a new locality to database
   * @param entity LocalityEntitiy to add
   */
  @Insert
  void insert(LocationEntity entity);
}
