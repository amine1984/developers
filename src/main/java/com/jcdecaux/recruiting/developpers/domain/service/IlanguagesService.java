package com.jcdecaux.recruiting.developpers.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;

@Service
public interface IlanguagesService {
	
	 List<Integer> createLanguages(List<LanguageDTO> languages);
	 LanguageDTO viewLanguage (Integer idLanguage);

}
