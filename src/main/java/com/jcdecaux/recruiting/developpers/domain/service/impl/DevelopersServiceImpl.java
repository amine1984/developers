package com.jcdecaux.recruiting.developpers.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcdecaux.recruiting.developpers.domain.model.DeveloperEntity;
import com.jcdecaux.recruiting.developpers.domain.model.LanguageEntity;
import com.jcdecaux.recruiting.developpers.domain.repository.IdevelopersRepository;
import com.jcdecaux.recruiting.developpers.domain.repository.IlanguagesRepository;
import com.jcdecaux.recruiting.developpers.domain.service.IdevelopersService;
import com.jcdecaux.recruiting.developpers.domain.service.exceptions.ServiceException;
import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;
import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;

@Component
public class DevelopersServiceImpl implements IdevelopersService{
	
	private  static final String UPDATE_BAD_PARAMETRES_MESSAGE = "Developer to uptade not found";
	
	private  static final String ASSOCIATE_BAD_PARAMETRES_MESSAGE = "Developer id or Language id not found";
	
	@Autowired
	private IdevelopersRepository developerRepository;
	@Autowired
	private IlanguagesRepository languageRepository;

	@Override
	public List<DeveloperDTO> createDevelopers(List<DeveloperDTO> developers) {
		//We can use mapping framework to simplify like Selma or MapStruts
		Iterable<DeveloperEntity> developersIterator = developerRepository.save(developers.stream().map(developer ->{
			developer.setId(null);
			return mapToDeveloperEntity(developer);
		}).collect(Collectors.toList()));
		List<DeveloperDTO> createdDevelopers = new ArrayList<>();
		developersIterator.forEach(developer -> createdDevelopers.add(mapToDeveloperDTO(developer)));	
		return createdDevelopers;
	}

	@Override
	public int updateDevelopers(int developerId , DeveloperDTO developer) {
		DeveloperEntity developerEntity = developerRepository.findById(developerId);
		if(developerEntity!=null){
			developerRepository.save(mapToDeveloperEntity(developer, developerEntity));
			return developerEntity.getId();
		}else throw new ServiceException(UPDATE_BAD_PARAMETRES_MESSAGE);
	}
	
	@Override
	public void associateLanguages(Integer idDeveloper, String languageName, String languageVersion){
		DeveloperEntity developerEntity = developerRepository.findById(idDeveloper);
		LanguageEntity languageEntity = languageRepository.findByNameAndVersion(languageName,languageVersion);
		if(developerEntity!=null && languageEntity!=null){
			developerEntity.addLanguage(languageEntity);
			developerRepository.save(developerEntity);
		}else throw new ServiceException(ASSOCIATE_BAD_PARAMETRES_MESSAGE);
	}


	@Override
	public List<DeveloperDTO> getDevelopers(String languageName) {
		return developerRepository.findByLanguage(languageName).stream().map(this::mapToDeveloperDTO).collect(Collectors.toList());
	}
	
	private DeveloperDTO mapToDeveloperDTO(DeveloperEntity developerEntity){
		DeveloperDTO developerDTO = new DeveloperDTO();
		developerDTO.setId(developerEntity.getId());
		developerDTO.setFirstName(developerEntity.getFirstName());
		developerDTO.setLastName(developerEntity.getLastName());
		if(developerEntity.getLanguages()!=null && !developerEntity.getLanguages().isEmpty()){
			developerDTO.setLanguages(developerEntity.getLanguages().stream().map(language -> {
				LanguageDTO languageDTO = new LanguageDTO();
				languageDTO.setName(language.getName());
				languageDTO.setVersion(language.getVersion());
				return languageDTO;
			}).collect(Collectors.toList()));
		}
		return developerDTO;
	}
	private DeveloperEntity mapToDeveloperEntity(DeveloperDTO developerDTO){
		return mapToDeveloperEntity(developerDTO,new DeveloperEntity());
	}
	
	private DeveloperEntity mapToDeveloperEntity(DeveloperDTO developerDTO,DeveloperEntity developerEntity){
		developerEntity.setFirstName(developerDTO.getFirstName());
		developerEntity.setLastName(developerDTO.getLastName());
		developerEntity.setId(developerDTO.getId());
		return developerEntity;
	}

}
