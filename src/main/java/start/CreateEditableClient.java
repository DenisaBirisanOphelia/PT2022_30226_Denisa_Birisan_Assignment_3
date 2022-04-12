package start;

import dao.AbstractDAO;
import model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//din cauza ca CreateEditable e clasa cu param,nu ma lasa sa fac o asignare directa
public class CreateEditableClient extends CreateEditable<Client> {
    private List<JTextField> listaValori;
    public CreateEditableClient(Client tupla) throws IllegalAccessException {
        super(tupla);
    }

    @Override
    public List<JTextField> getListaValori() {

        return super.getListaValori();
    }

    @Override
    public void setListaValori(List<JTextField> listaValori) {
        super.setListaValori(listaValori);
    }
}
