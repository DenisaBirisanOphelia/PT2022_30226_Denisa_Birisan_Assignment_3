package bll.validators;

import model.Client;
import model.Produs;

public class ProductStocValidator implements Validator<Produs> {


    public void validate(Produs t) {

        if (t.getStoc() <=0 ) {
            throw new IllegalArgumentException("Nu incerca sa adaugi un stoc imposibil");
        }

    }
}
