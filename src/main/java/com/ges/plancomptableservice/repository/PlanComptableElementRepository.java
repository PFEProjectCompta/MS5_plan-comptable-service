package com.ges.plancomptableservice.repository;

import com.ges.plancomptableservice.entities.PlanComptableElement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlanComptableElementRepository extends MongoRepository<PlanComptableElement,String> {
}
