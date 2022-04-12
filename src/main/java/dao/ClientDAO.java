package dao;

import model.Client;

import java.util.List;

public class ClientDAO  extends AbstractDAO<Client>{
    //utlizeaza metodele de baza din superclasa
    //creez aici doar metode specifice pt student
// uses basic CRUD methods from superclass

    public ClientDAO(Client client) throws NoSuchFieldException, IllegalAccessException {//pt inserare
       super(client);//ma folosesc de constructorul de la AbstractDAO pt reflection
    }
    public ClientDAO() //pt findAll
    {

    }

    public Client findById(int id, String numeColId)
    {
        return (Client) super.findById(id,numeColId); //asa chem metoda generica
    }
    public List<Client> findByName(String nume)
    {
        return super.findByName(nume); //asa chem metoda generica
    }
    public  List<Client> findAll()
    {
        return (List<Client>) super.findAll(); //asa chem metoda generica
    }
    public Client insert(Client t)
    {
        return super.insert(t);
    }
    public int delete(Client t)
    {
        return super.delete(t);
    }
    public Client update(Client t)
    {
        return super.update(t);
    }
}


