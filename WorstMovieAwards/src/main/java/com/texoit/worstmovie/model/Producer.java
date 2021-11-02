package com.texoit.worstmovie.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPRODUCERS")
public class Producer {

	public Producer(){
		movies = new HashSet<>();
	}
	
	public Producer(String name, Set<Movie> movies) {
		this.name= name;
		this.movies = movies;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "producers")
	private Set<Movie> movies;
	
}
