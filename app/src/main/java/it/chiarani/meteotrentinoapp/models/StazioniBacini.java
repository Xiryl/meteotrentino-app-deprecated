package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public class StazioniBacini {

    private String stazione;
    private List<SensoreBacini> sensori;

    public StazioniBacini(String stazione, List<SensoreBacini> sensori) {
        this.stazione = stazione;
        this.sensori = sensori;
    }

    public StazioniBacini() {

    }

    public String getStazione() {
        return stazione;
    }

    public void setStazione(String stazione) {
        this.stazione = stazione;
    }

    public List<SensoreBacini> getSensori() {
        return sensori;
    }

    public void setSensori(List<SensoreBacini> sensori) {
        this.sensori = sensori;
    }
}
