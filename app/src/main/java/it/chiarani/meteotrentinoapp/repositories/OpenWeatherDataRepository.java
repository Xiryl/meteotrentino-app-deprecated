package it.chiarani.meteotrentinoapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import java.util.List;
import java.util.concurrent.Executors;

import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.OpenWeatherDataDao;
import it.chiarani.meteotrentinoapp.database.entity.OpenWeatherDataEntity;

public class OpenWeatherDataRepository {


  private OpenWeatherDataDao openWeatherDataDao;

  private LiveData<List<OpenWeatherDataEntity>> openWeatherDataEntities;

  public OpenWeatherDataRepository(Application app ){
    AppDatabase db = Room.databaseBuilder(app.getApplicationContext(), AppDatabase.class, "appDatabase").build();
    openWeatherDataDao = db.openWeatherDataDao();
    openWeatherDataEntities = openWeatherDataDao.getAll();
  }

  public void insert(final OpenWeatherDataEntity entity) {
    /**
     * Execute insert on a new thread
     */
    Executors.newSingleThreadExecutor().execute(() -> {
      openWeatherDataDao.insert(entity);
    });
  }

  public LiveData<List<OpenWeatherDataEntity>> getAll() {
    return  openWeatherDataEntities;
  }

  public void deleteAll() {
    // start on a new thread
    Executors.newSingleThreadExecutor().execute(() -> {
      openWeatherDataDao.deleteAll();
    });
  }
}
