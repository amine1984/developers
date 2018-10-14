package com.jcdecaux.recruiting.developpers.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcdecaux.recruiting.developpers.domain.model.LanguageEntity;
import com.jcdecaux.recruiting.developpers.domain.repository.IlanguagesRepository;
import com.jcdecaux.recruiting.developpers.domain.service.IlanguagesService;
import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;

@Component
public class LanguagesServiceImpl implements IlanguagesService{
	
	@Autowired
	private IlanguagesRepository languagesRepository;

	@Override
	public List<LanguageDTO> createLanguages(List<LanguageDTO> languages) {
		//We can use mapping framework to simplify like Selma or MapStruts
		List<LanguageEntity> languagesEnity = languagesRepository.save(languages.stream().map(language -> {
			LanguageEntity languageEntiry = new LanguageEntity();
			languageEntiry.setName(language.getName());
			languageEntiry.setVersion(language.getVersion());
			return languageEntiry;
		}).collect(Collectors.toList()));
		
		return languagesEnity.stream().map(language -> {
			LanguageDTO languageDTO = new LanguageDTO();
			languageDTO.setName(language.getName());
			languageDTO.setVersion(language.getVersion());
			return languageDTO;
		}).collect(Collectors.toList());
		
	}

}
