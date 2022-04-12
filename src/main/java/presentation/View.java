package presentation;

import start.DatabaseModel;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View  extends JFrame {

    //creez o fereastra mare, cu 3 butoane:Client,Order si Product,care sa redirectioneze la cele 3 ferestre distincte
    private  JButton clienti=new JButton("Clienti");
    private  JButton comenzi=new JButton("Comenzi");
    private  JButton produse=new JButton("Produse");


    //instantiez un model din view
    private final DatabaseModel DBmodel;

    public View(DatabaseModel modelulprimit)
    {
        //ma folosesc de logica din model
         DBmodel=modelulprimit;

        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Baza mea de date");

     JPanel content=new JPanel();
     content.setLayout( new GridBagLayout());
     content.add(new JLabel("Alege la ce tabel vrei sa navighezi      "));

      JPanel content1=new JPanel();
     content1.setLayout(new GridLayout(0,3));

     clienti.setBackground(Color.cyan);
     comenzi.setBackground(Color.lightGray);
     produse.setBackground(Color.pink);
     content1.add(clienti);
     content1.add(comenzi);
     content1.add(produse);

     content.add(content1);
     //setez contentu ferestrei la panelul creat si restu setarilor clasice
        this.add(content);

    }
 //vreau sa fac cand apas pe butomul clienti, sa ma duca la alta interfata

 public void showClientiTableListener( ControllerMain.ClientiWindowListener mal) {

       clienti.addActionListener(mal);//pt butonul clienti, ii adaug action listener, cd e apasat sa deschida alta fereastra
 }

 public void showMessage(String mesaj)
 {
     JOptionPane.showMessageDialog(this,mesaj);
 }
    public void showProduseTableListener( ControllerMain.ProduseWindowListener mal) {

        produse.addActionListener(mal);//pt butonul clienti, ii adaug action listener, cd e apasat sa deschida alta fereastra
    }

    public void showComenziTableListener( ControllerMain.ComenziWindowListener mal) {

        comenzi.addActionListener(mal);//pt butonul clienti, ii adaug action listener, cd e apasat sa deschida alta fereastra
    }
}
