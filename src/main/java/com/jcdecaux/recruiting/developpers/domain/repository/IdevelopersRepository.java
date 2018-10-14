package com.jcdecaux.recruiting.developpers.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jcdecaux.recruiting.developpers.domain.model.DeveloperEntity;

@Repository
public interface IdevelopersRepository extends JpaRepository<DeveloperEntity, Integer>{
	
	DeveloperEntity findById(Integer id);
	
	@Query("select developer from DeveloperEntity developer join developer.languages language where language.name=:language")
	List<DeveloperEntity> findByLanguage(@Param("language") String language);

}
