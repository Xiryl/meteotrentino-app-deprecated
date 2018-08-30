package it.chiarani.meteotrentinoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;


@Dao
public interface LocalityDao {
  @Query ("SELECT * FROM locality")
  LiveData<List<LocalityEntity>> getAll();

  @Query("DELETE FROM locality")
  void deleteAll();

  @Insert
  void Insert(LocalityEntity entity);
}
