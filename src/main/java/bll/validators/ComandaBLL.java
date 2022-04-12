package bll.validators;

import dao.ClientDAO;
import dao.ComandaDAO;
import model.Client;
import model.Comanda;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ComandaBLL {
    private ComandaDAO comandaDAO;

    public ComandaBLL() {
        comandaDAO = new ComandaDAO();
    }
    public ComandaBLL(Comanda comanda) throws NoSuchFieldException, IllegalAccessException {
        comandaDAO = new ComandaDAO(comanda);//validez, apoi folosesc constructorul de la clientDAO
    }
    public Comanda insert(Comanda t) {
        Comanda st = comandaDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("Nu s-a putut insera comanda in baza de date!!");
        }
        return st;
    }
}
