package presentation;

import bll.ClientBLL;
import bll.ProdusBLL;
import bll.validators.ComandaBLL;
import model.Client;
import model.Comanda;
import model.Produs;
import start.DatabaseModel;
import start.ReflectionStrategy;
import start.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControllerComenzi {
    private DatabaseModel modelulMeu;
    private ViewComenzi fereastraComenzi;

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /** Constructor */
    public ControllerComenzi(DatabaseModel model, ViewComenzi view) {
        modelulMeu = model;
        fereastraComenzi=view;
        //Adaugam ascultatori la view

        this.fereastraComenzi.addClearListener(new ClearListener());
        this.fereastraComenzi.addComandaListener(new ComandaListener());

    }
    class ClearListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            fereastraComenzi.reset();
        }
    }

     class ComandaListener  implements  ActionListener{
         @Override
         public void actionPerformed(ActionEvent e) {
             //aici vad ce valoare s-a selectat
             String numeClient = fereastraComenzi.getNumeClient();
             String prenumeClient = fereastraComenzi.getPrenumeClient();
             String numeProdus = fereastraComenzi.getNumeProdus();
             int cantitateDorita = fereastraComenzi.getCantitateDeCumparat();

             //verific conditia data de ei: sa fie suficiente produse in stoc
             //caut id-ul Produsului cu numele respectiv
             int idProdusDeComandat;

             ProdusBLL produsBLL = new ProdusBLL();

             Produs produs = null;
             try {
                 produs = produsBLL.findProdusByName(numeProdus).get(0);

             } catch (Exception ex) {
                 LOGGER.log(Level.INFO, ex.getMessage());
             }

             int stocExistent = produs.getStoc();
             if (stocExistent < cantitateDorita) {//afisez mesaj de eroare si resetez interfata de comenzi
                 JOptionPane.showMessageDialog(fereastraComenzi, "Nu sunt stocuri suficiente!!Ne cerem scuze");
                 fereastraComenzi.reset();
             } else {
                 if (cantitateDorita==0)
                 {
                     JOptionPane.showMessageDialog(fereastraComenzi, "Va rugam nu incercati sa cumparati 0 produse..");
                     fereastraComenzi.reset();
                 }
                 else
                 {  //scad stocul
                     try {
                         produsBLL.updateStoc(produs, stocExistent - cantitateDorita);
                     } catch (SQLException ex) {
                         ex.printStackTrace();
                     }
                     //adaug in tabelul Comanda
                     //incerc sa imi fac obiectul de tip comanda pe care sa il inserez
                     Comanda comandaNoua = new Comanda(numeClient, prenumeClient, numeProdus, cantitateDorita);

                     ComandaBLL comandaBLL1 = null;
                     try {
                         comandaBLL1 = new ComandaBLL(comandaNoua);
                     } catch (NoSuchFieldException ex) {
                         ex.printStackTrace();
                     } catch (IllegalAccessException ex) {
                         ex.printStackTrace();
                     }
                     Comanda comanda2 = null;//daca s-a reusit sau nu inserarea

                     try {
                         comanda2 = comandaBLL1.insert(comandaNoua);

                     } catch (Exception ex) {
                         LOGGER.log(Level.INFO, ex.getMessage());
                     }

                     //generez factura sub forma de text file
                     String textFisier=("Clientul "+numeClient+" "+prenumeClient+
                             " a comandat produsul "+produs.getNume() +" in cantitatea "+cantitateDorita);
                     CreareFisier nouFisi=new CreareFisier();
                     nouFisi.creeazaFisier("Istoric comenzi");
                     nouFisi.scrie(textFisier);
                 }
             }


         }
     }
}
