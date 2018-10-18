package it.chiarani.meteotrentinoapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import java.util.List;
import java.util.concurrent.Executors;
import it.chiarani.meteotrentinoapp.database.AppDatabase;
import it.chiarani.meteotrentinoapp.database.dao.WeatherReportDao;
import it.chiarani.meteotrentinoapp.database.entity.WeatherReportEntity;

public class WeatherReportRepository {

  private WeatherReportDao weatherReportDao;

  private LiveData<List<WeatherReportEntity>> weatherReportentity;

  public WeatherReportRepository(Application app ){
    AppDatabase db      = Room.databaseBuilder(app.getApplicationContext(), AppDatabase.class, "appDatabase").build();
    weatherReportDao    = db.weatherReportDao();
    weatherReportentity = weatherReportDao.getAll();
  }

  public void insert(final WeatherReportEntity entity) {
    /**
     * Execute insert on a new thread
     */
    Executors.newSingleThreadExecutor().execute(() -> {
      weatherReportDao.insert(entity);
    });
  }

  public LiveData<List<WeatherReportEntity>> getAll() {
    return  weatherReportentity;
  }

  public void deleteAll() {
    // start on a new thread
    Executors.newSingleThreadExecutor().execute(() -> weatherReportDao.deleteAll());
  }

}
