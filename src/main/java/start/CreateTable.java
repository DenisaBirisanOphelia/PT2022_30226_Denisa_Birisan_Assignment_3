package start;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CreateTable<T> {
    JFrame f;
    // Table
    JTable j;


    //metoda asta vreau sa imi creez un tabel, pt orice lista de obiecte ii dau eu, adica sa mi le organizeze frumos
 public  CreateTable(List<T> listaTabel) throws NoSuchFieldException, IllegalAccessException {
        //creez un frame nou
        f = new JFrame();

        // Frame Title
        f.setTitle("Tabel "+listaTabel.get(0).getClass().getName());


        //cream coloanele(adica headerul) prin reflection
        ReflectionStrategy helper=new ReflectionStrategy();
        int sizeHeader=helper.retrieveheader(listaTabel).size();
        String[] header=new String[sizeHeader];

        int i=0;
        while(i<sizeHeader) {
            header[i] = helper.retrieveheader(listaTabel).get(i).toString();
            i++;
        }
        //fac rost de linii,adica de valorile inregistrarilor
        int nrtuple= listaTabel.size();
        String[][] tuple=new String[nrtuple][sizeHeader];

        i=0;
  int k;
        for (T t : listaTabel) //iterez prin lista de tuple
        { k=0;
            for (Field field : t.getClass().getDeclaredFields()) //iterez prin campurile clasei respective
            {

                //imi ia corect fieldurile,dar daca fac cu for simplu, nu mai merge

                field.setAccessible(true);
                Object value = field.get(t);
                tuple[i][k] = value.toString();
                k++;
            }
            i++;
        }

        //creez un tabel
        JTable j=new JTable(tuple,header);

        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);

    }
}
