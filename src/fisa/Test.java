package fisa;

import Controller.ControllerPost;
import Controller.ControllerSarcina;
import Entitate.Post;
import Entitate.Sarcina;
import Repository.Repository;
import Repository.Vector;
import Validator.ValidatorPost;

public class Test {
	
	ValidatorPost v = new ValidatorPost();

	Post p1 = new Post(1, "n1", "f");
	Sarcina s1 = new Sarcina(1, "n1",90);
	Post p2 = new Post(2, "n2", "p");
	Sarcina s2 = new Sarcina(2, "n2",78);
	Sarcina s3 = new Sarcina(3, "n3",56);

	void testPost() {
		
		try{
			v.validate(p1);
		}
		catch(Exception er){
			assert false;
		}
		assert p1.getId() == 1;
		assert p1.getNume() == "n1";
		p1.setId(2);
		p1.setNume("n4");
		p1.setTip("t2");
		assert p1.getId() == 2;
		assert p1.getNume() == "n4";
		assert p1.getTip().equals("t2");
	}
	
	void testSarcina() {
		
		assert s1.getId() == 1;
		assert s1.getDescriere() == "n1";
		s1.setIdSarcina(2);
		s1.setDescriere("n4");
		assert s1.getId() == 2;
		assert s1.getDescriere() == "n4";
	}
	
	void testVector() {
		
		Vector<Sarcina> rep = new Vector<Sarcina>();
		rep.add(s1);
		Sarcina all = (Sarcina) rep.getEl(0);
		assert all.getId() == 2;
		assert all.getDescriere() == "n4";
		
		rep.add(s2);
		all = (Sarcina)rep.getEl(1);
		assert all.getId() == 2;
		assert all.getDescriere() == "n2";
		assert rep.getSize() == 2;
		
		rep.remove(0);
		all = (Sarcina)rep.getEl(0);
		assert all.getId() == 2;
		assert all.getDescriere() == "n2";
		assert rep.getSize() == 1;
		
		rep.update(s2, 0);
		all = (Sarcina)rep.getEl(0);
		assert all.getId() == 2;
		assert all.getDescriere() == "n2";

	}
	
	void testRepo(){

		Repository<Sarcina> rep= new Repository<Sarcina>();
		try{
			rep.addEl(s1);
			assert true;
		}
		catch(Exception e){
			assert false;
		}

		
		Sarcina all = rep.getAll().get(0);
		assert all.getId() == 2;
		assert all.getDescriere() == "n4";
		
		try{
			rep.addEl(new Sarcina(9,"hjj", 78));
			assert true;
		}
		catch(Exception e){
			assert false;
		}
		all = rep.getAll().get(1);
		assert all.getId() == 9;
		assert all.getDescriere() == "hjj";
		
		try{
			rep.removeEl(0);
			assert false;
		}
		catch(Exception e){
			assert true;
		}
		try {
			rep.updateEl(9, new Sarcina(3, "n3",56));
			all = rep.getAll().get(1);
			assert all.getId() == 3;
			assert all.getDescriere() == "n3";
		}
		catch(Exception e) {
			assert false;
		}
		
	}
	
//	void testControllerSarcina() throws Exception{
//		Repository<Sarcina> rep= new Repository<Sarcina>();
//		ControllerSarcina ctr = new ControllerSarcina(rep);
//		ctr.addTask(1, "s1",8);
//		ArrayList<Sarcina> all = ctr.getList();
//		assert all.get(0).getId() == 1;
//		assert all.get(0).getDescriere() == "s1";
//		assert all.size() == 1;
//		
//		ctr.addTask(4, "s2", 9) ;
//		ctr.removeTask(1);
//		all = ctr.getList();
//		assert all.size() == 1;
//		try{
//			ctr.removeTask(5);
//			assert false;
//		}
//		catch(Exception e){
//			assert true;
//		}
//		
//		
//		try {
//			ctr.updateTask(4, 6, "s6", 8);
//		} catch (Exception e1) {
//			assert false;
//		}
//		all = ctr.getList();
//		assert all.get(0).getId() == 6;
//		assert all.get(0).getDescriere() == "s6";
//		try{
//			ctr.updateTask(5, 9, "8", 6);
//			assert  false;
//		}
//		catch(Exception e){
//			assert true;
//		}
//		
//	}	
//	
//	void testControllerPost() throws Exception{
//		Repository<Post> rep= new Repository<Post>();
//		ControllerPost ctr = new ControllerPost(rep);
//		ctr.addPost(1, "s1", "t1");
//		ArrayList<Post> all = ctr.getList();
//		assert all.get(0).getId() == 1;
//		assert all.get(0).getNume() == "s1";
//		assert all.get(0).getTip() == "t1";
//		assert all.size() == 1;
//		
//		ctr.addPost(4, "s2", "t6") ;
//		ctr.removePost(1);
//		all = ctr.getList();
//		assert all.size() == 1;
//		ctr.addPost(8, "s7", "t7");
//		
//		ctr.updatePost(4, 6, "s6", "t9") ;
//		all = ctr.getList();
//		assert all.get(0).getId() == 6;
//		assert all.get(0).getNume() == "s6";
//		assert all.get(0).getTip() == "t9";
//		
//		List<Post> items = ctr.filterPostbyDen("s");
//		assert (items.size() == 2);
//		items = ctr.filterPostbyTip("7");
//		assert items.get(0).getId() == 8;
//		assert items.get(0).getNume() == "s7";
//		assert items.get(0).getTip() == "t7";
//		
//	}		
}
