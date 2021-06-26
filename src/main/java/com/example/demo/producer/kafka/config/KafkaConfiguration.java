package com.example.demo.producer.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.demo.producer.kafka.model.Transaction;

@Configuration
public class KafkaConfiguration {

	@Value("${kafka.server.config}")
	private String serverConfig;

	@Bean
	public ProducerFactory<String, Transaction> getKafkaProducerFactory() {
		Map<String, Object> configuration = new HashMap<>();
		configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverConfig);
		configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Transaction>(configuration);
	}

	@Bean
	public KafkaTemplate<String, Transaction> kafkaTemplate() {

		return new KafkaTemplate<>(getKafkaProducerFactory());
	}

}
