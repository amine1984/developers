package com.jcdecaux.recruiting.developpers.domain.service.impl;

import java.util.ArrayList;
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
	public List<Integer> createLanguages(List<LanguageDTO> languages) {
		List<LanguageEntity> languagesEntity = languagesRepository.save(languages.stream().map(this::mapToLanguageEntity).collect(Collectors.toList()));
		List<Integer> idLanguages = new ArrayList<>();
		languagesEntity.forEach(languageEntity -> idLanguages.add(languageEntity.getId()));
		return idLanguages;
	}

	@Override
	public LanguageDTO viewLanguage(Integer idLanguage) {
		return mapToLanguageDTO(languagesRepository.findById(idLanguage));
	}

	private LanguageEntity mapToLanguageEntity(LanguageDTO languageDTO){
		LanguageEntity languageEntity = new LanguageEntity();
		languageEntity.setName(languageDTO.getName());
		languageEntity.setVersion(languageDTO.getVersion());
		return languageEntity;
	}
	private LanguageDTO mapToLanguageDTO(LanguageEntity languageEntity){
		LanguageDTO languageDTO = new LanguageDTO();
		languageDTO.setName(languageEntity.getName());
		languageDTO.setVersion(languageEntity.getVersion());
		return languageDTO;
	}

}
