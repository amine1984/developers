package com.jcdecaux.recruiting.developpers.domain.model.queries;

import com.jcdecaux.recruiting.developpers.domain.model.DeveloperEntity;
import org.springframework.data.jpa.domain.Specification;

public class DeveloperSpecification {

    public static Specification<DeveloperEntity> likeDeveloperName(String key, String value){
        return ((root, query, cb) -> cb.like(root.get(key).as(String.class),value));
    }
}
