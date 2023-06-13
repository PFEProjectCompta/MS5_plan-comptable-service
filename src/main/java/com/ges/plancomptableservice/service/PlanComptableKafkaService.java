package com.ges.plancomptableservice.service;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.kafka.PlanComptableElementProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WIAM
 **/
@Service
public class PlanComptableKafkaService {
    @Autowired
    private PlanComptableElementProducer planComptableElementProducer;

    public void sendMessagePlanComptable(PlanComptableElement planComptableElement){
        PlanComptableDTOKafka planComptableDTOKafka=PlanComptableDTOKafka.builder()
                .id(planComptableElement.getId())
                .intitule(planComptableElement.getIntitule())
                .reporter(planComptableElement.getReporter())
                .numeroCompte(planComptableElement.getNumeroCompte())
                .compteGeneralId(planComptableElement.getCompteGeneral().getId())
                .build();
        planComptableElementProducer.sendMessage(planComptableDTOKafka);
    }
    public void sendMessagePlanComptableDeleted(PlanComptableElement planComptableElement){
        PlanComptableDTOKafka planComptableDTOKafka=PlanComptableDTOKafka.builder()
                .id(planComptableElement.getId())
                .intitule(planComptableElement.getIntitule())
                .reporter(planComptableElement.getReporter())
                .numeroCompte(planComptableElement.getNumeroCompte())
                .compteGeneralId(planComptableElement.getCompteGeneral().getId())
                .build();
        planComptableElementProducer.sendMessageDeletedPlanComptableElement(planComptableDTOKafka);
    }
}
