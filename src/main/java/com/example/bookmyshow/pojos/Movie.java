package com.example.bookmyshow.pojos;
import javax.persistence.*;
@Entity
@Table(name="movies")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="genere")
	private String genere;
	@Column(name="language")
	private String language;
	
	public Movie(){
	
	}
	public Movie(long id, String name, String genere, String language) {
		super();
		this.id = id;
		this.name = name;
		this.genere = genere;
		this.language = language;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
