package it.chiarani.meteotrentinoapp.helper;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {

  public WeatherStation() {

  }

  public static List<String> getWeatherStationList() {
    String[] arr = WeatherStationsCSV.WeatherStationsCSV.split("\n");

    List<String> tmp = new ArrayList<>();
    for (String anArr : arr) {
      tmp.add(anArr.split(",")[1]);
    }
    return tmp;
  }

  public static String getStationFromPos(int Pos) {
    String[] arr = WeatherStationsCSV.WeatherStationsCSV.split("\n");
    int i = 1;
    for (String anArr : arr) {
      if(i == Pos) {
        return anArr.split(",")[0];
      }
      i++;
    }
    return "";
  }
}
