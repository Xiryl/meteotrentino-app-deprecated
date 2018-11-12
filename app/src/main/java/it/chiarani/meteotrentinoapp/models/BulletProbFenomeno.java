package it.chiarani.meteotrentinoapp.models;

public class BulletProbFenomeno {

    private String fenomeno;
    private int    valore;
    private String probabilita;

    /**
     * Empty constructor
     */
    public  BulletProbFenomeno() {
    }


    /**
     * full constructor
     * @param fenomeno
     * @param valore
     * @param probabilita
     */
    public  BulletProbFenomeno(String fenomeno, int valore, String probabilita) {

        this.fenomeno = fenomeno;
        this.valore = valore;
        this.probabilita = probabilita;

    }

    public String getFenomeno() {
        return fenomeno;
    }

    public void setFenomeno(String fenomeno) {
        this.fenomeno = fenomeno;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public String getProbabilita() {
        return probabilita;
    }

    public void setProbabilita(String probabilita) {
        this.probabilita = probabilita;
    }
}
