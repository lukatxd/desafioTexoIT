package com.texoit.worstmovie.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.worstmovie.model.AwardInterval;
import com.texoit.worstmovie.model.Movie;
import com.texoit.worstmovie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	public Map<String, List<AwardInterval>> getMinMaxInterval() {
		final String min = "min";
		final String max = "max";
		final Map<String, Set<AwardInterval>> mapOfMinMaxInterval = new HashMap<>();
		
		mapOfMinMaxInterval.put(min, null);
		mapOfMinMaxInterval.put(max, null);
		
		List<Movie> movies = movieRepository.findAllByWinner(true);
		for(Movie movie : movies) {
			
		}
		
		
		return null;
	}

	public void saveAll(List<Movie> movies) {
		for(Movie movie : movies) {
			Movie movieTemp = movieRepository.findByTitle(movie.getTitle());
			if(null != movieTemp) {
				continue;
			}
			movieRepository.save(movie);	
		}
	}

}
