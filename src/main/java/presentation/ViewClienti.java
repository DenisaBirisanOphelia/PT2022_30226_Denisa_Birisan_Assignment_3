package presentation;

import start.DatabaseModel;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.MONOSPACED;

public class ViewClienti  extends JFrame{
    //am un model de baza de date aici
    private DatabaseModel DBmodel;
    //aici trebuie sa am alt atribut pentru JFRame-ul nou
    private  View fereastraClienti;
    private Button showAll=new Button("showClients");
    private Button insertNewClient=new Button("InsertNewClient");
    private Button clear=new Button("Clear");
    private Button delete=new Button("Delete");
    private Button edit=new Button("Edit");
    private JTextField idClientDeSters=new JTextField(3);
    private JTextField idClientDeEditat=new JTextField(3);
    private JTextField idClient=new JTextField(3);
    private JTextField nume=new JTextField(15);
    private JTextField prenume=new JTextField(15);
    private JTextField adresa=new JTextField(15);
    private JTextField bani=new JTextField(4);
    private JTextField age=new JTextField(3);


    public ViewClienti(DatabaseModel modelulprimit)
    {
        //ma folosesc de logica din model
        DBmodel=modelulprimit;

        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//cu macro-ul asta inchid doar fereastra curenta,
        // nu toata app cd dau pe x
        this.setTitle("Interfata cu tabelul clienti");

     JPanel content=new JPanel(new FlowLayout());
      content.add(new JLabel("Felicitari!Ai ajuns cu succes la tabelul clienti!"));
     //creez JPanelul cu butoane
        JPanel content1=new JPanel(new FlowLayout());
        showAll.setPreferredSize(new Dimension(100,60));
        showAll.setBackground(Color.cyan);
        showAll.setFont(Font.getFont(MONOSPACED));
        content1.add(showAll);
        insertNewClient.setPreferredSize(new Dimension(100,60));
        insertNewClient.setBackground(Color.cyan);
        insertNewClient.setFont(Font.getFont(MONOSPACED));
        content1.add(insertNewClient);
        clear.setPreferredSize(new Dimension(100,60));
        clear.setBackground(Color.cyan);
        clear.setFont(Font.getFont(MONOSPACED));
        content1.add(clear);
        delete.setPreferredSize(new Dimension(100,60));
        delete.setBackground(Color.cyan);
        delete.setFont(Font.getFont(MONOSPACED));
        content1.add(delete);
        edit.setPreferredSize(new Dimension(100,60));
        edit.setBackground(Color.cyan);
        edit.setFont(Font.getFont(MONOSPACED));
        content1.add(edit);
         // pun textFields pt inserare
       // JPanel content3=new JPanel(new FlowLayout());
        //pe prima linie am id si nume
        JPanel content4=new JPanel(new GridLayout(0,2));
        JLabel label1=new JLabel("idClient:");
        label1.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label1);
        content4.add(idClient);
        JLabel label2=new JLabel("nume:");
        label2.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label2);
        content4.add(nume);
        //apoi am prenume si adresa
        //JPanel content5=new JPanel(new FlowLayout());
        JLabel label3=new JLabel("prenume:");
        label3.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label3);
        content4.add(prenume);
        JLabel label4=new JLabel("adresa:");
        label4.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label4);
        content4.add(adresa);
        //apoi am bani si age
        //JPanel content7=new JPanel(new FlowLayout());
        JLabel label5=new JLabel("bani:");
        label5.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label5);
        content4.add(bani);
        JLabel label6=new JLabel("age:");
        label6.setHorizontalAlignment(JLabel.CENTER);
        content4.add(label6);
        content4.add(age);

        JPanel content6=new JPanel(new FlowLayout());
        content6.add(new JLabel("Inserati in casuta alaturata id-ul clientului pe care doriti sa-l stergeti:"));
        content6.add(idClientDeSters);

       // JPanel content7=new JPanel(new FlowLayout());
        content6.add(new JLabel("Inserati in casuta alaturata id-ul clientului pe care doriti sa-l editati:"));
        content6.add(idClientDeEditat);
        //content6.add(content7);
        //content3.add(bani);
        //content3.add(age);
        //le pun laolalta
        JPanel content2=new JPanel(new GridLayout(0,1));
        content2.add(content);
        content2.add(content1);
        content2.add(content4);
        content2.add(content6);
        //content2.add(content7);

        //setez contentu ferestrei la panelul creat si restu setarilor clasice
        this.add(content2);

        this.setVisible(true);
    }
   //adaug butonul pentru afisarea tuturor clientilor
   public  void showAllClientiListener( ControllerClienti.AllClientiListener mal) {

       showAll.addActionListener(mal);//deschide un pop-up cu toti clientii
   }

   //ma ocup de inserare
   public int getUserIdClient() {
       return Integer.parseInt(idClient.getText());
   }
    public String getUserNume() {
        return nume.getText();
    }
    public String getUserPrenume() {
        return prenume.getText();
    }
    public String getUserAdresa() {
        return adresa.getText();
    }
    public int getUserBani() {
        return Integer.parseInt(bani.getText());
    }
    public int getUserAge() {
        return Integer.parseInt(age.getText());
    }
    public  void setIdClient(String nou) {
        idClient.setText(nou);
    }
    public  void setNume(String nou) {
        nume.setText(nou);
    }
    public  void setPrenume(String nou) {
        prenume.setText(nou);
    }
    public  void setAdresa(String nou) {
        adresa.setText(nou);
    }
    public  void setBani(String nou) {
        bani.setText(nou);
    }
    public  void setAge(String nou) {
        age.setText(nou);
    }
    public int getIdClientDeSters() {
        return Integer.parseInt(idClientDeSters.getText());
    }
    public int getIdClientUpdate() {
        return Integer.parseInt(idClientDeEditat.getText());
    }
    public  void setIdClientDeSters(String nou) {
        idClientDeSters.setText(nou);
    }
    public  void setIdClientDeEditat(String nou) {
        idClientDeEditat.setText(nou);
    }
    public void addClearListener(ControllerClienti.ClearListener mal) {
        clear.addActionListener(mal);
    }

    public void reset() {
           setIdClient("");
           setNume("");
           setPrenume("");
           setAdresa("");
           setBani("");
           setAge("");
           setIdClientDeSters("");
           setIdClientDeEditat("");
    }
    //fac operatia de inserare
    public void addInsertClientListener(ControllerClienti.InsertClientListener mal) {

        insertNewClient.addActionListener(mal);
    }
    //fac operatia de stergere
    public void deleteClientListener(ControllerClienti.DeleteClientListener mal) {

        delete.addActionListener(mal);
    }
    //fac operatia de editare
    public void   updateClientListener(ControllerClienti.UpdateClientListener mal) {

        edit.addActionListener(mal);
    }
}
