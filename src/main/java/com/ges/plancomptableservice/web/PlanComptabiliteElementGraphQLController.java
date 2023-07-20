package com.ges.plancomptableservice.web;

import com.ges.plancomptableservice.dto.PlanComptableElementDTO;
import com.ges.plancomptableservice.dto.PlanComptableElementSocieteDTO;
import com.ges.plancomptableservice.entities.CompteGeneral;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import com.ges.plancomptableservice.service.PlanComptableKafkaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class PlanComptabiliteElementGraphQLController {
    private CompteGeneralRepository compteGeneralRepository;
    private PlanComptableElementRepository planComptableElementRepository;
    private PlanComptableKafkaService planComptableKafkaService;

    public PlanComptabiliteElementGraphQLController(CompteGeneralRepository compteGeneralRepository,
                                                    PlanComptableElementRepository planComptableElementRepository, PlanComptableKafkaService planComptableKafkaService) {
        this.compteGeneralRepository = compteGeneralRepository;
        this.planComptableElementRepository = planComptableElementRepository;
        this.planComptableKafkaService = planComptableKafkaService;
    }

    @QueryMapping
    public List<PlanComptableElement> planComptableElementList() {
        return planComptableElementRepository.findAll();
    }

    @QueryMapping
    public PlanComptableElement planComptableElementById(@Argument String id) {
        return planComptableElementRepository.findById(id).get();
    }

    @QueryMapping
    public List<PlanComptableElement> planComptableElementBySocieteId(@Argument String id) {
        List<PlanComptableElement> planComptableElementList = planComptableElementRepository.findAll();
        List<PlanComptableElement> planComptableElementsBySocieteId = new ArrayList<>();
        for (int i = 0; i < planComptableElementList.size(); i++) {
            if (planComptableElementList.get(i).getSocieteId() != null) {
                if (planComptableElementList.get(i).getSocieteId().equals(id)) {
                    planComptableElementsBySocieteId.add(planComptableElementList.get(i));
                }
            }

        }
        return planComptableElementsBySocieteId;
    }

    @MutationMapping
    public PlanComptableElement ajouterplanComptableElement(@Argument PlanComptableElementDTO planComptableElementDTO) {
        PlanComptableElement planComptableElement = PlanComptableElement.builder()
                .id(UUID.randomUUID().toString())
                .reporter(true)
                .numeroCompte(planComptableElementDTO.getNumeroCompte())
                .intitule(planComptableElementDTO.getIntitule())
                .compteGeneral(compteGeneralRepository.findById(planComptableElementDTO.getCompteGeneralId()).get())
                .societeId(planComptableElementDTO.getSocieteId())
                .build();
        PlanComptableElement saved = planComptableElementRepository.save(planComptableElement);
        planComptableKafkaService.sendMessagePlanComptable(saved);
        return saved;
    }

    @MutationMapping
    public PlanComptableElement ajouterplanComptableElementSociete(@Argument PlanComptableElementSocieteDTO planComptableElementSocieteDTO) {
        List<CompteGeneral> compteGenerals = compteGeneralRepository.findAll();
        List<CompteGeneral> compteGeneralListByIdSociete = new ArrayList<>();
        CompteGeneral compteGeneral = new CompteGeneral();
        for (int i = 0; i < compteGenerals.size(); i++) {
            if (compteGenerals.get(i).getIdSociete().equals(planComptableElementSocieteDTO.getSocieteId())) {
                compteGeneralListByIdSociete.add(compteGenerals.get(i));
            }
        }

        String[] s = planComptableElementSocieteDTO.getIntitule().split("\\s+");
        for (int i = 0; i < s.length; i++) {
            System.out.println("items : " + s[i]);

            for (int j = 0; j < compteGeneralListByIdSociete.size(); j++) {
                if (compteGeneralListByIdSociete.get(j).getNatureCompte().toString().contains("_")) {
                    if (s[i].toLowerCase().indexOf(compteGeneralListByIdSociete.get(j).getNatureCompte().toString().split("_")[0].substring(0, 4).toLowerCase()) != -1) {
                        compteGeneral = compteGeneralListByIdSociete.get(j);
                        break;
                    } else if (s[i].toLowerCase().indexOf(compteGeneralListByIdSociete.get(j).getNatureCompte().toString().split("_")[1].substring(0, 4).toLowerCase()) != -1) {
                        compteGeneral = compteGeneralListByIdSociete.get(j);
                        break;
                    }
                } else {
                    if (s[i].toLowerCase().indexOf(compteGeneralListByIdSociete.get(j).getNatureCompte().toString().toLowerCase()) != -1) {
                        compteGeneral = compteGeneralListByIdSociete.get(j);
                        break;
                    }
                }
            }
            if (compteGeneral.getId() != null) {
                break;
            }
        }
        PlanComptableElement planComptableElement = PlanComptableElement.builder()
                .id(UUID.randomUUID().toString())
                .reporter(true)
                .numeroCompte(planComptableElementSocieteDTO.getNumeroCompte())
                .intitule(planComptableElementSocieteDTO.getIntitule())
                .compteGeneral(compteGeneral)
                .societeId(planComptableElementSocieteDTO.getSocieteId())
                .build();
        PlanComptableElement saved = planComptableElementRepository.save(planComptableElement);
        planComptableKafkaService.sendMessagePlanComptable(saved);
        return saved;
    }

    @MutationMapping
    public PlanComptableElement modifierplanComptableElement(@Argument PlanComptableElementDTO planComptableElementDTO,
                                                             @Argument String id) {
        PlanComptableElement planComptableElement = planComptableElementRepository.findById(id).get();
        planComptableElement.setIntitule(planComptableElementDTO.getIntitule() == null ? planComptableElement.getIntitule() : planComptableElementDTO.getIntitule());
        planComptableElement.setReporter(planComptableElementDTO.getReporter() == null ? planComptableElement.getReporter() : planComptableElementDTO.getReporter());
        planComptableElement.setNumeroCompte(planComptableElementDTO.getNumeroCompte() == null ? planComptableElement.getNumeroCompte() : planComptableElementDTO.getNumeroCompte());
        planComptableElement.setCompteGeneral(planComptableElementDTO.getCompteGeneralId() == null ? planComptableElement.getCompteGeneral() : compteGeneralRepository.findById(planComptableElementDTO.getCompteGeneralId()).get());
        planComptableElement.setSocieteId(planComptableElementDTO.getSocieteId() == null ? planComptableElement.getSocieteId() : planComptableElementDTO.getSocieteId());
        PlanComptableElement saved = planComptableElementRepository.save(planComptableElement);
        planComptableKafkaService.sendMessagePlanComptable(saved);
        return saved;
    }

    @MutationMapping
    public PlanComptableElement supprimerplanComptableElement(@Argument String id) {
        PlanComptableElement planComptableElement = planComptableElementRepository.findById(id).get();
        planComptableElementRepository.delete(planComptableElement);
        planComptableKafkaService.sendMessagePlanComptableDeleted(planComptableElement);
        return planComptableElement;
    }
}
