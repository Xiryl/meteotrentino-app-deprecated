package it.chiarani.meteotrentinoapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import java.util.List;
import java.util.concurrent.Executors;

import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.CustomAlertDao;
import it.chiarani.meteotrentinoapp.database.dao.LocalityDao;
import it.chiarani.meteotrentinoapp.database.entity.CustomAlertEntity;
import it.chiarani.meteotrentinoapp.database.entity.LocalityEntity;

public class CustomAlertRepository {

  /**
   * Locality DAO
   */
  private CustomAlertDao alertDao;

  /**
   * Locality Entities
   */
  private LiveData<List<CustomAlertEntity>> customAlertEntity;

  /**
   * Constructor
   */
  public CustomAlertRepository(Application app) {
    AppDatabase db = Room.databaseBuilder(app.getApplicationContext(), AppDatabase.class, "appDatabase").build();
    alertDao    = db.customAlertDao();
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
