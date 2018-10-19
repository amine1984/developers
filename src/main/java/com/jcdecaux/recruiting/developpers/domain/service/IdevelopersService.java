package com.jcdecaux.recruiting.developpers.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;

@Service
public interface IdevelopersService {
	
	public List<DeveloperDTO> createDevelopers(List<DeveloperDTO> developers);
	
	public int updateDevelopers(int developerId , DeveloperDTO developer);
	
	public Optional<List<DeveloperDTO>> getDevelopers(String langugeName);
	
	public void associateLanguages(Integer idDeveloper, String languageName, String languageVersion);	
	

}
