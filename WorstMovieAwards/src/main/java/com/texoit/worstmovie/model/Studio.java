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
@Table(name="TBLSTUDIOS")
public class Studio {
	
	public Studio() {}

	public Studio(String name, HashSet<Movie> hashSet) {
		this.name = name;
		this.movies = hashSet;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "studios")
	private Set<Movie> movies;
}
