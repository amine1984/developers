package com.jcdecaux.recruiting.developpers.domain.service.impl;

import com.jcdecaux.recruiting.developpers.domain.model.DeveloperEntity;
import com.jcdecaux.recruiting.developpers.domain.model.LanguageEntity;
import com.jcdecaux.recruiting.developpers.domain.repository.IdevelopersRepository;
import com.jcdecaux.recruiting.developpers.domain.repository.IlanguagesRepository;
import com.jcdecaux.recruiting.developpers.domain.service.IdevelopersService;
import com.jcdecaux.recruiting.developpers.domain.service.exceptions.ServiceException;
import com.jcdecaux.recruiting.developpers.domain.service.messages.ErrorMessages;
import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;
import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class DevelopersServiceImpl implements IdevelopersService{
	
	@Autowired
	private IdevelopersRepository developerRepository;
	@Autowired
	private IlanguagesRepository languageRepository;

	@Override
	public List<Integer> createDevelopers(List<DeveloperDTO> developers) {
		//We can use mapping framework to simplify like Selma or MapStruts
		Iterable<DeveloperEntity> developersIterator = developerRepository.save(developers.stream().map(developer ->{
			developer.setId(null);
			return mapToDeveloperEntity(developer);
		}).collect(Collectors.toList()));
		List<Integer> createdDevelopersIds = new ArrayList<>();
		developersIterator.forEach(developer -> createdDevelopersIds.add(developer.getId()));
		return createdDevelopersIds;
	}

	@Override
	public int updateDevelopers(int developerId , DeveloperDTO developer) {
		DeveloperEntity developerEntity = developerRepository.findById(developerId);
		if(developerEntity!=null){
			developerRepository.save(mapToDeveloperEntity(developer, developerEntity));
			return developerEntity.getId();
		}else throw new ServiceException(ErrorMessages.UPDATE_BAD_PARAMETERS_MESSAGE);
	}
	
	@Override
	public void associateLanguages(Integer idDeveloper, String languageName, String languageVersion){
		DeveloperEntity developerEntity = developerRepository.findById(idDeveloper);
		LanguageEntity languageEntity = languageRepository.findByNameAndVersion(languageName,languageVersion);
		if(developerEntity!=null && languageEntity!=null){
			developerEntity.addLanguage(languageEntity);
			developerRepository.save(developerEntity);
		}else throw new ServiceException(ErrorMessages.ASSOCIATE_BAD_PARAMETERS_MESSAGE);
	}

	@Override
	public Optional<DeveloperDTO> viewDeveloper(Integer idDeveloper) {
		return Optional.ofNullable(mapToDeveloperDTO(developerRepository.findById(idDeveloper)));
	}

	@Override
	public List<DeveloperDTO> getDevelopers(String languageName) {
			return developerRepository.findAllByLanguagesName(languageName).stream().map(this::mapToDeveloperDTO).collect(Collectors.toList());
	}
	
	private DeveloperDTO mapToDeveloperDTO(DeveloperEntity developerEntity){
		DeveloperDTO developerDTO = null;
		if(developerEntity !=null){
			developerDTO = new DeveloperDTO();
			developerDTO.setId(developerEntity.getId());
			developerDTO.setFirstName(developerEntity.getFirstName());
			developerDTO.setLastName(developerEntity.getLastName());
			if(!CollectionUtils.isEmpty(developerEntity.getLanguages())){
				developerDTO.setLanguages(developerEntity.getLanguages().stream().map(language -> {
					LanguageDTO languageDTO = new LanguageDTO();
					languageDTO.setName(language.getName());
					languageDTO.setVersion(language.getVersion());
					return languageDTO;
				}).collect(Collectors.toList()));
			}
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
