package com.jcdecaux.recruiting.developpers.service.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.domain.service.InfosService;
import com.jcdecaux.recruiting.developpers.service.rest.InfosResource;

@Service
public class InfosResourceImpl implements InfosResource{

	@Autowired
	private InfosService service;

	public String get() {
		return new StringBuilder()//
				.append("Current version is : ")//
				.append(service.getCurrentVersion())//
				.toString();
	}
	
	
	
}
