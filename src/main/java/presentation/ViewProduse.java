package presentation;

import start.DatabaseModel;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.MONOSPACED;

public class ViewProduse extends JFrame {
    private DatabaseModel DBmodel;
    private  View fereastraProduse;
    private Button showAll=new Button("showProducts");
    private Button insertNewProduct=new Button("InsertNewProduct");
    private Button clear=new Button("Clear");
    private Button delete=new Button("Delete");
    private Button edit=new Button("Edit");
    private JTextField idProdusDeSters=new JTextField(3);
    private JTextField idProdusDeEditat=new JTextField(3);
    private JTextField idProdus=new JTextField(3);
    private JTextField nume=new JTextField(15);
    private JTextField producator=new JTextField(15);
    private JTextField stoc=new JTextField(4);


    public ViewProduse(DatabaseModel modelulprimit)
    {
        DBmodel=modelulprimit;
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//cu macro-ul asta inchid doar fereastra curenta,
        this.setTitle("Interfata cu tabelul produse");

        JPanel content=new JPanel(new FlowLayout());
        content.add(new JLabel("Felicitari!Ai ajuns cu succes la tabelul produse!"));
        //creez JPanelul cu butoane
        JPanel content1=new JPanel(new FlowLayout());
        showAll.setPreferredSize(new Dimension(100,60));
        showAll.setBackground(Color.lightGray);
        showAll.setFont(Font.getFont(MONOSPACED));
        content1.add(showAll);
        insertNewProduct.setPreferredSize(new Dimension(100,60));
        insertNewProduct.setBackground(Color.lightGray);
        insertNewProduct.setFont(Font.getFont(MONOSPACED));
        content1.add(insertNewProduct);
        clear.setPreferredSize(new Dimension(100,60));
        clear.setBackground(Color.lightGray);
        clear.setFont(Font.getFont(MONOSPACED));
        content1.add(clear);
        delete.setPreferredSize(new Dimension(100,60));
        delete.setBackground(Color.lightGray);
        delete.setFont(Font.getFont(MONOSPACED));
        content1.add(delete);
        edit.setPreferredSize(new Dimension(100,60));
        edit.setBackground(Color.lightGray);
        edit.setFont(Font.getFont(MONOSPACED));
        content1.add(edit);

        JPanel content4=new JPanel(new GridLayout(0,2));
        JLabel label1=new JLabel("idProdus:");
        label1.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label1);
        content4.add(idProdus);
        JLabel label2=new JLabel("nume:");
        label2.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label2);
        content4.add(nume);

        JLabel label3=new JLabel("producator:");
        label3.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label3);
        content4.add(producator);
        JLabel label4=new JLabel("stoc:");
        label4.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label4);
        content4.add(stoc);

        JPanel content6=new JPanel(new FlowLayout());
        content6.add(new JLabel("Inserati in casuta alaturata id-ul produsului pe care doriti sa-l stergeti:"));
        content6.add(idProdusDeSters);

        content6.add(new JLabel("Inserati in casuta alaturata id-ul produsului pe care doriti sa-l editati:"));
        content6.add(idProdusDeEditat);

        //le pun laolalta
        JPanel content2=new JPanel(new GridLayout(0,1));
        content2.add(content);
        content2.add(content1);
        content2.add(content4);
        content2.add(content6);

        //setez contentu ferestrei la panelul creat si restu setarilor clasice
        this.add(content2);

        this.setVisible(true);
    }
    //adaug butonul pentru afisarea tuturor produselor
    public  void showAllProductsListener( ControllerProduse.AllProduseListener mal) {

        showAll.addActionListener(mal);//deschide un pop-up cu toti clientii
    }

    //ma ocup de inserare
    public int getUserIdProdus() {
        return Integer.parseInt(idProdus.getText());
    }
    public String getUserNume() {
        return nume.getText();
    }
    public String getUserProducator() {
        return producator.getText();
    }
    public int getUserStoc() {
        return Integer.parseInt(stoc.getText());
    }

    public  void setIdProdus(String nou) {
        idProdus.setText(nou);
    }
    public  void setNume(String nou) {
        nume.setText(nou);
    }
    public  void setProducator(String nou) {
        producator.setText(nou);
    }
    public  void setStoc(String nou) {
        stoc.setText(nou);
    }

    public int getIdProdusDeSters() {
        return Integer.parseInt(idProdusDeSters.getText());
    }
    public int getIdProdusUpdate() {
        return Integer.parseInt(idProdusDeEditat.getText());
    }
    public  void setIdProdusDeSters(String nou) {
        idProdusDeSters.setText(nou);
    }
    public  void setIdProdusDeEditat(String nou) {
        idProdusDeEditat.setText(nou);
    }
    public void addClearListener(ControllerProduse.ClearListener mal) {
        clear.addActionListener(mal);
    }

    public void reset() {
        setIdProdus("");
        setNume("");
        setProducator("");
        setStoc("");
        setIdProdusDeSters("");
        setIdProdusDeEditat("");
    }

    public void addInsertProdusListener(ControllerProduse.InsertProdusListener mal) {

        insertNewProduct.addActionListener(mal);
    }

    public void deleteProdusListener(ControllerProduse.DeleteProdusListener mal) {

        delete.addActionListener(mal);
    }

    public void   updateProdusListener(ControllerProduse.UpdateProdusListener mal) {

        edit.addActionListener(mal);
    }

}
