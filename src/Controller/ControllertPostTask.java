package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import Entitate.ElementSarcina;
import Entitate.Post;
import Entitate.Sarcina;
import Observer.Observer;
import Repository.Repository;
import Validator.MyException;

public class ControllertPostTask implements Observer {
	Integer id;
	Repository<ElementSarcina> rep;
	Repository<Post> repPost;
	Repository<Sarcina> repTask;
	Hashtable<Integer,Integer> top;
	
	
	public ControllertPostTask(Repository<ElementSarcina> rep, 	Repository<Post> repPost, Repository<Sarcina> repTask) {
		
		super();
		this.rep = rep;
		this.repPost = repPost;
		this.repTask = repTask;
		top = new Hashtable<Integer, Integer>();
		
		update();
		if (rep.getAll().isEmpty())
			id =1;
		else{
			Integer value, n;
			for (ElementSarcina el :rep.getAll()){
				   value = el.getSarcina().getId();
				   n = top.get(el.getSarcina().getId());
				   if (n != null) {
				     top.put(el.getSarcina().getId(), n+1);
				   }
				   else {
					 top.put(el.getSarcina().getId(), 1);
				   }
				   
			}
			id=rep.getAll().get(rep.getLength()-1).getId()+1;
		}

	}
	
	/**
	 * 
	 * @return lista de legaturi
	 */
	public ArrayList<ElementSarcina> getList(){
		return rep.getAll();
	}
	
	/**
	 * adauga o sarcina noua cu atributele idSarcina si descr
	 * @param idSarcina: int id-ul Sarcinei
	 * @param descr
	 * @throws MyException 
	 * @throws Exception 
	 */
	public void addRel(Post post, Sarcina sarcina) throws MyException {
		if (exists(post.getId(),sarcina.getId())){
			throw new MyException("Relatie existenta!");
		}
		ElementSarcina s = new ElementSarcina(id, post, sarcina);
		rep.addEl(s);
		Integer value = sarcina.getId();
		if (top.containsKey(value)){
			top.put(value, top.get(value)+1);
		}
		else{
			top.put(value, 1);
		}
		id=id+1;
		
	}
	
	
	/**
	 * sterge sarcina cu id-ul idSarcina
	 * @param idSarcina: int id-ul sarcinii care va fi stearsa
	 * @throws MyException 
	 * @throws Exception 
	 */
	public void removeRel(int post, int sarcina) throws MyException{
		int id=-1;
		for(ElementSarcina x: getList()){
		if(x.getPost().getId()==post&&x.getSarcina().getId()==sarcina)
				id = x.getId();

		}
		if (top.get(sarcina) == 1){
			top.remove(sarcina);
		}
		else {
			top.put(sarcina, top.get(sarcina)-1);
		}
		rep.removeEl(id);

	}
	
	/**
	 * cauta toate sarcinile atribuite unui post
	 * @param idPost id ul dupa care se cauta sarcinele
	 * @return lista de sarcini atribuite unui post
	 */
	public ArrayList<Sarcina> showAll(int idPost){
		ArrayList<Sarcina> list = new ArrayList<Sarcina>();
		getList().forEach(x->{
			if(x.getPost().getId()==idPost){
				list.add(x.getSarcina());
			}
		});
		return list;
	}
	
	/**
	 * sterge relatiile care contin sarcini sau posturi inexistente 
	 * inlocuieste toate sarcinile sau posturile cu cele curente
	 */
	public void update(){
		
		getList().removeIf(x->repPost.findById(x.getPost().getId()) == -1 || repTask.findById(x.getSarcina().getId()) == -1);
	

		getList().forEach(x->{
			x.setPost(repPost.getAll().get((repPost.findById(x.getPost().getId()))));
			x.setSarcina(repTask.getAll().get((repTask.findById(x.getSarcina().getId()))));
		});
	}
	
	public Post findPost(int id){
		if( repPost.findById(id)==-1)
				return null;
		return repPost.getAll().get(repPost.findById(id));
	}
	
	public boolean exists(int idPost, int idSarcina){
		for(ElementSarcina x: getList()){
			if(x.getPost().getId()==idPost&&x.getSarcina().getId()==idSarcina)
				return true;
		}
		return false;
	}
	
	/**
	 * creaza un hashtable cu top 3 cele mai solicitate sarcini si nr de programatori inscrisi la ele
	 * @return
	 */
	public Hashtable<Integer, Integer> getTopN(int n){
		Hashtable<Integer, Integer> top3 = new  Hashtable<Integer, Integer>();
		ArrayList<Integer> values = Collections.list(top.elements());
		Collections.sort(values);
		Collections.reverse(values);
	
		for (int i=0; i<Math.min(n, values.size()); i++){
			for (int key : top.keySet()){
				if (top.get(key) == values.get(i)){
					top3.put(key, values.get(i));
					if(i==Math.min(n, values.size())-1)
						break;
				}
			}
			
		}
		return top3;
		
	}
	
	
	
	

}
