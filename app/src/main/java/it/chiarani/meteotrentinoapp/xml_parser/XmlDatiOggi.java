package it.chiarani.meteotrentinoapp.xml_parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * XML DAO for Station Weather Data
 */

@XStreamAlias("datiOggi")
public class XmlDatiOggi {

  @XStreamAlias("data")
  private String data;

  @XStreamAlias("tmin")
  private String tmin;

  @XStreamAlias("tmax")
  private String tmax;

  @XStreamAlias("rain")
  private String rain;

  @XStreamImplicit
  private List<XmlTemperature> temperature;

  public XmlDatiOggi(String data, String tmin, String tmax, String rain, List<XmlTemperature> temperature) {
  this.data = data;
  this.tmax = tmax;
  this.tmin = tmin;
  this.rain = rain;
  this.temperature = temperature;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getTmin() {
    return tmin;
  }

  public void setTmin(String tmin) {
    this.tmin = tmin;
  }

  public String getTmax() {
    return tmax;
  }

  public void setTmax(String tmax) {
    this.tmax = tmax;
  }

  public String getRain() {
    return rain;
  }

  public void setRain(String rain) {
    this.rain = rain;
  }

  public List<XmlTemperature> getTemperature() {
    return temperature;
  }

  public void setTemperature(List<XmlTemperature> temperature) {
    this.temperature = temperature;
  }
}

