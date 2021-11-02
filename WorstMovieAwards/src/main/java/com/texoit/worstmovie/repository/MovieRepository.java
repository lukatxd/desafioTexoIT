package com.texoit.worstmovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoit.worstmovie.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	public Movie findByTitle(String title);
	
	public List<Movie> findAllByWinner(boolean winner);

}
