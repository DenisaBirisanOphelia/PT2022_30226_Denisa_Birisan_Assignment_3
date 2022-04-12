package model;

public class Produs {
    private int idProdus;
    private String nume;
    private String producator;
    private  int stoc;


    public Produs() //am nevoie de el
    {

    }

    public Produs(int idProdus, String nume, String producator, int stoc) {
        this.idProdus = idProdus;
        this.nume = nume;
        this.producator = producator;
        this.stoc = stoc;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
    @Override
    public String toString() {
        return "Produs: id="+this.getIdProdus()+", nume=" + this.getNume() + ", producator=" + this.getProducator() +
           ", stoc=" + this.getStoc();
    }
}
