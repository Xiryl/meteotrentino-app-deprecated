package it.chiarani.meteotrentinoapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import it.chiarani.meteotrentinoapp.models.WeatherForSlot;

/**
 * Guide: https://developer.android.com/training/data-storage/room/
 */

@Entity(tableName = "weatherForSlot")
public class WeatherForSlotEntity implements WeatherForSlot {

  // #region private fields
  private int     idPrevisioneFascia;
  private String  fascia;
  private String  fasciaPer;
  private String  fasciaOre;
  private int     icona;
  private String  descIcona;
  private int     idPrecProb;
  private String  descPrecProb;
  private int     idPrecInten;
  private String  descPrecInten;
  private int     idTempProb;
  private String  descTempProb;
  private int     idVentoIntQuota;
  private String  descVentoIntQuota;
  private int     idVentoDirQuota;
  private String  descVentoDirQuota;
  private int     idVentoIntValle;
  private String  descVentoIntValle;
  private int     idVentoDirValle;
  private String  descVentoDirValle;
  private int     iconaVentoQuota;
  private int     zeroTermico;
  private int     limiteNevicate;
  // #endregion

  @Ignore
  public WeatherForSlotEntity() {}

  /**
   * full constructor
   */
  public WeatherForSlotEntity(int idPrevisioneFascia, String fascia, String fasciaPer, String fasciaOre, int icona, String descIcona, int idPrecProb, String descPrecProb, int idPrecInten, String descPrecInten, int idTempProb, String descTempProb, int idVentoIntQuota, String descVentoIntQuota, int idVentoDirQuota, String descVentoDirQuota, int idVentoIntValle, String descVentoIntValle, int idVentoDirValle, String descVentoDirValle, int iconaVentoQuota, int zeroTermico, int limiteNevicate) {

    this.idPrevisioneFascia = idPrevisioneFascia;
    this.fascia             = fascia;
    this.fasciaPer          = fasciaPer;
    this.fasciaOre          = fasciaOre;
    this.icona              = icona;
    this.descIcona          = descIcona;
    this.idPrecProb         = idPrecProb;
    this.descPrecProb       = descPrecProb;
    this.idPrecInten        = idPrecInten;
    this.descPrecInten      = descPrecInten;
    this.idTempProb         = idTempProb;
    this.descTempProb       = descTempProb;
    this.idVentoIntQuota    = idVentoIntQuota;
    this.descVentoIntQuota  = descVentoIntQuota;
    this.idVentoDirQuota    = idVentoDirQuota;
    this.descVentoDirQuota  = descVentoDirQuota;
    this.idVentoIntValle    = idVentoIntValle;
    this.descVentoIntValle  = descVentoIntValle;
    this.idVentoDirValle    = idVentoDirValle;
    this.descVentoDirValle  = descVentoDirValle;
    this.iconaVentoQuota    = iconaVentoQuota;
    this.zeroTermico        = zeroTermico;
    this.limiteNevicate     = limiteNevicate;
  }

  // #region GETTER & SETTER

  @Override
  public int getIdPrevisioneFascia() {
    return idPrevisioneFascia;
  }
  @Override
  public void setIdPrevisioneFascia(int idPrevisioneFascia) {
    this.idPrevisioneFascia = idPrevisioneFascia;
  }
  @Override
  public String getFascia() {
    return fascia;
  }
  @Override
  public void setFascia(String fascia) {
    this.fascia = fascia;
  }
  @Override
  public String getFasciaPer() {
    return fasciaPer;
  }
  @Override
  public void setFasciaPer(String fasciaPer) {
    this.fasciaPer = fasciaPer;
  }
  @Override
  public String getFasciaOre() {
    return fasciaOre;
  }
  @Override
  public void setFasciaOre(String fasciaOre) {
    this.fasciaOre = fasciaOre;
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
  public int getIdPrecProb() {
    return idPrecProb;
  }
  @Override
  public void setIdPrecProb(int idPrecProb) {
    this.idPrecProb = idPrecProb;
  }
  @Override
  public String getDescPrecProb() {
    return descPrecProb;
  }
  @Override
  public void setDescPrecProb(String descPrecProb) {
    this.descPrecProb = descPrecProb;
  }
  @Override
  public int getIdPrecInten() {
    return idPrecInten;
  }
  @Override
  public void setIdPrecInten(int idPrecInten) {
    this.idPrecInten = idPrecInten;
  }
  @Override
  public String getDescPrecInten() {
    return descPrecInten;
  }
  @Override
  public void setDescPrecInten(String descPrecInten) {
    this.descPrecInten = descPrecInten;
  }
  @Override
  public int getIdTempProb() {
    return idTempProb;
  }
  @Override
  public void setIdTempProb(int idTempProb) {
    this.idTempProb = idTempProb;
  }
  @Override
  public String getDescTempProb() {
    return descTempProb;
  }
  @Override
  public void setDescTempProb(String descTempProb) {
    this.descTempProb = descTempProb;
  }
  @Override
  public int getIdVentoIntQuota() {
    return idVentoIntQuota;
  }
  @Override
  public void setIdVentoIntQuota(int idVentoIntQuota) {
    this.idVentoIntQuota = idVentoIntQuota;
  }
  @Override
  public String getDescVentoIntQuota() {
    return descVentoIntQuota;
  }
  @Override
  public void setDescVentoIntQuota(String descVentoIntQuota) {
    this.descVentoIntQuota = descVentoIntQuota;
  }
  @Override
  public int getIdVentoDirQuota() {
    return idVentoDirQuota;
  }
  @Override
  public void setIdVentoDirQuota(int idVentoDirQuota) {
    this.idVentoDirQuota = idVentoDirQuota;
  }
  @Override
  public String getDescVentoDirQuota() {
    return descVentoDirQuota;
  }
  @Override
  public void setDescVentoDirQuota(String descVentoDirQuota) {
    this.descVentoDirQuota = descVentoDirQuota;
  }
  @Override
  public int getIdVentoIntValle() {
    return idVentoIntValle;
  }
  @Override
  public void setIdVentoIntValle(int idVentoIntValle) {
    this.idVentoIntValle = idVentoIntValle;
  }
  @Override
  public String getDescVentoIntValle() {
    return descVentoIntValle;
  }
  @Override
  public void setDescVentoIntValle(String descVentoIntValle) {
    this.descVentoIntValle = descVentoIntValle;
  }
  @Override
  public int getIdVentoDirValle() {
    return idVentoDirValle;
  }
  @Override
  public void setIdVentoDirValle(int idVentoDirValle) {
    this.idVentoDirValle = idVentoDirValle;
  }
  @Override
  public String getDescVentoDirValle() {
    return descVentoDirValle;
  }
  @Override
  public void setDescVentoDirValle(String descVentoDirValle) {
    this.descVentoDirValle = descVentoDirValle;
  }
  @Override
  public int getIconaVentoQuota() {
    return iconaVentoQuota;
  }
  @Override
  public void setIconaVentoQuota(int iconaVentoQuota) {
    this.iconaVentoQuota = iconaVentoQuota;
  }
  @Override
  public int getZeroTermico() {
    return zeroTermico;
  }
  @Override
  public void setZeroTermico(int zeroTermico) {
    this.zeroTermico = zeroTermico;
  }
  @Override
  public int getLimiteNevicate() {
    return limiteNevicate;
  }
  @Override
  public void setLimiteNevicate(int limiteNevicate) {
    this.limiteNevicate = limiteNevicate;
  }

  // #endregion
}
