package com.ges.plancomptableservice.web;

import com.base.basemodel.dto.SocieteDTOKafka;
import com.ges.plancomptableservice.service.SocieteKafkaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author WIAM
 **/
@RestController
public class SocieteKafkaController {
    @Autowired
    private SocieteKafkaService societeKafkaService;
    @Autowired
    private RedisTemplate<String, SocieteDTOKafka> redisTemplate;

    @GetMapping("allSocieteKafka")
    public List<SocieteDTOKafka> allSocieteKafka(){
        return societeKafkaService.allSocieteKafka();
    }
    @GetMapping("viderRedise")
    public String viderRedise(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
        return "Done";
    }
}
