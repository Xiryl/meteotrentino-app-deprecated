package it.chiarani.meteotrentinoapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherForSlot")
public class WeatherForSlotEntity {

  // #REGION PRIVATE FIELDS
  private int idPrevisioneFascia;
  private String fascia;
  private String fasciaPer;
  private String fasciaOre;
  private int icona;
  private String descIcona;
  private int idPrecProb;
  private String descPrecProb;
  private int idPrecInten;
  private String descPrecInten;
  private int idTempProb;
  private String descTempProb;
  private int idVentoIntQuota;
  private String descVentoIntQuota;
  private int idVentoDirQuota;
  private String descVentoDirQuota;
  private int idVentoIntValle;
  private String descVentoIntValle;
  private int idVentoDirValle;
  private String descVentoDirValle;
  private int iconaVentoQuota;
  private int zeroTermico;
  private int limiteNevicate;
  // #ENDREGION*/

  /**
   * def. constructor
   */
  @Ignore
  public WeatherForSlotEntity() {
  }

  /**
   * full constructor
   */
  public WeatherForSlotEntity(int idPrevisioneFascia, String fascia, String fasciaPer, String fasciaOre, int icona, String descIcona, int idPrecProb, String descPrecProb, int idPrecInten, String descPrecInten, int idTempProb, String descTempProb, int idVentoIntQuota, String descVentoIntQuota, int idVentoDirQuota, String descVentoDirQuota, int idVentoIntValle, String descVentoIntValle, int idVentoDirValle, String descVentoDirValle, int iconaVentoQuota, int zeroTermico, int limiteNevicate) {

    this.idPrevisioneFascia = idPrevisioneFascia;
    this.fascia    = fascia;
    this.fasciaPer = fasciaPer;
    this.fasciaOre = fasciaOre;
    this.icona     = icona;
    this.descIcona = descIcona;
    this.idPrecProb = idPrecProb;
    this.descPrecProb = descPrecProb;
    this.idPrecInten = idPrecInten;
    this.descPrecInten = descPrecInten;
    this.idTempProb = idTempProb;
    this.descTempProb = descTempProb;
    this.idVentoIntQuota = idVentoIntQuota;
    this.descVentoIntQuota = descVentoIntQuota;
    this.idVentoDirQuota = idVentoDirQuota;
    this.descVentoDirQuota = descVentoDirQuota;
    this.idVentoIntValle = idVentoIntValle;
    this.descVentoIntValle = descVentoIntValle;
    this.idVentoDirValle = idVentoDirValle;
    this.descVentoDirValle = descVentoDirValle;
    this.iconaVentoQuota = iconaVentoQuota;
    this.zeroTermico = zeroTermico;
    this.limiteNevicate = limiteNevicate;
  }

  // #REGION GETTER & SETTER

  public int getIdPrevisioneFascia() {
    return idPrevisioneFascia;
  }

  public void setIdPrevisioneFascia(int idPrevisioneFascia) {
    this.idPrevisioneFascia = idPrevisioneFascia;
  }

  public String getFascia() {
    return fascia;
  }

  public void setFascia(String fascia) {
    this.fascia = fascia;
  }

  public String getFasciaPer() {
    return fasciaPer;
  }

  public void setFasciaPer(String fasciaPer) {
    this.fasciaPer = fasciaPer;
  }

  public String getFasciaOre() {
    return fasciaOre;
  }

  public void setFasciaOre(String fasciaOre) {
    this.fasciaOre = fasciaOre;
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

  public int getIdPrecProb() {
    return idPrecProb;
  }

  public void setIdPrecProb(int idPrecProb) {
    this.idPrecProb = idPrecProb;
  }

  public String getDescPrecProb() {
    return descPrecProb;
  }

  public void setDescPrecProb(String descPrecProb) {
    this.descPrecProb = descPrecProb;
  }

  public int getIdPrecInten() {
    return idPrecInten;
  }

  public void setIdPrecInten(int idPrecInten) {
    this.idPrecInten = idPrecInten;
  }

  public String getDescPrecInten() {
    return descPrecInten;
  }

  public void setDescPrecInten(String descPrecInten) {
    this.descPrecInten = descPrecInten;
  }

  public int getIdTempProb() {
    return idTempProb;
  }

  public void setIdTempProb(int idTempProb) {
    this.idTempProb = idTempProb;
  }

  public String getDescTempProb() {
    return descTempProb;
  }

  public void setDescTempProb(String descTempProb) {
    this.descTempProb = descTempProb;
  }

  public int getIdVentoIntQuota() {
    return idVentoIntQuota;
  }

  public void setIdVentoIntQuota(int idVentoIntQuota) {
    this.idVentoIntQuota = idVentoIntQuota;
  }

  public String getDescVentoIntQuota() {
    return descVentoIntQuota;
  }

  public void setDescVentoIntQuota(String descVentoIntQuota) {
    this.descVentoIntQuota = descVentoIntQuota;
  }

  public int getIdVentoDirQuota() {
    return idVentoDirQuota;
  }

  public void setIdVentoDirQuota(int idVentoDirQuota) {
    this.idVentoDirQuota = idVentoDirQuota;
  }

  public String getDescVentoDirQuota() {
    return descVentoDirQuota;
  }

  public void setDescVentoDirQuota(String descVentoDirQuota) {
    this.descVentoDirQuota = descVentoDirQuota;
  }

  public int getIdVentoIntValle() {
    return idVentoIntValle;
  }

  public void setIdVentoIntValle(int idVentoIntValle) {
    this.idVentoIntValle = idVentoIntValle;
  }

  public String getDescVentoIntValle() {
    return descVentoIntValle;
  }

  public void setDescVentoIntValle(String descVentoIntValle) {
    this.descVentoIntValle = descVentoIntValle;
  }

  public int getIdVentoDirValle() {
    return idVentoDirValle;
  }

  public void setIdVentoDirValle(int idVentoDirValle) {
    this.idVentoDirValle = idVentoDirValle;
  }

  public String getDescVentoDirValle() {
    return descVentoDirValle;
  }

  public void setDescVentoDirValle(String descVentoDirValle) {
    this.descVentoDirValle = descVentoDirValle;
  }

  public int getIconaVentoQuota() {
    return iconaVentoQuota;
  }

  public void setIconaVentoQuota(int iconaVentoQuota) {
    this.iconaVentoQuota = iconaVentoQuota;
  }

  public int getZeroTermico() {
    return zeroTermico;
  }

  public void setZeroTermico(int zeroTermico) {
    this.zeroTermico = zeroTermico;
  }

  public int getLimiteNevicate() {
    return limiteNevicate;
  }

  public void setLimiteNevicate(int limiteNevicate) {
    this.limiteNevicate = limiteNevicate;
  }

  // #ENDREGION
}
