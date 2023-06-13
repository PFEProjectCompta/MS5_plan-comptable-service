package com.ges.plancomptableservice.web;

import com.base.basemodel.dto.SocieteDTOKafka;
import com.ges.plancomptableservice.entities.CompteGeneral;
import com.ges.plancomptableservice.service.SocieteKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WIAM
 **/
@RestController
public class SocieteGraphQLController {
    @Autowired
    private SocieteKafkaService societeKafkaService;
    @QueryMapping
    public List<SocieteDTOKafka> allSocieteKafka(){
        return societeKafkaService.allSocieteKafka();
    }
}
