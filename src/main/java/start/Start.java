package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.ControllerMain;
 import presentation.View;
//in maven au adaugat deja ei librarie speciala ,jar pt a pitea lucra cu MySQL
/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017d
 */
public class Start {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
	public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {

   //aici am modelul, interfata principala si controlleru principal, de restu ma ocup pe parcurs
	   DatabaseModel DBmodel=new DatabaseModel();
	   View DBview=new View(DBmodel);
        DBview.setVisible(true);

		ControllerMain controlly=new ControllerMain(DBmodel,DBview);


	}

}
