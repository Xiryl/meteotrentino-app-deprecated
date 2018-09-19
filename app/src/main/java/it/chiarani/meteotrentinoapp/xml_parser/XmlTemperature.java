package it.chiarani.meteotrentinoapp.xml_parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * XML DAO for Station Weather Data
 */

@XStreamAlias("temperature")
public class XmlTemperature {
  @XStreamImplicit
  private List<XmlTemperaturaAria> temperature;


  public List<XmlTemperaturaAria> getTemperature() {
    return temperature;
  }

  public void setTemperature(List<XmlTemperaturaAria> temperature) {
    this.temperature = temperature;
  }
}
