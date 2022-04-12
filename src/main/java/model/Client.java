package model;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//clasele de acest gen se numesc ENTITIES, ele sunt o mapare 1:1 cu tabelul
/*Caracteristici:
    - atributele clasei=campurile din tabel
    -settere si gettere pt fiecare si constructor
  --mai trebuie sa creez tabelele in MySQL. HOW??
  --aici doar modelul,nici un query, so simplest stuff here
*/
public class Client {
    private int idClient;
    private String nume;
    private String  prenume;
    private String  adresa;
    private int bani;//poate fac ceva fun si il as sacumpere doar daca mai are suficienti bani, we'll see
    private int age;



    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

public Client()//daca fac mereu cu constructoru asta,dc  l am mai fct si pe cel cuparam explicit????
 {
 }

    public Client(int idClient, String nume, String prenume, String adresa, int bani, int age) {
        this.idClient = idClient;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.bani = bani;
        this.age = age;
    }

    public String getNume() {
        return nume;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getBani() {
        return bani;
    }

    public void setBani(int bani) {
        this.bani = bani;
    }
    //suprascriu aici si toString
    @Override
    public String toString() {
        return "Client: id="+this.getIdClient()+", nume=" + this.getNume() + ", prenume=" + this.getPrenume() + ", adresa=" + this.getAdresa() +
                ", ramas in cont=" + this.getBani() + ", varsta=" + this.getAge();
    }


    }

