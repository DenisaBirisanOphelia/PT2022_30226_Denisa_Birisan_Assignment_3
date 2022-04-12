package presentation;

import bll.ClientBLL;
import bll.ProdusBLL;
import model.Client;
import model.Produs;
import start.CreateTable;
import start.DatabaseModel;
import start.ReflectionStrategy;
import start.Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProduse {
    //aici duc dupa mine modelul si interfata
    private DatabaseModel modelulMeu;
    private ViewProduse fereastraProduse;

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /** Constructor */
    public ControllerProduse(DatabaseModel model, ViewProduse view) {
        modelulMeu = model;
        fereastraProduse=view;
        //Adaugam ascultatori la view

        this.fereastraProduse.showAllProductsListener(new ControllerProduse.AllProduseListener());
       this.fereastraProduse.addClearListener(new ControllerProduse.ClearListener());
        this.fereastraProduse.addInsertProdusListener(new ControllerProduse.InsertProdusListener());
        this.fereastraProduse.deleteProdusListener(new ControllerProduse.DeleteProdusListener());
        this.fereastraProduse.updateProdusListener(new ControllerProduse.UpdateProdusListener());
    }

    class AllProduseListener   implements ActionListener {//vr sa si pun interfata so..
        @Override
        public void actionPerformed(ActionEvent e) {
            //fac rost de lista tuturor produselor
            ProdusBLL produsBLL = new ProdusBLL();

            Produs produs1=null;
            try {
                produs1 = produsBLL.findProdusById(1,"idProdus");
                //ReflectionStrategy.retrieveProperties(produs1);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            //asta imi e lista cu toti clientii
            List<Produs> listaProduse=produsBLL.findAll();

            try {
                CreateTable tabelas = new CreateTable<Produs>(listaProduse);
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
            fereastraProduse.reset();
        }
    }
    class InsertProdusListener   implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            int userIdProdus  = fereastraProduse.getUserIdProdus();
            String userNume=fereastraProduse.getUserNume();
            String userProducator=fereastraProduse.getUserProducator();
            int userStoc=fereastraProduse.getUserStoc();

            Produs produsNou=new  Produs(userIdProdus,userNume,userProducator,userStoc);
            ProdusBLL produsBLL1=null;
            try {
                produsBLL1 = new ProdusBLL(produsNou);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            Produs produs2=null;
            try {
                produs2 = produsBLL1.insert(produsNou);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

        }
    }
    class DeleteProdusListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idProdus= fereastraProduse.getIdProdusDeSters();
            ProdusBLL produsBLL = new ProdusBLL();
            Produs produs=null;
            try {
                produs = produsBLL.findProdusById(idProdus,"idProdus");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            produsBLL.delete(produs);
        }
    }
   class UpdateProdusListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idProdusDeEditat = fereastraProduse.getIdProdusUpdate();

            ProdusBLL produsBLL = new ProdusBLL();
            Produs produs = null;
            try {
                produs = produsBLL.findProdusById(idProdusDeEditat, "idProdus");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            produsBLL.update(produs);
        }
    }
}
