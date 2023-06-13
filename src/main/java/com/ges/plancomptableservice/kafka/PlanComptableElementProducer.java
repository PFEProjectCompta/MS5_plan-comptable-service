package com.ges.plancomptableservice.kafka;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.base.basemodel.dto.SocieteDTOKafka;
import com.ges.plancomptableservice.entities.PlanComptableElement;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PlanComptableElementProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanComptableElementProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, PlanComptableDTOKafka> kafkaTemplate;


    public PlanComptableElementProducer(NewTopic topic, KafkaTemplate<String, PlanComptableDTOKafka> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(PlanComptableDTOKafka planComptableDTOKafka){
        LOGGER.info(String.format("Compte => %s",planComptableDTOKafka.getId()));
        Message<PlanComptableDTOKafka> message= MessageBuilder
                .withPayload(planComptableDTOKafka)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessageDeletedPlanComptableElement(PlanComptableDTOKafka planComptableDTOKafka){
        LOGGER.info(String.format("Societe supprimer => %s",planComptableDTOKafka.getId()));
        Message<PlanComptableDTOKafka> message= MessageBuilder
                .withPayload(planComptableDTOKafka)
                .setHeader(KafkaHeaders.TOPIC, "deleted_plan_comptable")
                .build();
        kafkaTemplate.send(message);
    }
}
