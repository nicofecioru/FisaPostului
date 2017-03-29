package Entitate;

import java.io.Serializable;
import java.util.Comparator;

import Repository.HasId;

public class Sarcina implements HasId, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idSarcina;
	private String descriere;
	private Integer durata;
	
	public Integer getDurata() {
		return durata;
	}

	public void setDurata(Integer durata) {
		this.durata = durata;
	}

	/**
	 * contruieste un obiect cu parametrii dati
	 * @param idSarcina: int 
	 * @param descriere: string
	 */
	public Sarcina(int idSarcina, String descriere, Integer durata) {
		super();
		this.idSarcina = idSarcina;
		this.descriere = descriere;
		this.durata =  durata;
	}
	
	/**
	 * 
	 * @return int id-ul sarcinii
	 */
	public Integer getId() {
		return idSarcina;
	}
	
	/**
	 * seteaza id ul sarcinii cu cel nou
	 * @param idSarcina: int noul id
	 */
	public void setIdSarcina(int idSarcina) {
		this.idSarcina = idSarcina;
	}
	
	/**
	 * 
	 * @return string descrierea sarcinii
	 */
	public String getDescriere() {
		return descriere;
	}
	
	/**
	 * seteaza descrierea sarcinii cu cea noua
	 * @param descriere: string noua descriere
	 */
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}


	@Override
	public String toString() {
		return "Sarcina [idSarcina=" + idSarcina + ", descriere=" + descriere + ", durata=" + durata + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriere == null) ? 0 : descriere.hashCode());
		result = prime * result + idSarcina;
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
		Sarcina other = (Sarcina) obj;
		if (descriere == null) {
			if (other.descriere != null)
				return false;
		} else if (!descriere.equals(other.descriere))
			return false;
		if (idSarcina != other.idSarcina)
			return false;
		return true;
	}
	public static Comparator<Sarcina> cmpDescriere = (x,y)->x.getDescriere().compareTo(y.getDescriere());
	public static Comparator<Sarcina> cmpDurata = (x,y)->x.getDurata()-y.getDurata();
}
