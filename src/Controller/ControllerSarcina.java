package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import Entitate.Sarcina;
import Observer.Observable;
import Observer.Observer;
import Repository.Repository;
import Validator.ValidatorSarcina;



public class ControllerSarcina extends Controller<Sarcina> implements Observable{
	private Repository<Sarcina> rep;
	private ValidatorSarcina v = new ValidatorSarcina();
	Integer id;

	/**
	 * construieste un controller cu parametrul rep
	 * @param rep: de tip Repository<Sarcina>
	 */
	public ControllerSarcina(Repository<Sarcina> rep) {
		super();
		this.rep = rep;
		if (rep.getAll().isEmpty())
			id =1;
		else
			id=rep.getAll().get(rep.getLength()-1).getId()+1;

	}
	
	/**
	 * adauga o sarcina noua cu atributele idSarcina si descr
	 * @param idSarcina: int id-ul Sarcinei
	 * @param descr
	 * @throws Exception 
	 */
	public void addTask( String descr, int durata) throws Exception{
		Sarcina s = new Sarcina(id, descr, durata);
		v.validate(s);
		rep.addEl(s);
		id = id+1;
		this.notifyObservers();
		
	}
	
	/**
	 * 
	 * @return lista de sarcini
	 */
	public ArrayList<Sarcina> getList(){
		return rep.getAll();
	}
	
	
	/**
	 * sterge sarcina cu id-ul idSarcina
	 * @param idSarcina: int id-ul sarcinii care va fi stearsa
	 * @throws Exception 
	 */
	public void removeTask(int idSarcina) throws Exception{

		rep.removeEl(idSarcina);
		this.notifyObservers();

	}
	
	/**
	 * modifica sarcina cu idSarcina cu una noua cu atributele newId si newDescr
	 * @param idSarcina: int id-ul sarcinei care va fi inlocuita
	 * @param newId: id-ul noii sarcini
	 * @param newDescr: string descrierea noii sarcini
	 * @throws Exception 

	 */
	public void updateTask(int idSarcina, String newDescr, int durata) throws Exception{
		Sarcina s = new Sarcina(idSarcina , newDescr, durata);
		v.validate(s);	
		rep.updateEl(idSarcina, s);
		this.notifyObservers();
	}
	
	/**
	 * 
	 * @param a stringul care se cauta in descriere tipului
	 * @return lista de task uri al caror descriere contin stringul a
	 */
	public List<Sarcina> filterTaskbyDescr(String a){
		Predicate<Sarcina> p = x->x.getDescriere().contains(a);
		return filterGeneric(getList(),p );
	}
	
	/**
	 * 
	 * @param durata int ul cu care se compara durata task urilor
	 * @return task urile cu durata mai mica decat durata data ca parametru 
	 */
	public List<Sarcina> filterTaskbyDurata(int durata){
		Predicate<Sarcina> p = x->x.getDurata()< durata;
		return filterGeneric(getList(),p );
	}

	protected ArrayList<Observer> observers = new ArrayList<Observer>();
    /**
     * Register an observer.
     * @param o the observer
     */
    public void addObserver(Observer o){
    	observers.add(o);
    }
    /**
     * Unregister an observer.
     * @param o the observer
     */
    public void removeObserver(Observer o){
    	observers.removeIf(x->x==o);
    }
    
    
    public void notifyObservers(){
    	observers.forEach(x->x.update());
    }
    
    public Sarcina findTask(int id){
    	return getList().get(rep.findById(id));
    }




}
