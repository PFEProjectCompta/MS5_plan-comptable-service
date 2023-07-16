package com.ges.plancomptableservice.entities;

import com.ges.plancomptableservice.enums.NatureCompte;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@ToString
@NoArgsConstructor@AllArgsConstructor @Builder
@Document(collection = "comptes-generaux")
public class CompteGeneral {
    @Id
    private String id;
    private NatureCompte natureCompte;
    private String debutFaurchette;
    private String finFaurchette;
    private String idSociete;
}
