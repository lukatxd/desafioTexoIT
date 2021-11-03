package com.texoit.worstmovie.services.movieBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.texoit.worstmovie.model.Movie;
import com.texoit.worstmovie.model.Producer;
import com.texoit.worstmovie.model.Studio;
import com.texoit.worstmovie.services.ProducerService;
import com.texoit.worstmovie.services.StudioService;

@Component
//when called by MovieBuilder it starts a new instance thanks to this annotation below
@Scope("prototype")
public class MovieBuilderObject {

	private Movie movie;
	
	@Autowired
	ProducerService producerService;
	
	@Autowired
	StudioService studioService;
	
	public MovieBuilderObject(){
		movie = new Movie();
	}
	
	public MovieBuilderObject addProducers(String producers) {
		String[] producersNames = producers.split(",");
		for(String name : producersNames) {
			name = sanitizePluralString(name);
			this.addSingleProducer(name);
		}
		return this;
	}
	
	private MovieBuilderObject addSingleProducer(String producerName) {
		Producer producer = producerService.getProducerByName(producerName);
		if(null != producer) {
			movie.getProducers().add(producer);
		}
		return this;
	}
	
	public MovieBuilderObject onYear(int year) {
		movie.setYear(year);
		return this;
	}
	
	public MovieBuilderObject wonAward() {
		movie.setWinner(true);
		return this;
	}

	public MovieBuilderObject addStudios(String producers) {
		String[] studiosNames = producers.split(",");
		for(String name : studiosNames) {
			name = sanitizePluralString(name);
			this.addSingleStudio(name);
		}
		return this;
	}
	
	private MovieBuilderObject addSingleStudio(String studioName) {
		Studio studio = studioService.getStudioByName(studioName);
		if(null != studio) {
			movie.getStudios().add(studio);
		}
		return this;
	}
	
	private String sanitizePluralString(String name) {
		return name.replace(" and ", "").trim();
	}

	public Movie build() {
		return movie;
	}

	public MovieBuilderObject withTitle(String name) {
		if(null != name) {
			name = name.trim();
		}
		else {
			throw new RuntimeException();
		}
		movie.setTitle(name);
		return this;
	}
}
