package presentation;

import start.DatabaseModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//din controlleru asta incerc sa imi deschid fiecare fereastra separat si apoi sa fac un controller pt fiecare fereastra
public class ControllerMain {
        //aici duc dupa mine modelul si interfata
        private DatabaseModel modelulMeu;
        private View  interfataMea;
        private ViewClienti fereastraClienti;
        private ViewProduse fereastraProduse;

        /** Constructor */
        public ControllerMain(DatabaseModel model, View view) {
            modelulMeu = model;
            interfataMea  = view;
            //Adaugam ascultatori la view

            this.interfataMea.showClientiTableListener(new ControllerMain.ClientiWindowListener());
            this.interfataMea.showProduseTableListener(new ControllerMain.ProduseWindowListener());
            this.interfataMea.showComenziTableListener(new ControllerMain.ComenziWindowListener());

        }
        class ClientiWindowListener   implements ActionListener {//vr sa si pun interfata so..
            @Override
            public void actionPerformed(ActionEvent e) {
                //aici trebui sa inchid vechea fereastra si sa creez una noua pt tabelul clienti
                try {

                    ViewClienti viewclienti = new ViewClienti(modelulMeu);
                    viewclienti.setVisible(true);
                   //aici instantiez controllerul pt clienti
                    ControllerClienti contr=new ControllerClienti(modelulMeu,viewclienti);
                }
                catch(Exception exceptie)
                {
                    interfataMea.showMessage(exceptie.getMessage());
                }

            }
        }
    class ProduseWindowListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ViewProduse viewProduse = new ViewProduse(modelulMeu);
                viewProduse.setVisible(true);
                //aici instantiez controllerul pt clienti
                ControllerProduse contr=new ControllerProduse(modelulMeu,viewProduse);
            }
            catch(Exception exceptie)
            {
                interfataMea.showMessage(exceptie.getMessage());
            }

        }
    }


    class ComenziWindowListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ViewComenzi viewComenzi = new ViewComenzi(modelulMeu);
                viewComenzi.setVisible(true);
                //aici instantiez controllerul pt c
                ControllerComenzi contr=new ControllerComenzi(modelulMeu,viewComenzi);
            }
            catch(Exception exceptie)
            {
                interfataMea.showMessage(exceptie.getMessage());
            }

        }
    }
    }


