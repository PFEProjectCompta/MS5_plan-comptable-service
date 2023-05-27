package com.ges.plancomptableservice.config;

import com.ges.plancomptableservice.entities.CompteGeneral;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.enums.NatureCompte;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class InitialData {

    private static CompteGeneralRepository compteGeneralRepository;

    private static PlanComptableElementRepository planComptableElementRepository;

    public InitialData(CompteGeneralRepository compteGeneralRepository, PlanComptableElementRepository planComptableElementRepository) {
        this.compteGeneralRepository = compteGeneralRepository;
        this.planComptableElementRepository = planComptableElementRepository;
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
}
