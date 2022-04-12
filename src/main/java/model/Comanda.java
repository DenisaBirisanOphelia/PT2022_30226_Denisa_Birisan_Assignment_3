package model;

public class Comanda {
    private String numeClient;
    private String prenumeClient;
    private String numeProdus;
    private int cantitateCumparata;

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }


    public Comanda(String numeClient, String prenumeClient, String numeProdus, int cantitateCumparata) {
        this.numeClient = numeClient;
        this.prenumeClient=prenumeClient;
        this.numeProdus = numeProdus;
        this.cantitateCumparata = cantitateCumparata;
    }
  public Comanda()
  {
  }
    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }


    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getCantitateCumparata() {
        return cantitateCumparata;
    }

    public void setCantitateCumparata(int cantitateCumparata) {
        this.cantitateCumparata = cantitateCumparata;
    }

}
