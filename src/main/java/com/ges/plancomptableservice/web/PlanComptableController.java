package com.ges.plancomptableservice.web;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.ges.plancomptableservice.dto.PlanComptableElementDTO;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.service.PlanComptableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WIAM
 **/
@RestController
public class PlanComptableController {
    @Autowired
    private PlanComptableService planComptableService;
    @PostMapping("/ajouterPlanComptableElement")
    public PlanComptableElement ajouterPlanComptableElement(@RequestBody PlanComptableElementDTO planComptableElementDTO){
        return planComptableService.ajouterPlanComotable(planComptableElementDTO);
    }
}
