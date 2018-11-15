package it.chiarani.meteotrentinoapp.models;

public class SensoreBacini {

    private String nome_sensore;
    private String api_sensore;
    private String id_sensore;
    private String ente_proprietario;

    public SensoreBacini() {

    }

    public SensoreBacini(String nome_sensore, String api_sensore, String id_sensore, String ente_proprietario) {
        this.nome_sensore = nome_sensore;
        this.api_sensore = api_sensore;
        this.id_sensore = id_sensore;
        this.ente_proprietario = ente_proprietario;
    }

    public String getNome_sensore() {
        return nome_sensore;
    }

    public void setNome_sensore(String nome_sensore) {
        this.nome_sensore = nome_sensore;
    }

    public String getApi_sensore() {
        return api_sensore;
    }

    public void setApi_sensore(String api_sensore) {
        this.api_sensore = api_sensore;
    }

    public String getId_sensore() {
        return id_sensore;
    }

    public void setId_sensore(String id_sensore) {
        this.id_sensore = id_sensore;
    }

    public String getEnte_proprietario() {
        return ente_proprietario;
    }

    public void setEnte_proprietario(String ente_proprietario) {
        this.ente_proprietario = ente_proprietario;
    }
}
