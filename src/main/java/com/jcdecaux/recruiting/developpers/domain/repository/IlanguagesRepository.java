package com.jcdecaux.recruiting.developpers.domain.repository;

import com.jcdecaux.recruiting.developpers.domain.model.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IlanguagesRepository extends JpaRepository<LanguageEntity, Integer>{

	LanguageEntity findByNameAndVersion(String name,String version);

	LanguageEntity findById(Integer idLanguage);
	

}
