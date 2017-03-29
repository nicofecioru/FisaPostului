package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import Repository.HasId;
import Repository.Repository;

public class Controller<E extends HasId> {
	
	
	
	private Repository<E> rep = new Repository<E>();
	/**
	 * 
	 * @param lista: lista initiala care va fi filtrata
	 * @param p: criteriul de filtrare
	 * @return lista filtrata
	 */
	public <E> List<E> filterGeneric(List<E> lista, Predicate<E> p) {
		ArrayList<E> list = new ArrayList<E>();
		lista.stream().filter(p).forEach(x->list.add(x));
		return list;
	}
	
	public int find(int id){
		return rep.findById(id);
	}
	
	
	

	
	
}
