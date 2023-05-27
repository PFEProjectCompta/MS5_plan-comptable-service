package com.ges.plancomptableservice.web;

import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import com.ges.plancomptableservice.service.CompteGeneralService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompteGeneralController {
    private CompteGeneralService compteGeneralService;
    public CompteGeneralController(CompteGeneralService compteGeneralService) {
        this.compteGeneralService = compteGeneralService;
    }
    @GetMapping("/plantCompteList/{id}")
    public List<PlanComptableElement> plantCompteList(@PathVariable String id){
        return compteGeneralService.plantCompteList(id);
    }
}
