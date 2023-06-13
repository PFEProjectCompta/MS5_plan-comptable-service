package com.ges.plancomptableservice.config;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.ges.plancomptableservice.entities.CompteGeneral;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.enums.NatureCompte;
import com.ges.plancomptableservice.kafka.PlanComptableElementProducer;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InitialData {

    private static CompteGeneralRepository compteGeneralRepository;

    private static PlanComptableElementRepository planComptableElementRepository;
    private static PlanComptableElementProducer planComptableElementProducer;

    public InitialData(CompteGeneralRepository compteGeneralRepository, PlanComptableElementRepository planComptableElementRepository, PlanComptableElementProducer planComptableElementProducer) {
        this.compteGeneralRepository = compteGeneralRepository;
        this.planComptableElementRepository = planComptableElementRepository;
        this.planComptableElementProducer = planComptableElementProducer;
    }
    public static void ajouterCompteGeneraux(){
        for(int i =0;i<5;i++){
            CompteGeneral compteGeneral=CompteGeneral.builder()
                    .id(UUID.randomUUID().toString())
                    .natureCompte(NatureCompte.CAPITAUX)
                    .debutFaurchette("31zzzzzz")
                    .finFaurchette("33zzzzzz")
                    .build();
            System.out.println(compteGeneralRepository.save(compteGeneral));
        }

    }
    public static void ajouterPlanComptableElement(){
        List<CompteGeneral> compteGenerals=compteGeneralRepository.findAll();
        compteGenerals.forEach(compteGeneral -> {
            for (int i=0; i<10;i++){
                PlanComptableElement planComptableElement=PlanComptableElement.builder()
                        .id(UUID.randomUUID().toString())
                        .reporter(true)
                        .numeroCompte("313300000")
                        .intitule("la vente des immobilisation")
                        .compteGeneral(compteGeneral)
                        .build();
                System.out.println(planComptableElementRepository.save(planComptableElement));
            }
        });
    }
    public static void addToKafka(){
        List<PlanComptableElement> planComptableElements=planComptableElementRepository.findAll();
        for(int i=0;i<planComptableElements.size();i++){
            PlanComptableDTOKafka planComptableDTOKafka=PlanComptableDTOKafka.builder()
                    .id(planComptableElements.get(i).getId())
                    .numeroCompte(planComptableElements.get(i).getNumeroCompte())
                    .compteGeneralId(planComptableElements.get(i).getCompteGeneral().getId())
                    .reporter(planComptableElements.get(i).getReporter())
                    .intitule(planComptableElements.get(i).getIntitule())
                    .build();
            planComptableElementProducer.sendMessage(planComptableDTOKafka);
        }
    }
}
