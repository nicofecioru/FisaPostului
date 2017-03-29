package Repository;

public interface CrudRepo<type> {
	/**
	 * 
	 * @return vectorul de elemente
	 */
	Iterable<type> getAll() ;

	/**
	 * adauga un obiect  in lista
	 * @param s: type el de adaugat in lista
	 * @throws Exception 
	 */
	
	void addEl(type s) throws Exception;
	
	/**
	 * sterge el de pe pozitia poz
	 * @param poz: int pozitia de pe care va fi sters elementul
	 * @throws Exception 
	 */
	void removeEl(int poz) throws Exception;
	
	/**
	 * modifica el de pe pozitia poz cu s
	 * @param poz: int pozitia obiectului care va fi modificat
	 * @param s: type noul element
	 * @throws Exception 
	 */
	void updateEl(int poz, type s) throws Exception;
	/**
	 * 
	 * @return int dimensiunea vectorului
	 */
	int getLength();
	
	/**
	 * cauta un obiect dupa id
	 * @param id: int id-ul obiectului cautat
	 * @return pozitia pe care se gaseste id-ul daca este gasit, -1 altfel
	 */
	int findById(int id);
	
}
