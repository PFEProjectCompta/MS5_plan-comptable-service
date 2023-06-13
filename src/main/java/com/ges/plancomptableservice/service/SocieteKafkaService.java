package com.ges.plancomptableservice.service;

import com.base.basemodel.dto.SocieteDTOKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author WIAM
 **/
@Service
public class SocieteKafkaService {
    @Autowired
    private RedisTemplate<String, SocieteDTOKafka> redisTemplate;

    public List<SocieteDTOKafka> allSocieteKafka(){
        List<SocieteDTOKafka> kafkaMessages = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) {
            List<SocieteDTOKafka> storedMessages = redisTemplate.opsForValue().multiGet(keys);
            kafkaMessages.addAll(storedMessages);
        }
        return kafkaMessages;
    }
}
