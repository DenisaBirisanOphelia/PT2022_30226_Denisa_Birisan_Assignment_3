package bll.validators;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 18;//nu lasam clientii minori sa faca shopping
	private static final int MAX_AGE = 120;
	public ClientAgeValidator()
	{
	}

	public void validate(Client t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}

	}

}
