package com.ges.plancomptableservice;

import com.ges.plancomptableservice.config.InitialData;
import com.ges.plancomptableservice.entities.CompteGeneral;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import com.ges.plancomptableservice.enums.NatureCompte;
import com.ges.plancomptableservice.repository.CompteGeneralRepository;
import com.ges.plancomptableservice.repository.PlanComptableElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class PlanComptableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanComptableServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(){
		return args -> {
			InitialData.ajouterCompteGeneraux();
			InitialData.ajouterPlanComptableElement();
		};
	};
}
