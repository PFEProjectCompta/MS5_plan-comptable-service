package com.ges.plancomptableservice.service;

import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteGeneralService {

    private PlanComptableElementRepository planComptableElementRepository;

    public CompteGeneralService(PlanComptableElementRepository planComptableElementRepository) {
        this.planComptableElementRepository = planComptableElementRepository;
    }
    public List<PlanComptableElement> plantCompteList(@PathVariable String id){
        List<PlanComptableElement> planComptableElements=planComptableElementRepository.findAll();
        List<PlanComptableElement> planComptableElementsFiliter=new ArrayList<>();
        planComptableElements.forEach(planComptableElement -> {
            if (planComptableElement.getCompteGeneral().getId().equals(id)){
                planComptableElementsFiliter.add(planComptableElement);
            }
        });
        return planComptableElementsFiliter;
    }
}
