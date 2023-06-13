package com.ges.plancomptableservice.kafka;

import com.base.basemodel.dto.SocieteDTOKafka;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SocieteConsumer {
    @Autowired
    private RedisTemplate<String,SocieteDTOKafka> redisTemplate;
    private static final Logger LOGGER= LoggerFactory.getLogger(SocieteDTOKafka.class);



    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}")
    public void consume(SocieteDTOKafka societeDTOKafka){
        redisTemplate.opsForValue().set(societeDTOKafka.getId(), societeDTOKafka);
        LOGGER.info(String.format("Societe for kafka => %s",societeDTOKafka.getId()));
    }
    @KafkaListener(
            topics = "deleted_societe"
            ,groupId = "${spring.kafka.consumer.group-id}")
    public void consumeDeletedSociete(SocieteDTOKafka societeDTOKafka){
        redisTemplate.delete(societeDTOKafka.getId());
        LOGGER.info(String.format("Societe for kafka => %s",societeDTOKafka.getId()));
    }

}
