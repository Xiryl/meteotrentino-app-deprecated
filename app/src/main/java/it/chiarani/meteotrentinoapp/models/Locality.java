package it.chiarani.meteotrentinoapp.models;

/**
 * Locality class:
     "localita": "ALA",
     "comune": "ALA",
     "quota": 177,
     "latitudine": "45.757142",
     "longitudine": "11.004772"
 */

public interface Locality {

  int getIdLocality();

  void setIdLocality(int idLocality);

  String getLoc();

  void setLoc(String loc);

  String getMunicipality();

  void setMunicipality(String municipality);

  int getHeight();

  void setHeight(int height);

  String getLatitude();

  void setLatitude(String latitude);

  String getLongitude();

  void setLongitude(String longitude);

}
