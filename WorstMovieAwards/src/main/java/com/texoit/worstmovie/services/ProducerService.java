package com.texoit.worstmovie.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.texoit.worstmovie.model.Producer;
import com.texoit.worstmovie.repository.ProducerRepository;

@Configurable
@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	public Producer getProducerByName(String name) {
		Producer producer = producerRepository.findByName(name);
		if(null == producer) {
			producer = new Producer(name, new HashSet<>());
			producerRepository.save(producer);
		}
		return producer;
	}
}
