package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import it.chiarani.meteotrentinoapp.models.Location;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "location")
public class LocationEntity implements Location {

  // #region private fields
  @PrimaryKey (autoGenerate = true)
  private int idLocality;

  private String loc;
  private String municipality;
  private int    height;
  private String latitude;
  private String longitude;
  // #endregion

  @Ignore
  public LocationEntity() {}

  /**
   * full constructor
   */
  public LocationEntity(String loc, String municipality, int height, String latitude, String longitude) {
    this.loc          = loc;
    this.municipality = municipality;
    this.height       = height;
    this.latitude     = latitude;
    this.longitude    = longitude;
  }

  // #region GETTER & SETTER

  @Override
  public String getLoc() {
    return loc;
  }

  @Override
  public void setLoc(String loc) {
    this.loc = loc;
  }

  @Override
  public String getMunicipality() {
    return municipality;
  }

  @Override
  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String getLatitude() {
    return latitude;
  }

  @Override
  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  @Override
  public String getLongitude() {
    return longitude;
  }

  @Override
  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  @Override
  public int getIdLocality() {
    return idLocality;
  }

  @Override
  public void setIdLocality(int idLocality) {
    this.idLocality = idLocality;
  }

  // #endregion
}
