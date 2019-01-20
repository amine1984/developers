package com.jcdecaux.recruiting.developpers.domain.repository;

import com.jcdecaux.recruiting.developpers.domain.model.DeveloperEntity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IdevelopersRepository extends JpaRepository<DeveloperEntity, Integer> , JpaSpecificationExecutor<DeveloperEntity> {

	DeveloperEntity findById(Integer id);

	Set<DeveloperEntity> findAllByLanguagesName(String language);

	@Override
	List<DeveloperEntity> findAll(Specification<DeveloperEntity> specification);
}
