package Validator;

public class MyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 629242370328749665L;

	public MyException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return this.getMessage();
	}



}
