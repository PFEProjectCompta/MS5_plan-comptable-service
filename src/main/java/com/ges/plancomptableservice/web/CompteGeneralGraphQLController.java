package com.ges.plancomptableservice.web;

import com.ges.plancomptableservice.dto.CompteGeneralDTO;
import com.ges.plancomptableservice.entities.CompteGeneral;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import com.ges.plancomptableservice.service.CompteGeneralService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CompteGeneralGraphQLController {
    private CompteGeneralService compteGeneralService;
    private CompteGeneralRepository compteGeneralRepository;

    public CompteGeneralGraphQLController(CompteGeneralService compteGeneralService,
                                          CompteGeneralRepository compteGeneralRepository) {
        this.compteGeneralService = compteGeneralService;
        this.compteGeneralRepository = compteGeneralRepository;
    }
    @QueryMapping
    public List<CompteGeneral> compteGeneralList(){
        return compteGeneralRepository.findAll();
    }
    @QueryMapping
    public CompteGeneral compteGeneralById(@Argument String id){
        return compteGeneralRepository.findById(id).get();
    }
    @MutationMapping
    public CompteGeneral ajouterCompteGeneral(@Argument CompteGeneralDTO compteGeneralDTO){
        CompteGeneral compteGeneral=CompteGeneral.builder()
                .id(UUID.randomUUID().toString())
                .natureCompte(compteGeneralDTO.getNatureCompte())
                .debutFaurchette(compteGeneralDTO.getDebutFaurchette())
                .finFaurchette(compteGeneralDTO.getFinFaurchette())
                .build();
        return compteGeneralRepository.save(compteGeneral);
    }
    @MutationMapping
    public CompteGeneral modifierCompteGeneral(@Argument CompteGeneralDTO compteGeneralDTO,
                                               @Argument String id){
        CompteGeneral compteGeneral=compteGeneralRepository.findById(id).get();
        compteGeneral.setNatureCompte(compteGeneralDTO.getNatureCompte()==null? compteGeneral.getNatureCompte():compteGeneralDTO.getNatureCompte());
        compteGeneral.setFinFaurchette(compteGeneralDTO.getFinFaurchette()==null?compteGeneral.getFinFaurchette():compteGeneralDTO.getFinFaurchette());
        compteGeneral.setDebutFaurchette(compteGeneralDTO.getDebutFaurchette()==null?compteGeneral.getDebutFaurchette():compteGeneralDTO.getDebutFaurchette());
        return compteGeneralRepository.save(compteGeneral);
    }
    @MutationMapping
    public CompteGeneral supprimerCompteGeneral(@Argument String id){
        CompteGeneral compteGeneral=compteGeneralRepository.findById(id).get();
        compteGeneralRepository.delete(compteGeneral);
        return compteGeneral;
    }
    @QueryMapping
    public List<PlanComptableElement> planComptableByCompteGeneralId(@Argument String id){
        return compteGeneralService.plantCompteList(id);
    }
}
