package com.ges.plancomptableservice.entities;

import com.ges.plancomptableservice.enums.NatureCompte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "compteGenegalProjection",types = CompteGeneral.class)
public interface CompteGenegalProjection {
    public String getId();
    public NatureCompte getNatureCompte();
    public String getDebutFaurchette();
    public String getFinFaurchette();
}
