package it.chiarani.meteotrentinoapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import it.chiarani.meteotrentinoapp.models.CustomAlert;

@Entity(tableName = "alert")
public class CustomAlertEntity implements CustomAlert {

  @PrimaryKey(autoGenerate = true)
  private int     alertId;
  private String  alertDescription;
  private long    alertTime;

  public CustomAlertEntity(String alertDescription, long alertTime){
    this.alertDescription = alertDescription;
    this.alertTime        = alertTime;
  }

  @Ignore
  public CustomAlertEntity() {}

  @Override
  public long getAlertTime() {
    return alertTime;
  }

  @Override
  public void setAlertTime(long alertTime) {
    this.alertTime = alertTime;
  }

  @Override
  public int getAlertId() {
    return alertId;
  }

  @Override
  public void setAlertId(int alertId) {
    this.alertId = alertId;
  }

  @Override
  public String getAlertDescription() {
    return alertDescription;
  }

  @Override
  public void setAlertDescription(String alertDescription) {
    this.alertDescription = alertDescription;
  }
}
