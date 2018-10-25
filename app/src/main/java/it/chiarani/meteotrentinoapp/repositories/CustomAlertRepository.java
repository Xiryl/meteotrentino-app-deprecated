package it.chiarani.meteotrentinoapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import java.util.List;
import java.util.concurrent.Executors;

import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.CustomAlertDao;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;

public class CustomAlertRepository {

  /**
   * Location DAO
   */
  private CustomAlertDao alertDao;

  /**
   * Location Entities
   */
  private LiveData<List<CustomAlertEntity>> customAlertEntity;

  /**
   * Constructor
   */
  public CustomAlertRepository(Application app) {
    AppDatabase db    = Room.databaseBuilder(app.getApplicationContext(), AppDatabase.class, "appDatabase")
        .fallbackToDestructiveMigration()
        .build();
    alertDao          = db.customAlertDao();
    customAlertEntity = alertDao.getAll();
  }

  public void insert(final CustomAlertEntity entity) {
    /**
     * Execute insert on a new thread
     */
     Executors.newSingleThreadExecutor().execute(() -> {
        alertDao.insert(entity);
    });
  }

  public LiveData<List<CustomAlertEntity>> getAll() {
    return  customAlertEntity;
  }

  public void deleteAll() {
    // start on a new thread
    Executors.newSingleThreadExecutor().execute(() -> {
      alertDao.deleteAll();
    });
  }
}
