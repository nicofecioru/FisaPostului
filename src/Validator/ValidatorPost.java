package Validator;

import Entitate.Post;

public class ValidatorPost implements Validator<Post>{

	@Override
	public void validate(Post entity) throws MyException {
		String err = "";
		if(entity == null){
			err += "Referinta la null ";
		}
		
		if(entity.getId()<=0){
			err += "Id ul postului nu poate fi negativ ";
		}
		
		if(entity.getNume().isEmpty()){
			err += "Numele postului nu poate fi vid ";
		}
		
		if(entity.getTip().isEmpty()){
			err += "Tipul nu poate fi vid ";
		}
		if(!err.isEmpty()){
			throw new MyException(err);
		}
		
	}

}
