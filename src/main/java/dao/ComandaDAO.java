package dao;

import model.Comanda;
import model.Produs;

public class ComandaDAO extends AbstractDAO<Comanda>{
    public ComandaDAO(Comanda comanda) throws NoSuchFieldException, IllegalAccessException {//pt inserare
        super(comanda);//ma folosesc de constructorul de la AbstractDAO pt reflection
    }
    public ComandaDAO()
    {
    }
}
