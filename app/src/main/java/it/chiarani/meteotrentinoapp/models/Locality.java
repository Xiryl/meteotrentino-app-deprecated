package it.chiarani.meteotrentinoapp.models;

/**
 * Describe the locality
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
