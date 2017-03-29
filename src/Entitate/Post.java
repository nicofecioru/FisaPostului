package Entitate;

import java.io.Serializable;
import java.util.Comparator;

import Repository.HasId;

public class Post implements HasId, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nume;
	private String tip;
	
	/**
	 * contruieste un obiect cu parametrii
	 * @param id: int id-ul postului
	 * @param nume: string numele postului
	 * @param tip: string tipul (fulltime/partime)
	 */
	public Post(int id, String nume, String tip) {
		super();
		this.id = id;
		this.nume = nume;
		this.tip = tip;
	}
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return int id-ul postului
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * seteaza id-ul obiectului cu id-ul nou
	 * @param id: int id-ul postului
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nume == null) ? 0 : nume.hashCode());
		result = prime * result + ((tip == null) ? 0 : tip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		return true;
	}

	/**
	 * 
	 * @return string numele postului 
	 */
	public String getNume() {
		return nume;
	}
	
	/**
	 * seteaza numele postului cu noul nume
	 * @param nume: string noul nume al postului
	 */
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	/**
	 * 
	 * @return tipul postului
	 */
	public String getTip() {
		return tip;
	}
	
	/**
	 * seteaza tipul postului cu noul tip
	 * @param tip: string noul tip al postului
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", nume=" + nume + ", tip=" + tip + "]";
	}	
	public static Comparator<Post> cmpTip = (x,y)->x.getTip().compareTo(y.getTip());
	public static Comparator<Post> cmpNume = (x,y)->x.getNume().compareTo(y.getNume());
}
