package it.chiarani.meteotrentinoapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.LocationDao;
import it.chiarani.meteotrentinoapp.database.entity.LocationEntity;

public class LocationRepository {

  /**
   * Location DAO
   */
  private LocationDao locationDao;

  /**
   * Location Entities
   */
  private LiveData<List<LocationEntity>> localityEntity;

  /**
   * Constructor
   */
  public LocationRepository(Context app) {
    AppDatabase db = Room.databaseBuilder(app.getApplicationContext(), AppDatabase.class, "appDatabase")
        .fallbackToDestructiveMigration()
        .build();
    locationDao = db.localityDao();
    localityEntity = locationDao.getAll();
  }

  public void insert(final LocationEntity entity) {
    /**
     * Execute insert on a new thread
     */
   // Executors.newSingleThreadExecutor().execute(() -> {
      locationDao.insert(entity);
    //});
  }

  public LiveData<List<LocationEntity>> getAll() {
    return  localityEntity;
  }
}
