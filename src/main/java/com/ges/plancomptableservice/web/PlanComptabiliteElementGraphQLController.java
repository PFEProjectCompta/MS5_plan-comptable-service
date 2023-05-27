package com.ges.plancomptableservice.web;

import com.ges.plancomptableservice.dto.PlanComptableElementDTO;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class PlanComptabiliteElementGraphQLController {
    private CompteGeneralRepository compteGeneralRepository;
    private PlanComptableElementRepository planComptableElementRepository;

    public PlanComptabiliteElementGraphQLController(CompteGeneralRepository compteGeneralRepository,
                                                    PlanComptableElementRepository planComptableElementRepository) {
        this.compteGeneralRepository = compteGeneralRepository;
        this.planComptableElementRepository = planComptableElementRepository;
    }

    @QueryMapping
    public List<PlanComptableElement> planComptableElementList(){
        return planComptableElementRepository.findAll();
    }
    @QueryMapping
    public PlanComptableElement planComptableElementById(@Argument String id){
        return planComptableElementRepository.findById(id).get();
    }
    @MutationMapping
    public PlanComptableElement ajouterplanComptableElement(@Argument PlanComptableElementDTO planComptableElementDTO){
        PlanComptableElement planComptableElement=PlanComptableElement.builder()
                .id(UUID.randomUUID().toString())
                .reporter(true)
                .numeroCompte("122300000")
                .intitule("l'achat des marchandises")
                .compteGeneral(compteGeneralRepository.findById(planComptableElementDTO.getCompteGeneralId()).get())
                .build();
        return planComptableElementRepository.save(planComptableElement);
    }
    @MutationMapping
    public PlanComptableElement modifierplanComptableElement(@Argument PlanComptableElementDTO planComptableElementDTO ,
                                                             @Argument String id){
        PlanComptableElement planComptableElement= planComptableElementRepository.findById(id).get();
        planComptableElement.setIntitule(planComptableElementDTO.getIntitule()==null? planComptableElement.getIntitule() : planComptableElementDTO.getIntitule());
        planComptableElement.setReporter(planComptableElementDTO.getReporter()==null? planComptableElement.getReporter():planComptableElementDTO.getReporter());
        planComptableElement.setNumeroCompte(planComptableElementDTO.getNumeroCompte()==null? planComptableElement.getNumeroCompte(): planComptableElementDTO.getNumeroCompte());
        planComptableElement.setCompteGeneral(planComptableElementDTO.getCompteGeneralId()==null?planComptableElement.getCompteGeneral():compteGeneralRepository.findById(planComptableElementDTO.getCompteGeneralId()).get());
        return planComptableElementRepository.save(planComptableElement);
    }
    @MutationMapping
    public PlanComptableElement supprimerplanComptableElement(@Argument String id){
        PlanComptableElement planComptableElement=planComptableElementRepository.findById(id).get();
        planComptableElementRepository.delete(planComptableElement);
        return planComptableElement;
    }
}
