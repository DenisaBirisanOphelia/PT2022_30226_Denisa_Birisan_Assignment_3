package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		//validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());

		clientDAO = new ClientDAO();
	}
	public ClientBLL(Client client) throws NoSuchFieldException, IllegalAccessException {
		validators = new ArrayList<Validator<Client>>();
		//validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());

		clientDAO = new ClientDAO(client);//validez, apoi folosesc constructorul de la clientDAO
	}

	public Client findClientById(int id,String numeColId) {
		Client st = clientDAO.findById(id,numeColId);
		if (st == null) {
			throw new NoSuchElementException("The client with idClient =" + id + " was not found!");
		}
		return st;
	}

	public List<Client> findClientByName(String nume) {
		List<Client> st = clientDAO.findByName(nume);
		if (st == null) {
			throw new NoSuchElementException("The client with name =" + nume + " was not found!");
		}
		return st;
	}
	public List<Client> findAll() {
		List<Client> st = clientDAO.findAll();
		if (st == null) {
			throw new NoSuchElementException("Nu exista clienti in baza de date");
		}
		return st;
	}
	public Client insert(Client t) {
		Client st = clientDAO.insert(t);
		if (st == null) {
			throw new NoSuchElementException("Nu s-a putut insera clientul in baza de date!!");
		}
		return st;
	}
	public int delete(Client t) {
		int st = clientDAO.delete(t);
		if (st == -9)
			throw new NoSuchElementException("Nu s-a putut sterge clientul din baza de date!!");
		return st;
	}
	public Client update(Client t) {
		Client st = clientDAO.update(t);
		if (st == null) {
			throw new NoSuchElementException("Nu s-a putut updata clientul in baza de date!!");
		}
		return st;
	}
}
