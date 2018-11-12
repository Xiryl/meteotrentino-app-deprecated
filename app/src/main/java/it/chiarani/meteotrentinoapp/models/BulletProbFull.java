package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public class BulletProbFull {

    private String dataPubblicazione;
    private List<BulletProbGiorno> giorni;

    /**
     * Empty constructor
     */
    public  BulletProbFull() {
    }

    /**
     * Full constructor
     * @param dataPubblicazione
     * @param giorni
     */
    public   BulletProbFull(String dataPubblicazione, List<BulletProbGiorno> giorni) {
        this.dataPubblicazione = dataPubblicazione;
        this.giorni = giorni;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(String dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public List<BulletProbGiorno> getGiorni() {
        return giorni;
    }

    public void setGiorni(List<BulletProbGiorno> giorni) {
        this.giorni = giorni;
    }
}
