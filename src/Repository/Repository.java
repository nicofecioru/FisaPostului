package Repository;

import java.util.ArrayList;

import Validator.MyException;



public class Repository<type extends HasId> implements CrudRepo<type> {
	protected ArrayList<type> all = new ArrayList<type>();

	/**
	 * 
	 * @return vectorul de elemente
	 */
	public ArrayList<type> getAll() {
		
		return all;
	}

	/**
	 * adauga un obiect  in lista
	 * @param s: type el de adaugat in lista
	 * @throws MyException 
	 */
	public void addEl(type s) throws MyException{
		if(findById(s.getId()) != -1){
			throw new MyException("Id dublicat!");
		}
		all.add(s);
		saveData();
	}
	
	/**
	 * sterge el de pe pozitia poz
	 * @param id: int id ul de pe care va fi sters elementul
	 * @throws MyException 
	 */
	public void removeEl(int id) throws MyException{
		int poz = findById(id);
		if(poz == -1){
			throw new MyException("Id inexistent!");
		}
		all.remove(poz);
		saveData();
	}
	
	/**
	 * modifica el de pe pozitia poz cu s
	 * @param id: int id-ul obiectului care va fi modificat
	 * @param s: type noul element
	 * @throws MyException 
	 */
	public void updateEl(int id, type s) throws MyException{
		if(id != s.getId() && findById(s.getId()) != -1 ){
			throw new MyException("Id dublicat!");
		}
		int poz = findById(id);
		if(poz == -1){
			throw new MyException("Id inexistent!");
		}
		all.set(poz, s);
		saveData();
	}
	
	/**
	 * 
	 * @return int dimensiunea vectorului
	 */
	public int getLength() {
		return all.size();
	}

	public int findById(int id){

		for (int i=0; i<all.size(); i++){
			if (all.get(i).getId() == id){
				return i;
			}
		}
		return -1;
	}
	
	public void saveData(){
		
	}
	
}
