package com.texoit.worstmovie.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "TBLMOVIES")
public class Movie {

	public Movie() {
		this.studios = new HashSet<>();
		this.producers = new HashSet<>();
	}

	public Movie(int year, String title, Set<Studio> studios, Set<Producer> producers, boolean winner) {
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private int year;
	@Column
	private String title;

	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="TBLMOVIESTUDIOS", joinColumns=
    {@JoinColumn(name="movieId")}, inverseJoinColumns=
      {@JoinColumn(name="studioId")})
	private Set<Studio> studios;
	
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="TBLMOVIEPRODUCERS", joinColumns=
    {@JoinColumn(name="movieId")}, inverseJoinColumns=
      {@JoinColumn(name="producerId")})
	private Set<Producer> producers;
	
	@Column
	private boolean winner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Studio> getStudios() {
		return studios;
	}

	public void setStudios(Set<Studio> studios) {
		this.studios = studios;
	}

	public Set<Producer> getProducers() {
		return producers;
	}

	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, producers, studios, title, winner, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(id, other.id) && Objects.equals(producers, other.producers)
				&& Objects.equals(studios, other.studios) && Objects.equals(title, other.title)
				&& winner == other.winner && year == other.year;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", year=" + year + ", title=" + title + ", studios=" + studios + ", producers="
				+ producers + ", winner=" + winner + "]";
	}
}
