package com.jcdecaux.recruiting.developpers.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcdecaux.recruiting.developpers.domain.repository.InfosRepository;
import com.jcdecaux.recruiting.developpers.domain.service.InfosService;

@Component
public class InfosServiceImpl implements InfosService {

	@Autowired
	private InfosRepository repository;
	
	public String getCurrentVersion() {
		return repository.get();
	}

}
