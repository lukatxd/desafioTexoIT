package com.texoit.worstmovie.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.worstmovie.model.AwardInterval;
import com.texoit.worstmovie.services.MovieService;

@RestController
@RequestMapping(path = "/worstMovie")
public class GetWorstMovieAwardIntervalController {

	@Autowired
	MovieService worstMovieService;
	
	@GetMapping(path = "/getMinMaxInterval", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Set<AwardInterval>> a() {
		Map<String, Set<AwardInterval>> mapOfMinMaxInterval = worstMovieService.getMinMaxInterval();
		return mapOfMinMaxInterval;
	}
}
