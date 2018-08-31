package it.chiarani.meteotrentinoapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import java.util.List;
import java.util.concurrent.Executors;

import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.LocalityDao;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;
import it.chiarani.meteotrentinoapp.models.Locality;

public class LocalityRepository {

  /**
   * Locality DAO
   */
  private LocalityDao localityDao;

  /**
   * Locality Entities
   */
  private LiveData<List<LocalityEntity>> localityEntity;

  /**
   * Constructor
   */

  public LocalityRepository(Application app) {
    AppDatabase db = Room.databaseBuilder(app.getApplicationContext(), AppDatabase.class, "appDatabase").build();
    localityDao    = db.localityDao();
    localityEntity = localityDao.getAll();
  }

  public void insert(final LocalityEntity entity) {
    /**
     * Execute insert on a new thread
     */
    Executors.newSingleThreadExecutor().execute(() -> {
      localityDao.insert(entity);
    });
  }

  public LiveData<List<LocalityEntity>> getAll() {
    return  localityEntity;
  }
}
