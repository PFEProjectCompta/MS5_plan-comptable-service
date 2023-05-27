package com.ges.plancomptableservice.repository;

import com.ges.plancomptableservice.entities.CompteGeneral;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface CompteGeneralRepository extends MongoRepository<CompteGeneral,String> {
}
