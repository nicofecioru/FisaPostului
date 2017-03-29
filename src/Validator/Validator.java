package Validator;

import Validator.MyException;

public interface Validator<E> {
	 public void validate(E entity) throws MyException;

}
