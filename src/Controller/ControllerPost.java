package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import Entitate.Post;
import Repository.Repository;
import Validator.ValidatorPost;

public class ControllerPost extends Controller<Post>{
	private Repository<Post> rep;
	ValidatorPost v = new ValidatorPost();
	Integer id;

	/**
	 * construieste un controller cu parametrul rep
	 * @param rep: de tip Repository<Post>
	 */
	public ControllerPost(Repository<Post> rep) {
		super();
		this.rep = rep;
		if (rep.getAll().isEmpty())
			id =1;
		else
			id=rep.getAll().get(rep.getLength()-1).getId()+1;
	}
	
	/**
	 * adauga un Post nou cu atributele idPost tip si nume
	 * @param idPost: int id-ul postului
	 * @param nume
	 * @throws Exception 
	 */
	public void addPost( String nume, String tip) throws Exception{
		Post s = new Post(id, nume, tip);		
		v.validate(s);		
		rep.addEl(s);	
		id = id+1;
	}
	
	/**
	 * 
	 * @return lista de posturi
	 */
	public ArrayList<Post> getList(){
		return rep.getAll();
	}
	
	public int find(int id){
		return rep.findById(id);
	}
		
	/**
	 * sterge Post cu id-ul idPost
	 * @param idPost: int id-ul postului care va fi sters
	 * @throws Exception 
	 */
	public void removePost(int idPost) throws Exception{
		rep.removeEl(idPost);
	}
	
	/**
	 * modifica Post cu idPost cu una noua cu atributele newId si newnume si newtip
	 * @param idPost: int id-ul postului care va fi inlocuita
	 * @param newId: id-ul noului post
	 * @param newnume: string numele noului post
	 * @param newtip: string tipul noiului post
	 * @throws Exception 

	 */
	public void updatePost(int idPost,String newnume, String newtip) throws Exception{
		Post s = new Post(idPost , newnume, newtip);	
		v.validate(s);	
		rep.updateEl(idPost, s);
	}
	/**
	 * 
	 * @param den stringul care se cauta in denumiri
	 * @return lista cu posurile care contin stringul in tipuri
	 */
	public List<Post> filterPostbyTip(String den){
		Predicate<Post> p = x->x.getTip().contains(den);
		return filterGeneric(getList(),p );
	}
	
	/**
	 * 
	 * @param a caracterul cu care se doreste sa inceapa numele postului
	 * @return lista cu posturi al caror nume incepe cu cr a
	 */
	public List<Post> filterPostbyDen(String a){
		Predicate<Post> p = x->x.getNume().startsWith(a);
		return filterGeneric(getList(), p);
	}
	
}
