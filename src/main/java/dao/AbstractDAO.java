package dao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;
import javax.swing.*;
import static java.awt.Font.MONOSPACED;
import static java.lang.Integer.parseInt;
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;
	@SuppressWarnings("unchecked")
	public AbstractDAO() { //aici obtin clasa specifica aka tabelul pe care lucrez
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public AbstractDAO(T element) throws IllegalAccessException, NoSuchFieldException { //aici creez un obiect de tipul <Cliwnt> sau <Produs> etc
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		//aici trebuie sa apelez rez= new Client(Client element)=>adica de campurile din element
		//fac rost de fields din element
		for (Field field : element.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(element);
			field.set(element, value);
		}
	}
	private String createSelectQuery(String field) {//aici numele clasei trebuie sa fie acelasi cu al tabelului
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + "=?");
		return sb.toString();
	}
	private   String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
	private String createInsertStatement(T tupla) {//ii dau lista de obiecte si face headerul, similar cu createHeaderS
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT into ");
		sb.append(type.getSimpleName());
		sb.append(" values ( ");
		//calculez nr de fields
		int sizeHeader=0;
		for(Field field: type.getDeclaredFields())
			sizeHeader++;

		//System.out.println(sizeHeader);
	    //fac pt fiecare field
		int i=1;
		for(Field field: type.getDeclaredFields()) {
			field.setAccessible(true);
			Object value = null;
			try {
				value = field.get(tupla);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			//String value = "\"ROM\"";
			//if (value instanceof Integer)//daca e intreg, nu pun ghilimele
				 sb.append("?");
			//else sb.append("'?'");
			if (i!=sizeHeader) sb.append(",");
			i++;

		}
		sb.append(")");
		//System.out.println(sb.toString());
//construieste bn statementul
		return sb.toString();
	}
	private String createDeleteStatement(T tupla)
	{ StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("from ");
		sb.append(type.getSimpleName());
		int i=0;
		String fieldName=null;
		for(Field f:tupla.getClass().getDeclaredFields()) {
			if (i == 0) fieldName = f.getName();
			i++;//aici fac rost de primul field,dupa el fac stergerea
		}
		sb.append(" WHERE " + fieldName + " =?");
		return sb.toString();
	}
	private String createUpdateStatement(T tupla)
	{  StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		int i=0;
		String fieldName=null;
		int sizeHeader=0;
		for(Field field:tupla.getClass().getDeclaredFields())
			sizeHeader++;
		for(Field field:tupla.getClass().getDeclaredFields()) {
			if (i == 0) fieldName = field.getName(); //aici fac rost de primul field, dupa care fac update-ul
			else
			{ if(i!=sizeHeader-1)
				   sb.append(field.getName()+"=?, ");
				else
					sb.append(field.getName()+"=?");
			}
			i++;
		}
		sb.append(" WHERE " + fieldName + "=?");
		return sb.toString();
	}
  //metoda asta o modific incat sa fie un select all:teoretic, e scrisa, acuma numa de testat mai e
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);//aici returnez o lista de obiecte, pe care le creeaza metoda deja scrisa de ei
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {ConnectionFactory.close(resultSet);
			        ConnectionFactory.close(statement);
			        ConnectionFactory.close(connection);
		}
		return null;
	}
	public T findById(int id,String numeColId) {//aici cica inlocuiesc id cu PK(mai generic)
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(numeColId);
		try {connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);


			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);//aici returnez doar un lement,deoarece BD are un idClient unic
		} catch (SQLException e) {

			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	public List<T> findByName(String nume) {//aici cica inlocuiesc id cu PK(mai generic)
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("nume");
		try {connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);


			statement.setString(1, nume);
			//System.out.println(statement.toString());
			resultSet = statement.executeQuery();

			return createObjects(resultSet);//aici returnez doar un lement,deoarece BD are un idClient unic
		} catch (SQLException e) {

			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
		} finally {ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	//avand resultSet-ul, creeaza lista de obiecte de tip <T>
	private   List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {	//pt fiecare obiect din resultSet
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance(); 	//creez o noua instanta de tip T
				for (Field field : type.getDeclaredFields()) {//pt fiecare field
					String fieldName = field.getName();//obtine numele fieldului
					Object value = resultSet.getObject(fieldName);//obtine valoarea in resultSet
					//System.out.println(fieldName+" "+value);

					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					//System.out.println(fieldName+" "+type.toString());
					Method method = propertyDescriptor.getWriteMethod();
					//System.out.println(instance.toString());
					method.invoke(instance, value);
				}
				list.add(instance);	//adauga si in lista mea de obiecte de tip T,noul obiect creat

			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
	public T insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertStatement(t);
		try {connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i=1;
			for (Field field : type.getDeclaredFields() ) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object value=field.get(t);
				if(value.getClass().getSimpleName().equals("String")==true)
					statement.setString(i, value.toString());
				else statement.setInt(i, parseInt(value.toString()));
				i++;
			}
			statement.executeUpdate();
			return t;//aici returnez o lista de obiecte, pe care mi le creeaza din result set metoda deja scrisa de ei
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insertNewClient" + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	public T update(T tupla) {//aici creez o fereastra separata in care sa inserez valorile de modificat
		JFrame miniWindow= new JFrame();
		miniWindow.setSize(600,500);
		miniWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		miniWindow.setTitle("Interfata cu tabelul "+ tupla.getClass().getSimpleName());
		JPanel content= new JPanel(new GridLayout(0,2));
		content.setBackground(Color.lightGray);
		JButton  butonas= new JButton("Finish edit");//butonul de finish
		butonas.setPreferredSize(new Dimension(100,60));
		butonas.setBackground(Color.GREEN);
		butonas.setFont(Font.getFont(MONOSPACED));
		JPanel ei=new JPanel(new GridLayout(0,1));
		ei.add(butonas);
		JPanel content2= new JPanel(new FlowLayout());//adaug in panel butonul
		JPanel content1;
		int i=0;
		JTextField noutext;
		List<JTextField> listaValues= new ArrayList<>();
		for(Field field: tupla.getClass().getDeclaredFields())
		{  field.setAccessible(true);
			content1=new JPanel(new GridLayout(0,2));
			String fieldName= field.getName();
			content1.add(new JLabel(fieldName));
			noutext=new JTextField(10);
			content1.add(noutext);
			listaValues.add(noutext);
			if (i==0)   noutext.setEditable(false);
			content.add(content1);
			i++;
		}
		content2.add(content);
		content2.add(ei);
		miniWindow.add(content2);
		miniWindow.setVisible(true);
		butonas.addActionListener(new ActionListener() { //incerc altfel sa fraieresc sistemul
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				String query = createUpdateStatement(tupla);
				try {connection = ConnectionFactory.getConnection();
					statement = connection.prepareStatement(query);
					int nr=1,sizeHeader=0;
					for (Field field : type.getDeclaredFields())
						sizeHeader++;
					for (Field field : type.getDeclaredFields() ) {
						field.setAccessible(true);
						String fieldName = field.getName();
						Object value=listaValues.get(nr-1).getText();
						Object comparing=field.get(tupla).getClass().getSimpleName();
					if(nr==1) {
						statement.setInt(sizeHeader, (Integer) field.get(tupla));
					}
              else  {if (comparing.getClass().getSimpleName().equals("String") == true)
					  statement.setString(nr-1, value.toString());
				     else
					  statement.setInt(nr-1, parseInt(value.toString()));
			         }
						nr++;
					}
					statement.executeUpdate();
				} catch (SQLException exp) {
					LOGGER.log(Level.WARNING, type.getName() + "DAO:updateClient" + exp.getMessage());
				} catch (IllegalAccessException exp) {
					exp.printStackTrace();
				} finally {ConnectionFactory.close(resultSet);
					      ConnectionFactory.close(statement);
					      ConnectionFactory.close(connection);
				}
			}
		});
		return tupla;
	}
	public int delete(T t)
	{   Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteStatement(t);
		try { connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i=0,value=0;
			for(Field f: t.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (i == 0) value= (int) f.get(t);
					i++;
			}
			statement.setInt(1,value);
			statement.executeUpdate();
			return 0;//0=succes, -9=esec
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteClient" + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return -9;
	}
}
