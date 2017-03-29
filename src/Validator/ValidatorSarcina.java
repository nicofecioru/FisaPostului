package Validator;

import Entitate.Sarcina;
import Validator.MyException;

public class ValidatorSarcina implements Validator<Sarcina>{

	@Override
	public void validate(Sarcina entity) throws MyException {
		String err = "";
		if(entity == null){
			err += "Referinta la null ";
		}
		
		if(entity.getId()<=0){
			err += "Id ul sarcinii nu poate fi negativ ";
		}
		
		if(entity.getDurata()<=0){
			err += "Durata sarcinii nu poate fi negativa ";
		}
		
		
		if(entity.getDescriere().isEmpty()){
			err += "Numele postului nu poate fi vid ";
		}
		

		if(!err.isEmpty()){
			throw new MyException(err);
		}
		
	}

}

