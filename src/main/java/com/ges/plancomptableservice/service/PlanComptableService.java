package com.ges.plancomptableservice.service;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.ges.plancomptableservice.dto.PlanComptableElementDTO;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.kafka.PlanComptableElementProducer;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author WIAM
 **/
@Service
public class PlanComptableService {

    private PlanComptableElementRepository planComptableElementRepository;
    private PlanComptableElementProducer planComptableElementProducer;
    private CompteGeneralRepository compteGeneralRepository;

    public PlanComptableService(PlanComptableElementRepository planComptableElementRepository, PlanComptableElementProducer planComptableElementProducer, CompteGeneralRepository compteGeneralRepository) {
        this.planComptableElementRepository = planComptableElementRepository;
        this.planComptableElementProducer = planComptableElementProducer;
        this.compteGeneralRepository = compteGeneralRepository;
    }
    public PlanComptableElement ajouterPlanComotable(PlanComptableElementDTO planComptableElementDTO){
        PlanComptableElement planComptableElement=PlanComptableElement.builder()
                .id(UUID.randomUUID().toString())
                .numeroCompte(planComptableElementDTO.getNumeroCompte())
                .compteGeneral(compteGeneralRepository.findById(planComptableElementDTO.getCompteGeneralId()).get())
                .reporter(planComptableElementDTO.getReporter())
                .intitule(planComptableElementDTO.getIntitule())
                .build();
        PlanComptableElement saved=planComptableElementRepository.save(planComptableElement);
        PlanComptableDTOKafka planComptableDTOKafka=PlanComptableDTOKafka.builder()
                .id(saved.getId())
                .numeroCompte(saved.getNumeroCompte())
                .compteGeneralId(saved.getCompteGeneral().getId())
                .reporter(saved.getReporter())
                .intitule(saved.getIntitule())
                .build();
        planComptableElementProducer.sendMessage(planComptableDTOKafka);
        return saved;
    }
}
