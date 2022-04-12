package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 * //asta e clasa care imi realizeaza conexiunea
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	//numele driverului, initializat prin reflectie??
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	//locatia bazei de date
	private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
	//userul si parola
	private static final String USER = "root";
	private static final String PASS = "denisa12345A*";


	//am o singura conexiune deschisa(obiect de tip private static)
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	//conexiunea va fi plasata intr-un obiect Singleton
	/*
	O clasa Singleton e o clasa care poate avea un singur obiect(instanta) in acelasi timp. Dupa prima instantinere, daca mai
	incercam o data, noua instanta va pointa la prima instanta creata. Deci, orice modificari facem la orice variabila din
	interiorul clasei prin orice instanta, modifica aceleasi atribute si e vizibila prin orice instanta.
	 Key points:
	1. Fa constructorul privat(checked)
	2.Scrie o metoda  statica care are  ca return type obiectul acestei clase (Connection pt noi)=>conceptul de
	LAZY INITIALIZATION
	3. Instanta trebuie sa fie unica si sa fie stocata ca un atribut private static.
	De ce avem nevoie de o clasa Singleton?
	In principal, pentru ca vrem sa restrangem numarul de obiecte create la unul singur(folosit pt multithreading si database
	connection in Java). Clasa Singleton se asigura ca la un moment de timp un singur thread/o singura conexiune poate accesa
	conexiunea realizata.
	-Exemple de clase Singleton: Runtime class, Action Servlet, Service Locator; constructori private si factory methods sunt
	alte exemple.
	-Cum diferentiem o clasa normala de o clasa Singleton?
	    -pentru a instantia o clasa normala, folosim constructorul; pt a initializa o clasa Singleton folosim
	    metoda getInstance();
        -o clasa normala vanishes la finalul executiei unei aplicatii, dar una Singleton nu se distruge la final singura
     -Exista 2 forme a ale desgin patternului Singleton:(?? inca nu imi e clar aici??)
           -early instantiation= instantierea are loc la load time
           -lazy instantiation=insantierea e facute conform requirementului

	//sursa :https://www.geeksforgeeks.org/singleton-class-java/
	 */
	//aici am constructorul privat
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
//metoda pt a crea o conexiune
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}
//metoda pt a obtine conexiunea:metoda statica (factory method care creeaza defapt obictul)
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}


	//metoda pt a inchide o conexiune
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}
//metoda pt a inchide un statement
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}


	//metoda pt a inchide un result set
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}
