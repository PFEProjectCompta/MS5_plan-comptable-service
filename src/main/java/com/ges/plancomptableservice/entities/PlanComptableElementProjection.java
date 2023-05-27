package com.ges.plancomptableservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "planComptableElementProjection" ,types = PlanComptableElement.class)
public interface PlanComptableElementProjection {
    public String getId();
    public String getIntitule();
    public Boolean getReporter();
    public String getNumeroCompte();
    public CompteGeneral getCompteGeneral();
}
