package com.jcdecaux.recruiting.developpers.domain.service;

import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IdevelopersService {
	
	 List<Integer> createDevelopers(List<DeveloperDTO> developers);

	 int updateDevelopers(int developerId , DeveloperDTO developer);

	 List<DeveloperDTO> getDevelopers(String langugeName);

	 void associateLanguages(Integer idDeveloper, String languageName, String languageVersion);

	 Optional<DeveloperDTO> viewDeveloper(Integer idDeveloper);


}
