package com.texoit.worstmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.texoit.worstmovie.model.Studio;
import com.texoit.worstmovie.repository.StudioRepository;

@Configurable
@Service
public class StudioService {

	@Autowired
	StudioRepository studioRepository;
	
	public Studio getStudioByName(String name) {
		Studio studio = studioRepository.findByName(name);
		if(null == studio) {
			studio = new Studio(name);
			studioRepository.save(studio);
		}
		return studio;
	}
}
