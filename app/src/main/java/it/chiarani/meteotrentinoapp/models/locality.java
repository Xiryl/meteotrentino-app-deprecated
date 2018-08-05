package it.chiarani.meteotrentinoapp.models;

public class locality {

  // #REGION PRIVATE FIELDS
  private String loc;
  private String municipality;
  private int    height;
  private String latitude;
  private String longitude;
  // #ENDREGION


  /**
   * def. constructor
   */
  public locality() {

  }

  /**
   * full constructor
   */
  public locality(String loc, String municipality, int height, String latitude, String longitude) {
    this.loc          = loc;
    this.municipality = municipality;
    this.height       = height;
    this.latitude     = latitude;
    this.longitude    = longitude;
  }

  // #REGION GETTER & SETTER

  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }

  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  // #ENDREGION
}