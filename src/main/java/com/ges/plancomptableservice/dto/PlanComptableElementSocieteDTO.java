package com.ges.plancomptableservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIAM
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanComptableElementSocieteDTO {
    private String numeroCompte;
    private String intitule;
    private String societeId;
}
