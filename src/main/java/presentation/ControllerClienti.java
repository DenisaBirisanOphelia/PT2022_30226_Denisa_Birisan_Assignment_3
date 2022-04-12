package presentation;

import bll.ClientBLL;
import model.Client;
import start.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerClienti {
  //aici duc dupa mine modelul si interfata
  private DatabaseModel modelulMeu;
    private ViewClienti fereastraClienti;

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /** Constructor */
    public ControllerClienti(DatabaseModel model, ViewClienti view) {
        modelulMeu = model;
         fereastraClienti=view;
        //Adaugam ascultatori la view

        this.fereastraClienti.showAllClientiListener(new AllClientiListener());
        this.fereastraClienti.addClearListener(new ClearListener());
        this.fereastraClienti.addInsertClientListener(new InsertClientListener());
        this.fereastraClienti.deleteClientListener(new DeleteClientListener());
        this.fereastraClienti.updateClientListener(new UpdateClientListener());
    }

    class AllClientiListener   implements ActionListener {//vr sa si pun interfata so..
        @Override
        public void actionPerformed(ActionEvent e) { //aici afisez tot tabelul cu clienti din baza de date
            //creez un tabel,dar fac o metoda generala pt asta

            //fac rost de lista tuturor clientilor
            ClientBLL clientBLL = new ClientBLL();

            Client client1=null;
            try {
                client1 = clientBLL.findClientById(1,"idClient");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            //asta imi e lista cu toti clientii
            List<Client> listaClienti=clientBLL.findAll();

            try {
                CreateTable tabelas = new CreateTable<>(listaClienti);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }


        }
    }


    class ClearListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fereastraClienti.reset();
        }
    }
    class InsertClientListener   implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            //tot la fel ii spun viewului sa imi idea inputurile
            int userIdClient  = fereastraClienti.getUserIdClient();
            String userNume=fereastraClienti.getUserNume();
            String userPrenume=fereastraClienti.getUserPrenume();
            String userAdresa=fereastraClienti.getUserAdresa();
            int userBani=fereastraClienti.getUserBani();
            int userAge=fereastraClienti.getUserAge();

            //incerc sa imi fac obiectul de tip client pe care sa il inserez
            Client clientNou=new  Client(userIdClient,userNume,userPrenume,userAdresa,userBani,userAge);

            ClientBLL clientBLL1=null;
            try {
                 clientBLL1 = new ClientBLL(clientNou);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

            //creez un client de tipul ClientBLL sa il pot valida


            Client client2=null;//daca s-a reusit sau nu inserarea

            //voi crea un obect de tip client1 si il dau ca rezultatul validarii clientBLL, adica fix acel obiect sau null, daca are
            //un format incorect
            try {
                client2 = clientBLL1.insert(clientNou);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }



        }
    }
    class DeleteClientListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
             //fac rost de idClient din interfata
             int idClient= fereastraClienti.getIdClientDeSters();
            ClientBLL clientBLL = new ClientBLL();
            Client client=null;//daca s-a reusit sau nu inserarea
            try {
                client = clientBLL.findClientById(idClient,"idClient");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            clientBLL.delete(client);
        }
    }
    class UpdateClientListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //aici citesc valoarea din TextField
            int idClientDeEditat = fereastraClienti.getIdClientUpdate();
            //imi creez o alta fereastra pt a putea introduce celelalte date,dar asta e deja in absttract DAO
            ClientBLL clientBLL = new ClientBLL();//fac un client nou, doar pt ca am nevoie de o tupla
            Client client = null;//daca s-a reusit sau nu inserarea
            try {
                client = clientBLL.findClientById(idClientDeEditat, "idClient");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            //in client am clientulDeUpdatat
            clientBLL.update(client);
        }
    }

}
