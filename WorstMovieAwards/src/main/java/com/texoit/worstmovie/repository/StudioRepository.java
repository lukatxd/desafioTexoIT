package com.texoit.worstmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoit.worstmovie.model.Studio;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long>{

	public Studio findByName(String name);
	
}
