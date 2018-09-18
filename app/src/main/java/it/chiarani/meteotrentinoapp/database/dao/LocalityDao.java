package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;

@Dao
public interface LocalityDao {

  /**
   * Get all localities from database
   * @return list of locality
   */
  @Query ("SELECT * FROM locality")
  LiveData<List<LocalityEntity>> getAll();

  /**
   * Delete all localities from database
   */
  @Query("DELETE FROM locality")
  void deleteAll();

  /**
   * Insert a new locality to database
   * @param entity LocalityEntitiy to add
   */
  @Insert
  void insert(LocalityEntity entity);
}
