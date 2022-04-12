package start;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionStrategy<T> {

	public static void retrieveProperties(Object object) {

		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); 
			Object value;
			try {
				value = field.get(object);
				System.out.println(field.getName() + "=" + value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	public  List<T> retrieveheader(List<T> object) {
              List<T> lista=new ArrayList<>();
			  T obiect= object.get(0);
		for (Field field : obiect.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.getName();
				lista.add((T) value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		}
		return lista;
	}


}



