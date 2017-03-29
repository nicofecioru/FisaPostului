package Entitate;

import java.io.Serializable;

import Repository.HasId;

public class ElementSarcina implements HasId, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Post post;
	private Sarcina sarcina;
	
	public ElementSarcina(Integer id, Post post, Sarcina sarcina) {
		super();
		this.id = id;
		this.post = post;
		this.sarcina = sarcina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Sarcina getSarcina() {
		return sarcina;
	}

	public void setSarcina(Sarcina sarcina) {
		this.sarcina = sarcina;
	}
	
	

	
}
