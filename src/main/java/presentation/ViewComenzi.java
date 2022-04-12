package presentation;

import bll.ClientBLL;
import bll.ProdusBLL;
import model.Client;
import model.Produs;
import start.CreateTable;
import start.DatabaseModel;
import start.ReflectionStrategy;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.awt.Font.BOLD;
import static java.awt.Font.MONOSPACED;



public class ViewComenzi extends JFrame {
    private DatabaseModel DBmodel;
    //aici trebuie sa am alt atribut pentru JFRame-ul nou
    private  View fereastraComenzi;
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    private Button comanda=new Button("Comanda");
    private JTextField cantitateDeCumparat=new JTextField(4);
    private Button clear=new Button("Clear");
    private JComboBox<String> clientiNumeChoice=new JComboBox<String>();
    private JComboBox<String> clientiPrenumeChoice=new JComboBox<String>();
    private JComboBox<String> produseNumeChoice=new JComboBox<String>();
    public ViewComenzi(DatabaseModel modelulPrimit)
    {
        DBmodel=modelulPrimit;
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Interfata cu tabelul comenzi");

        JPanel content=new JPanel(new FlowLayout());
        content.add(new JLabel("Felicitari!Ai ajuns cu succes la tabelul comenzi!"));

        //fac drop-down menu pt client, resp pt prod, mai apoi fac buton cu nr produse stoc
        //imi trebuie un findAll pt clienti, resp pt produse

        ClientBLL clientBLL = new ClientBLL();
        Client client1=null;
        try {
            client1 = clientBLL.findClientById(1,"idClient");

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }
        //asta imi e lista cu toti clientii
        List<Client> listaClienti=clientBLL.findAll();

        //fac rost de lista tuturor produselor
        ProdusBLL produsBLL = new ProdusBLL();

        Produs produs1=null;
        try {
            produs1 = produsBLL.findProdusById(1,"idProdus");

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }

        //asta imi e lista cu toti clientii
        List<Produs> listaProduse=produsBLL.findAll();

        String[] numeClienti = new String[listaClienti.size()+1];
       String[] numeProduse = new String[listaProduse.size()+1];
       Integer[] stocProduse=new Integer[listaProduse.size()+1];

        for(int i=0;i<listaClienti.size();i++) {
            numeClienti[i] = listaClienti.get(i).getNume();
        }
        JPanel content1= new JPanel(new FlowLayout());
        clientiNumeChoice=new JComboBox<>(numeClienti);
        clientiPrenumeChoice=new JComboBox<>();
        //acuma am prima alegere gata
        content1.add(new JLabel("Selecteaza clientul care doresti sa plaseze comanda: "));
        content1.add(clientiNumeChoice);
        clientiNumeChoice.addActionListener(event -> {
            // Get the source of the component, which is our comboBox
            JComboBox comboBox1 = (JComboBox) event.getSource();

            // obtine numele clientului selectat
            Object selected = comboBox1.getSelectedItem();
            //obtin numele comenzii facute
            String command = event.getActionCommand();
            //System.out.println("Action Command = " + command);

            // Detect whether the action command is "comboBoxEdited or "comboBoxChanged"
            if ("comboBoxChanged".equals(command)) {
                //aici voi permite selectia doar pt cine are numele din selected
                ClientBLL clientBLL1 = new ClientBLL();
                Client client2 = null;
                List<Client> listaClientiPermisi = null;
                try {
                    listaClientiPermisi = clientBLL.findClientByName(selected.toString());

                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }


                //creez al doilea JComboBox
            clientiPrenumeChoice.removeAllItems();
                String[] prenumeClienti = new String[listaClientiPermisi.size() + 1];
                for (int i = 0; i < listaClientiPermisi.size(); i++) {
                    prenumeClienti[i] = listaClientiPermisi.get(i).getPrenume();
                    //System.out.println(prenumeClienti[i]);
                    clientiPrenumeChoice.addItem(prenumeClienti[i]);
                }
                //clientiPrenumeChoice=JComboBox<>(prenumeClienti);
                content1.add(clientiPrenumeChoice);
            }
        });
        content1.add(clientiPrenumeChoice);
         //aici pt nume produse si stocn am probleme
        for(int i=0;i<listaProduse.size();i++) {
            numeProduse[i] = listaProduse.get(i).getNume();
            stocProduse[i]=listaProduse.get(i).getStoc();//asta il folosesc maybe in controller
        }



        JPanel content2= new JPanel(new FlowLayout());
       produseNumeChoice=new JComboBox<>(numeProduse);
        content2.add(new JLabel("Selecteaza produsul pe care doresti sa il comanzi: "));
        content2.add(produseNumeChoice);

        JPanel content3= new JPanel(new FlowLayout());
        content3.add(new JLabel("Selecteaza cantitatea: "));
        content3.add(cantitateDeCumparat);
        comanda.setPreferredSize(new Dimension(60,40));
        comanda.setBackground(Color.pink);
        comanda.setFont(new Font("Arial", BOLD,12));
        content3.add(comanda);
        clear.setPreferredSize(new Dimension(60,40));
        clear.setBackground(Color.pink);
        clear.setFont(new Font("Arial", BOLD,12));
        content3.add(clear);

          content.add(content1);
          content.add(content2);
          content.add(content3);

        this.add(content);
    }
    public void addClearListener(ControllerComenzi.ClearListener mal)
    {
        clear.addActionListener(mal);
    }
    public int getCantitateDeCumparat()
    {
        return Integer.parseInt(cantitateDeCumparat.getText());
    }
    public  void setCantitateDeCumparat(String nou)
    {
        cantitateDeCumparat.setText(nou);
    }
    //incerc sa fac rost de valoarea de primul JComboBox, adica de numele clientului care vrea sa comande
    public String getNumeClient()
    {
        return (String) clientiNumeChoice.getItemAt( clientiNumeChoice.getSelectedIndex());
    }
    public String getPrenumeClient()
    {
        return (String) clientiPrenumeChoice.getItemAt( clientiPrenumeChoice.getSelectedIndex());
    }
    public String getNumeProdus()
    {
        return (String) produseNumeChoice.getItemAt( produseNumeChoice.getSelectedIndex());
    }
    public void reset() {
        setCantitateDeCumparat("");
    }
    public void addComandaListener(ControllerComenzi.ComandaListener mal)
    {
        comanda.addActionListener(mal);
    }
}
