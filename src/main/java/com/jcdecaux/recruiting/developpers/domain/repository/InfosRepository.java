package com.jcdecaux.recruiting.developpers.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.jcdecaux.recruiting.developpers.domain.model.InfosEntity;

public interface InfosRepository extends Repository<InfosEntity, String>{

	@Query("SELECT i.version FROM InfosEntity i")
	String get();
}
