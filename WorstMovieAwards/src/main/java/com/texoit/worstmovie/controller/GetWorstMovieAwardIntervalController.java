package com.texoit.worstmovie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@GetMapping(path = "/getInterval", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, List<AwardInterval>> a() {
		Map<String, List<AwardInterval>> mapOfMinMaxInterval = new HashMap<String, List<AwardInterval>>();
		mapOfMinMaxInterval = worstMovieService.getMinMaxInterval();
		return mapOfMinMaxInterval;
	}
}
