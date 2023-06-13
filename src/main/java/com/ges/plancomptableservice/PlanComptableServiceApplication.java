package com.ges.plancomptableservice;

import com.base.basemodel.dto.SocieteDTOKafka;
import com.ges.plancomptableservice.config.InitialData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableCaching
public class PlanComptableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanComptableServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(){
		return args -> {
//			InitialData.ajouterCompteGeneraux();
//			InitialData.ajouterPlanComptableElement();
			InitialData.addToKafka();
		};
	};
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public RedisTemplate<String, SocieteDTOKafka> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, SocieteDTOKafka> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheManager cacheManager = RedisCacheManager.create(connectionFactory);
		return cacheManager;
	}
//	@Bean
//	public Map<String, Object> consumerConfigs() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "plan-comptable");
//		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//		props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
////		props.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.example.MyKey");
//		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//		props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.ges.plancomptableservice.model.SocieteDTOKafka");
//		return props;
//	}
//
//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory() {
//		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//	}
//	@Bean
//	public KafkaListenerContainerFactory<?> kafkaJsonListenerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, SocieteDTOKafka> factory =
//				new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerFactory());
//		factory.setMessageConverter(new StringJsonMessageConverter());
//		return factory;
//	}
}
