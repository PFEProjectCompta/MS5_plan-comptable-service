package com.ges.plancomptableservice.dto;

import com.ges.plancomptableservice.entities.CompteGeneral;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PlanComptableElementDTO {
    private String numeroCompte;
    private String intitule;
    private Boolean reporter;
    private String compteGeneralId;
    private String societeId;
}
