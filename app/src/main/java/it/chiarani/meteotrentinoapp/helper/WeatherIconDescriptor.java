package it.chiarani.meteotrentinoapp.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherIconDescriptor {
  public  WeatherIconDescriptor() {}

  private static List<Integer> soleggiatoConPioggia      = new ArrayList<Integer>(Arrays.asList(112, 21, 121));
  private static List<Integer> soleggiatoConPioggiaNeve  = new ArrayList<Integer>(Arrays.asList(14, 114, 117, 17, 20, 120, 122, 22, 23, 123, 124, 24));
  private static List<Integer> nevicata      			       = new ArrayList<Integer>(Arrays.asList(15, 115, 166, 16, 6, 106, 107, 7, 108, 8));
  private static List<Integer> sole          			       = new ArrayList<Integer>(Arrays.asList(18, 118));
  private static List<Integer> soleggiato    			       = new ArrayList<Integer>(Arrays.asList(11, 111, 125, 25, 126, 26));
  private static List<Integer> coperto       			       = new ArrayList<Integer>(Arrays.asList(19, 119));
  private static List<Integer> copertoPioggia            = new ArrayList<Integer>(Arrays.asList(1, 101, 102, 2, 13, 12));
  private static List<Integer> copertoPioggiaAbbondante  = new ArrayList<Integer>(Arrays.asList(3, 103, 104, 4, 803, 903, 113));
  private static List<Integer> copertoPioggiaNeve        = new ArrayList<Integer>(Arrays.asList(5, 105, 109, 9, 110, 10));
  private static List<Integer> temporale				         = new ArrayList<Integer>(Arrays.asList(821));

  public static WeatherTypes getWeatherType(int idIcon) {
    if(soleggiatoConPioggia.contains(idIcon))
      return WeatherTypes.SOLEGGIATO_CON_PIOGGIA;
    else if(soleggiatoConPioggiaNeve.contains(idIcon))
      return WeatherTypes.SOLEGGIATO_CON_PIOGGIA_E_NEVE;
    else if(nevicata.contains(idIcon))
      return WeatherTypes.NEVICATA;
    else if(sole.contains(idIcon))
      return WeatherTypes.SOLE;
    else if(soleggiato.contains(idIcon))
      return WeatherTypes.SOLEGGIATO;
    else if(coperto.contains(idIcon))
      return WeatherTypes.COPERTO;
    else if(copertoPioggia.contains(idIcon))
      return WeatherTypes.COPERTO_CON_PIOGGIA;
    else if(copertoPioggiaAbbondante.contains(idIcon))
      return WeatherTypes.COPERTO_CON_PIOGGIA_ABBONDANTE;
    else if(copertoPioggiaNeve.contains(idIcon))
      return WeatherTypes.SOLEGGIATO_CON_PIOGGIA_E_NEVE;
    else if(temporale.contains(idIcon))
      return WeatherTypes.TEMPORALE;
    else
      return WeatherTypes.UNDEFINED;
  }
}
