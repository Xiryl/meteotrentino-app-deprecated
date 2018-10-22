package it.chiarani.meteotrentinoapp.xml_parser;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * XML DAO for Station Weather : XmlPrecipitazione
 */

@XStreamAlias("precipitazione")
public class XmlPrecipitazione {

  @XStreamAlias("data")
  private String data;

  @XStreamAlias("pioggia")
  private String pioggia;

  XmlPrecipitazione(String data, String temperatura) {

    this.data = data;
    this.pioggia = temperatura;
  }

  public String getData(){
    return this.data;
  }

  void setData(String data) {
    this.data = data;
  }

  public String getPioggia() {
    return  this.pioggia;
  }

  void setPioggia(String pioggia) {
    this.pioggia = pioggia;
  }
}
