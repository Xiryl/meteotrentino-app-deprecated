package it.chiarani.meteotrentinoapp.database.entity;
import android.arch.persistence.room.Entity;

import it.chiarani.meteotrentinoapp.models.WeatherForSlot;
import java.util.List;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherForDay")
public class WeatherForDayEntity {

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

  public int getIdPrevisioneGiorno() {
    return idPrevisioneGiorno;
  }

  public void setIdPrevisioneGiorno(int idPrevisioneGiorno) {
    this.idPrevisioneGiorno = idPrevisioneGiorno;
  }

  public String getGiorno() {
    return giorno;
  }

  public void setGiorno(String giorno) {
    this.giorno = giorno;
  }

  public int getIdIcona() {
    return idIcona;
  }

  public void setIdIcona(int idIcona) {
    this.idIcona = idIcona;
  }

  public int getIcona() {
    return icona;
  }

  public void setIcona(int icona) {
    this.icona = icona;
  }

  public String getDescIcona() {
    return descIcona;
  }

  public void setDescIcona(String descIcona) {
    this.descIcona = descIcona;
  }

  public String getIcoAllerte() {
    return icoAllerte;
  }

  public void setIcoAllerte(String icoAllerte) {
    this.icoAllerte = icoAllerte;
  }

  public String getColoreAllerte() {
    return coloreAllerte;
  }

  public void setColoreAllerte(String coloreAllerte) {
    this.coloreAllerte = coloreAllerte;
  }

  public String getDescIconaAllerte() {
    return descIconaAllerte;
  }

  public void setDescIconaAllerte(String descIconaAllerte) {
    this.descIconaAllerte = descIconaAllerte;
  }

  public String getTestoGiorno() {
    return testoGiorno;
  }

  public void setTestoGiorno(String testoGiorno) {
    this.testoGiorno = testoGiorno;
  }

  public int gettMinGiorno() {
    return tMinGiorno;
  }

  public void settMinGiorno(int tMinGiorno) {
    this.tMinGiorno = tMinGiorno;
  }

  public int gettMaxGiorno() {
    return tMaxGiorno;
  }

  public void settMaxGiorno(int tMaxGiorno) {
    this.tMaxGiorno = tMaxGiorno;
  }

  public List<WeatherForSlot> getFasce() {
    return fasce;
  }

  public void setFasce(List<WeatherForSlot> fasce) {
    this.fasce = fasce;
  }

  // #ENDREGION
}
