package com.texoit.worstmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoit.worstmovie.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
	
	public Producer findByName(String name);
}
