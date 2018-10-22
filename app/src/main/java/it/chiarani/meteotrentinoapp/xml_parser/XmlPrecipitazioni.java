package it.chiarani.meteotrentinoapp.xml_parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * XML DAO for Station Weather : XmlPrecipitazioni
 */

@XStreamAlias("precipitazioni")
public class XmlPrecipitazioni {
  @XStreamImplicit
  private List<XmlPrecipitazione> precipitazione;


  public List<XmlPrecipitazione> getPrecipitazione() {
    return precipitazione;
  }

  public void setTemperature(List<XmlPrecipitazione> precipitazione) {
    this.precipitazione = precipitazione;
  }
}
