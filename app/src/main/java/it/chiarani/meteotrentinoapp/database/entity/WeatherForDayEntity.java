package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import it.chiarani.meteotrentinoapp.models.WeatherForDay;
import it.chiarani.meteotrentinoapp.models.WeatherForSlot;
import java.util.List;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherForDay")
public class WeatherForDayEntity implements WeatherForDay {

  // #REGION PRIVATE FIELDS
  private int idPrevisioneGiorno;
  private String giorno;
  private int idIcona;
  private int icona;
  private String descIcona;
  private String icoAllerte;
  private String coloreAllerte;
  private String descIconaAllerte;
  private String testoGiorno;
  private int tMinGiorno;
  private int tMaxGiorno;
  private List<WeatherForSlot> fasce;
  // #ENDREGION

  /**
   * def. constructor
   */
  @Ignore
  public WeatherForDayEntity () {

  }

  /**
   * full constructor
   */
  public  WeatherForDayEntity(int idPrevisioneGiorno, String giorno, int idIcona, int icona, String descIcona, String icoAllerte, String coloreAllerte, String descIconaAllerte, String testoGiorno, int tMinGiorno, int tMaxGiorno, List<WeatherForSlot> fasce) {

    this.idPrevisioneGiorno = idPrevisioneGiorno;
    this.giorno = giorno;
    this.idIcona = idIcona;
    this.icona = icona;
    this.descIcona = descIcona;
    this.icoAllerte = icoAllerte;
    this.coloreAllerte = coloreAllerte;
    this.descIconaAllerte = descIconaAllerte;
    this.testoGiorno = testoGiorno;
    this.tMinGiorno = tMinGiorno;
    this.tMaxGiorno = tMaxGiorno;
    this.fasce = fasce;
  }

  // #REGION GETTER & SETTER
  @Override
  public int getIdPrevisioneGiorno() {
    return idPrevisioneGiorno;
  }
  @Override
  public void setIdPrevisioneGiorno(int idPrevisioneGiorno) {
    this.idPrevisioneGiorno = idPrevisioneGiorno;
  }
  @Override
  public String getGiorno() {
    return giorno;
  }
  @Override
  public void setGiorno(String giorno) {
    this.giorno = giorno;
  }
  @Override
  public int getIdIcona() {
    return idIcona;
  }
  @Override
  public void setIdIcona(int idIcona) {
    this.idIcona = idIcona;
  }
  @Override
  public int getIcona() {
    return icona;
  }
  @Override
  public void setIcona(int icona) {
    this.icona = icona;
  }
  @Override
  public String getDescIcona() {
    return descIcona;
  }
  @Override
  public void setDescIcona(String descIcona) {
    this.descIcona = descIcona;
  }
  @Override
  public String getIcoAllerte() {
    return icoAllerte;
  }
  @Override
  public void setIcoAllerte(String icoAllerte) {
    this.icoAllerte = icoAllerte;
  }
  @Override
  public String getColoreAllerte() {
    return coloreAllerte;
  }
  @Override
  public void setColoreAllerte(String coloreAllerte) {
    this.coloreAllerte = coloreAllerte;
  }
  @Override
  public String getDescIconaAllerte() {
    return descIconaAllerte;
  }
  @Override
  public void setDescIconaAllerte(String descIconaAllerte) {
    this.descIconaAllerte = descIconaAllerte;
  }
  @Override
  public String getTestoGiorno() {
    return testoGiorno;
  }
  @Override
  public void setTestoGiorno(String testoGiorno) {
    this.testoGiorno = testoGiorno;
  }
  @Override
  public int gettMinGiorno() {
    return tMinGiorno;
  }
  @Override
  public void settMinGiorno(int tMinGiorno) {
    this.tMinGiorno = tMinGiorno;
  }
  @Override
  public int gettMaxGiorno() {
    return tMaxGiorno;
  }
  @Override
  public void settMaxGiorno(int tMaxGiorno) {
    this.tMaxGiorno = tMaxGiorno;
  }
  @Override
  public List<WeatherForSlot> getFasce() {
    return fasce;
  }
  @Override
  public void setFasce(List<WeatherForSlot> fasce) {
    this.fasce = fasce;
  }

  // #ENDREGION
}
