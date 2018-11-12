package it.chiarani.meteotrentinoapp.models;

import java.util.List;

public class BulletProbGiorno {

    private String nomeGiorno;
    private String dataGiorno;
    private List<BulletProbFascia> fasce;


    /**
     * Empty constructor
     */
    public BulletProbGiorno() {
    }


    /**
     * Full constructor
     * @param nomeGiorno
     * @param dataGiorno
     * @param fasce
     */
    public BulletProbGiorno(String nomeGiorno, String dataGiorno, List<BulletProbFascia> fasce) {

        this.nomeGiorno = nomeGiorno;
        this.dataGiorno = dataGiorno;
        this.fasce = fasce;

    }

    public String getNomeGiorno() {
        return nomeGiorno;
    }

    public void setNomeGiorno(String nomeGiorno) {
        this.nomeGiorno = nomeGiorno;
    }

    public String getDataGiorno() {
        return dataGiorno;
    }

    public void setDataGiorno(String dataGiorno) {
        this.dataGiorno = dataGiorno;
    }

    public List<BulletProbFascia> getFasce() {
        return fasce;
    }

    public void setFasce(List<BulletProbFascia> fasce) {
        this.fasce = fasce;
    }
}
