package com.ges.plancomptableservice.dto;

import com.ges.plancomptableservice.enums.NatureCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompteGeneralDTO {
    private NatureCompte natureCompte;
    private String debutFaurchette;
    private String finFaurchette;
}
