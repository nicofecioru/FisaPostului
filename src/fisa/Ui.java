package fisa;//package fisa;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//
//import Controller.ControllerPost;
//import Controller.ControllerSarcina;
//import Entitate.Post;
//import Entitate.Sarcina;
//
//public class Ui {
//	private ControllerSarcina ctrTask;
//	private ControllerPost ctrPost;
//	Scanner scan ;
//
//	/**
//	 * construieste un ui cu parametrii
//	 * @param ctrTask: controller ul de sarcini
//	 * @param ctrPost: controller ul de posturi
//	 */
//	public Ui(ControllerSarcina ctrTask, ControllerPost ctrPost) {
//		super();
//		this.scan = new Scanner(System.in);
//		this.ctrTask = ctrTask;
//		this.ctrPost = ctrPost;
//	}
//
//	/**
//	 * citeste de la tastatura pana primeste un numar
//	 * @return numarul citit
//	 */
//	public int readNumber(){
//		int number;
//		while(!scan.hasNextInt()){
//			scan.nextLine();
//			System.out.println("Trebuie sa introduceti un numar!");
//		}
//		number=scan.nextInt();
//		scan.nextLine();
//		return number;
//
//	}
//
//
//	/**
//	 * afiseaza toate sarcinile
//	 */
//	public void showAllTasks() {
//		ArrayList<Sarcina> all = ctrTask.getList();
//		for (Sarcina task: all){
//			System.out.println(task);
//		}
//	}
//
//	/**
//	 * adauga o sarcina noua cu atribute citite de la tastatura
//	 * daca mai exista o sarcina cu acel id afiseaza un mesaj
//	 */
//	public void adaugaSarcina(){
//		System.out.println("introduceti id-ul: ");
//		int idSarcina = readNumber();
//
//		System.out.println("introduceti descrierea: ");
//		String descriere = scan.nextLine();
//		
//		System.out.println("introduceti durata: ");
//		int durata = readNumber();
//		try{
//			ctrTask.addTask(idSarcina, descriere, durata);
//		} catch(Exception e) {
//			System.out.println(e);
//		}
//	}
//
//	/**
//	 * sterge o sarcina cu id-ul citit de la tastatura daca exista, altfel afiseaza un mesaj
//	 */
//	public void stergeSarcina() {
//		showAllTasks();
//		System.out.println("introduceti id-ul sarcinii pe care doriti sa o stergeti: ");
//		int idSarcina = readNumber();
//		try {
//			ctrTask.removeTask(idSarcina) ;
//		}
//		catch(Exception e){
//			System.out.println(e);
//		}
//	}
//
//
//	/**
//	 * modifica o sarcina cu id-ul citit la tastatura, daca exista, cu una cu atributele citite de la tastatura
//	 * altfel afiseaza un mesaj
//	 */
//	public void updateSarcina(){
//		showAllTasks();
//		System.out.println("introduceti id-ul sarcinii pe care doriti sa o modificati: ");
//		int idSarcina = readNumber();
//
//		System.out.println("introduceti id-ul noii sarcinii: ");
//		int idS = readNumber();
//
//		System.out.println("introduceti descrierea: ");
//		String descriere = scan.nextLine();
//		
//		System.out.println("introduceti durata: ");
//		int durata = readNumber();
//		try{
//				ctrTask.updateTask(idSarcina, idS, descriere, durata);
//		}
//		catch(Exception e) {
//				System.out.println("Id dublicat!");
//			
//		}
//	}
//	
//	/**
//	 * filtreaza un post dupa tip; se citeste un string de la tastaura si se afiseaza sortate alfabetic dupa nume postarile 
//	 * al caror tip contin stringul
//	 */
//	void filterPostByTip(){
//		
//		System.out.println("introduceti stringul cautat: ");
//		String str = scan.nextLine();
//		List<Post> filtr = ctrPost.filterPostbyTip(str);
//		Collections.sort(filtr, Post.cmpNume);
//		filtr.forEach(x->System.out.println(x));
//		
//	}
//	
//	/**
//	 * filtreaza un post dupa nume 
//	 * se citeste un caracter de la tastatura si se afiseaza sortate alfabetic dupa tip postarile al caror nume 
//	 * incepe cu acel caracter
//	 */
//	void filterPostByNume(){
//		
//		System.out.println("introduceti caracterul: ");
//		String str = scan.nextLine();
//		List<Post> filtr = ctrPost.filterPostbyDen(str);
//		Collections.sort(filtr, Post.cmpTip);
//		filtr.forEach(x->System.out.println(x));
//		
//	}
//	
//	/**
//	 * filtreaza un task dupa descriere; se citeste un string de la tastaura si se afiseaza sortate crescator dupa durata sarcinile
//	 * al caror descriere contin stringul
//	 */
//	void filterTaskByDescr(){
//		
//		System.out.println("introduceti stringul: ");
//		String str = scan.nextLine();
//		List<Sarcina> filtr = ctrTask.filterTaskbyDescr(str);
//		Collections.sort(filtr, Sarcina.cmpDurata);
//		filtr.forEach(x->System.out.println(x));
//		
//	}
//	
//	/**
//		filtreaza un task dupa durata; se citeste un int de la tastaura si se afiseaza sortate alfabetic dupa descriere sarcinile
//	 * al caror durata este mai mica decat intul 
//	 */
//	void filterTaskByDurata(){
//		
//		System.out.println("introduceti durata: ");
//		int durata = readNumber();
//		List<Sarcina> filtr = ctrTask.filterTaskbyDurata(durata);
//		Collections.sort(filtr, Sarcina.cmpDescriere);
//		filtr.forEach(x->System.out.println(x));
//		
//	}
//
//	/**
//	 * afiseaza toate sarcinile
//	 */
//	public void showAllPosts() {
//		ArrayList<Post> all = ctrPost.getList();
//		for (Post Post: all){
//			System.out.println(Post);
//		}
//	}
//
//	/**
//	 * adauga un Post nou cu atribute citite de la tastatura
//	 * daca mai exista un Post cu acel id afiseaza un mesaj
//	 */
//	public void adaugaPost(){
//		System.out.println("introduceti id-ul: ");
//		int idPost = readNumber();
//
//		System.out.println("introduceti numele: ");
//		String nume = scan.nextLine();
//		System.out.println("introduceti tipul: ");
//		String tip = scan.nextLine();
//		try{
//			ctrPost.addPost(idPost, nume, tip);
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//	}
//
//	/**
//	 * sterge un Post cu id-ul citit de la tastatura daca exista, altfel afiseaza un mesaj
//	 */
//	public void stergePost() {
//		showAllPosts();
//		System.out.println("introduceti id-ul postului pe care doriti sa il stergeti: ");
//		int idPost = readNumber();
//		try{
//			ctrPost.removePost(idPost);
//		}
//		catch(Exception err){
//			System.out.println(err);
//		}
//	}
//
//	/**
//	 * modifica un Post cu id-ul citit la tastatura, daca exista, cu unul cu atributele citite de la tastatura
//	 * altfel afiseaza un mesaj
//	 */
//	public void updatePost(){
//		showAllPosts();
//		System.out.println("introduceti id-ul sarcinii pe care doriti sa o modificati: ");
//		int idPost = readNumber();
//		
//		System.out.println("introduceti id-ul noii sarcinii: ");
//		int idS = readNumber();
//			
//		System.out.println("introduceti numele: ");
//		String nume = scan.nextLine();
//		System.out.println("introduceti tipul: ");
//		String tip = scan.nextLine();
//		try{
//			ctrPost.updatePost(idPost, idS, nume, tip);
//		}
//		catch(Exception e){
//			System.out.println(e);
//		}
//	}
//	/**
//	 * citeste optiunile utilizatorului si afiseaza meniului
//	 */
//	public void showMenu() {
//
//		while(true){
//			System.out.println("Introduceti o optiune: \n 0.Parasiti aplicatia \n 1.Vezi toate sarcinile\n 2.Adauga sarcina\n 3.Sterge sarcina\n 4.Modifica sarcina");
//			System.out.println(" 5.Vezi toate posturile\n 6.Adauga postul\n 7.Sterge postul\n 8.Modifica postul\n 9.Filtreaza posturi dupa substring continut in tip\n 10.Filtreaza posturi cu nume care incepe cu un caracter");
//			System.out.println(" 11.Filtreaza sarcini dupa un string continut in descriere\n 12.Filtreaza sarcini cu durata mai mica de un numar dat de ore\n");
//			int option = readNumber();
//			switch(option){
//			case 0:
//				System.out.println("Ati parasit aplicatia!");
//				return;
//			case 1:
//				showAllTasks();
//				break;
//			case 2:
//				adaugaSarcina();
//				break;
//			case 3:
//				stergeSarcina();
//				break;
//			case 4:
//				updateSarcina();
//				break;
//			case 5:
//				showAllPosts();
//				break;
//			case 6:
//				adaugaPost();
//				break;
//			case 7:
//				stergePost();
//				break;
//			case 8:
//				updatePost();
//				break;
//			case 9:
//				filterPostByTip();
//				break;
//			case 10:
//				filterPostByNume();
//				break;
//			case 11:
//				filterTaskByDescr();
//				break;
//				
//			case 12:
//				filterTaskByDurata();
//				break;
//			default:
//				System.out.println("Optiune invalida");
//				break;
//
//			}
//		}
//	}
//
//}
