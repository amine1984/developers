package com.jcdecaux.recruiting.developpers.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jcdecaux.recruiting.developpers.domain.model.LanguageEntity;

@Repository
public interface IlanguagesRepository extends JpaRepository<LanguageEntity, Integer>{
	
	@Query("select language from LanguageEntity language where language.name=:name and language.version=:version")
	LanguageEntity findByNameAndVersion(@Param("name") String name, @Param("version")String version);
	

}
