package com.texoit.worstmovie.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.worstmovie.model.AwardInterval;
import com.texoit.worstmovie.model.Movie;
import com.texoit.worstmovie.model.Producer;
import com.texoit.worstmovie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	public Map<String, Set<AwardInterval>> getIntervals() {
		Map<Producer, Movie> mapProducerLastAward = new HashMap<>();
		Set<AwardInterval> minIntervalAwards = new HashSet<>();
		Set<AwardInterval> maxIntervalAwards = new HashSet<>();
		
		List<Movie> movies = movieRepository.findAllByWinner(true);
		
		int minInterval = Integer.MAX_VALUE;
		int maxInterval = -1;
		
		for(Movie movie : movies) {
			for(Producer producer : movie.getProducers()) {
				//Since "producers" is a collection, could not avoid getting this to an O(n*m) implementation
				//if it was a single producer I could manage making it O(n)
				//Map<Producer, Movie> movieProducers = movie.getProducers().stream().collect(Collectors.toMap(x->x, x-> movie));
				//also feel like all this could be implemented with a looong stream, but I prefer readability
				
				Movie lastAward = mapProducerLastAward.get(producer);
				if(null == lastAward) {
					mapProducerLastAward.put(producer, movie);
					continue;
				}else {
					final int interval = movie.getYear() - lastAward.getYear();
					if(interval < minInterval) {
						minInterval = interval;
						minIntervalAwards.clear();
						minIntervalAwards.add(new AwardInterval(producer, interval, lastAward.getYear(), movie.getYear()));
					}else if(interval == minInterval) {
						minIntervalAwards.add(new AwardInterval(producer,interval, lastAward.getYear(), movie.getYear()));
					}
					
					if(interval == maxInterval) {
						maxIntervalAwards.add(new AwardInterval(producer,interval,lastAward.getYear(),movie.getYear()));
					}else if(interval > maxInterval) {
						maxInterval = interval;
						maxIntervalAwards.clear();
						maxIntervalAwards.add(new AwardInterval(producer, interval, lastAward.getYear(), movie.getYear()));					
					}	
				}
			}
		}
		
		Map<String, Set<AwardInterval>> mapMinMaxInterval = new HashMap<>();
		mapMinMaxInterval.put("min", minIntervalAwards);
		mapMinMaxInterval.put("max", maxIntervalAwards);
		return mapMinMaxInterval;
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
