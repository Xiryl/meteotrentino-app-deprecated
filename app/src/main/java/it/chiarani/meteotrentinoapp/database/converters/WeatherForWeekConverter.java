package it.chiarani.meteotrentinoapp.database.converters;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import it.chiarani.meteotrentinoapp.database.entity.WeatherForWeekEntity;
import it.chiarani.meteotrentinoapp.models.WeatherForWeek;

/**
 * convert all classes to json
 * Guide: https://android.jlelse.eu/room-persistence-library-typeconverters-and-database-migration-3a7d68837d6c
 */
public class WeatherForWeekConverter {

  /**
   * Convert from json to WeatherForWeekEntity class
   */
  @TypeConverter
  public static WeatherForWeekEntity fromString(String value) {
    Type listType = new TypeToken<WeatherForWeekEntity>() {}.getType();
    return new Gson().fromJson(value, listType);
  }

  /**
   * Convert WeatherForWeekEntity class to json
   */
  @TypeConverter
  public static String fromWeatherForWeek(WeatherForWeekEntity weatherForWeek) {
    Gson gson = new Gson();
    String json = gson.toJson(weatherForWeek);
    return json;
  }
}
