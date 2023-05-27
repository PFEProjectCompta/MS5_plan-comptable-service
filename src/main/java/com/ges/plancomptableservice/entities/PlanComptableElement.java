package com.ges.plancomptableservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "plan-comptable")
public class PlanComptableElement {
    @Id
    private String id;
    private String numeroCompte;
    private String intitule;
    private Boolean reporter;
    private CompteGeneral compteGeneral;
}