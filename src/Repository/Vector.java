package Repository;

public class Vector<type>  {
	private Object all[];
	
	/**
	 * 
	 * @return elementul de pe pozitia poz
	 * @param poz: int pozitia de pe care se ia elementul
	 */
	public type getEl(int poz) {
		@SuppressWarnings("unchecked")
		final type el = (type) all[poz];
		return el;
	}

	private int size;
	int cap=5;
	
	/**
	 * dubleaza capacitatea vectorului si creeaza un nou vector de aceasta capacitate
	 */
	void resize(){
		cap = cap*2;
		Object aux[] = new Object[cap];
		for (int i=0; i<size; i++){
			aux[i] = all[i];
		}
		all=aux;
	}
	
	/**
	 * 
	 * @return int dimensiunea vectorului
	 */
	public int getSize() {
		return size;
	}


	public Vector() {
		all = new Object[cap];
		size = 0;
	}
	
	/**
	 * adauga elementul b la sfarsitul listei
	 * @param b: obiect de tipul celor din lista
	 */
	public void add(Object b){
		if (size == cap){
			resize();
		}
		all[size]=b;
		size++;
	}
	
	/**
	 * sterge elementul de pe pozitia poz
	 * @param poz: int pozitia pe care va fi sters obiectul
	 * precond: poz <= size
	 */
	public void remove(int poz){
		for(int i=poz; i<size-1; i++){
			all[i] = all[i+1];
		}
		size--;
	}
	
	/**
	 * modifica elementul de pe pozitia poz cu elementul e
	 * @param e noul obiect de tipul celor din lista
	 * @param poz int pozitia pe care va fi modificat obiectul
	 * precond: poz <= size
	 */
	public void update(Object e, int poz){
		all[poz] = e;
	}
	

	
	

}
