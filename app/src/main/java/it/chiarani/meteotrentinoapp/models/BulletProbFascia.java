package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public class BulletProbFascia {

    private String fascia;
    private String fasciaPer;
    private List<BulletProbFenomeno> fenomeni;

    /**
     * Empty construcor
     */
    public BulletProbFascia() {
    }


    /**
     * Full constructor
     * @param fascia
     * @param fasciaPer
     * @param fenomeni
     */
    public BulletProbFascia(String fascia, String fasciaPer, List<BulletProbFenomeno> fenomeni) {

        this.fascia = fascia;
        this.fasciaPer = fasciaPer;
        this.fenomeni = fenomeni;
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

    public List<BulletProbFenomeno> getFenomeni() {
        return fenomeni;
    }

    public void setFenomeni(List<BulletProbFenomeno> fenomeni) {
        this.fenomeni = fenomeni;
    }
}
