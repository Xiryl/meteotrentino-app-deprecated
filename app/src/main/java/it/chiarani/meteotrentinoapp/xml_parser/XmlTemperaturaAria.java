package it.chiarani.meteotrentinoapp.xml_parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * XML DAO for Station Weather Data
 */

@XStreamAlias("temperatura_aria")
public class XmlTemperaturaAria {

  @XStreamAlias("data")
  private String data;

  @XStreamAlias("temperatura")
  private String temperatura;

  XmlTemperaturaAria(String data, String temperatura) {

    this.data = data;
    this.temperatura = temperatura;
  }

  public String getData(){
    return this.data;
  }

  void setData(String data) {
    this.data = data;
  }

  public String getTemperatura() {
    return  this.temperatura;
  }

  void setTemperatura(String temperatura) {
    this.temperatura = temperatura;
  }
}
