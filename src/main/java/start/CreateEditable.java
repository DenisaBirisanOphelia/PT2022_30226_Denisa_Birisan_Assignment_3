package start;

import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

import static java.awt.Font.MONOSPACED;

public class CreateEditable<T> extends JFrame {
    private List<JTextField> listaValori;

    public List<JTextField> getListaValori() {
        System.out.println("fac io cevaa");
        return listaValori;
    }

    public void setListaValori(List<JTextField> listaValori) {
        this.listaValori = listaValori;
    }



    public  CreateEditable(T tupla) throws IllegalAccessException {
          JFrame miniWindow= new JFrame();


        miniWindow.setSize(600,500);
        miniWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miniWindow.setTitle("Interfata cu tabelul "+ tupla.getClass().getSimpleName());
        JPanel content= new JPanel();
        content.setBackground(Color.lightGray);
        content.setLayout(new GridLayout(0,2));
        //butonul de finish
        JButton  butonas= new JButton("Finish edit");
        butonas.setPreferredSize(new Dimension(100,60));
        butonas.setBackground(Color.GREEN);
        butonas.setFont(Font.getFont(MONOSPACED));
        JPanel ei=new JPanel(new GridLayout(0,1));
        ei.add(butonas);
        //adaug in panel butonul
        JPanel content2= new JPanel(new FlowLayout());
        JPanel content1;
        int i=0;
        JTextField noutext;
        T returnElement = null;
        List<JTextField> listaValues= new ArrayList<>();
       for(Field field: tupla.getClass().getDeclaredFields())
       {  field.setAccessible(true);

           content1=new JPanel(new GridLayout(0,2));
           content1.setAlignmentX(Component.CENTER_ALIGNMENT);
           content1.setAlignmentY(Component.CENTER_ALIGNMENT);
          String fieldName= field.getName();
          content1.add(new JLabel(fieldName));
          noutext=new JTextField(10);
          content1.add(noutext);
          listaValues.add(noutext);
           //System.out.println(fieldName+" "+noutext.getText());
          if (i==0)   noutext.setEditable(false);
          //else field.set(returnElement, noutext.getText().); //trebuie sa fac rost de valorile din fiecare textField

           content.add(content1);
          i++;
       }
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
       content.setAlignmentY(Component.CENTER_ALIGNMENT);
       content2.add(content);
       content2.add(ei);
        miniWindow.add(content2);
        miniWindow.setVisible(true);

      butonas.addActionListener(new ActionListener() { //incerc altfel sa fraieresc sistemul
          @Override
          public void actionPerformed(ActionEvent e) {


          }
      });
        /*miniWindow.addWindowListener(new WindowAdapter() {//poate fi de ajutor
            public void windowClosing(WindowEvent e) {
                System.out.println("am inchis fereastra");
            }
        });
        */

    }

}
