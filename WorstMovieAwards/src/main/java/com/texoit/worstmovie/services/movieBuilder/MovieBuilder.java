package com.texoit.worstmovie.services.movieBuilder;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieBuilder {
	
	@Autowired
	private ObjectFactory<MovieBuilderObject> movieBuilder;
	
	public MovieBuilderObject startBuilder() {
		return movieBuilder.getObject();
	}

}
