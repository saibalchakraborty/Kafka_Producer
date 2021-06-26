package com.example.demo.producer.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.producer.kafka.model.Transaction;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private KafkaTemplate<String, Transaction> kafkaTemplate;
	@Value("${kafka.topic}")
	private String topic;
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String produceTransactionDetails(@RequestBody Transaction transaction) {
		try {
			kafkaTemplate.send(topic, transaction);
			return "Published transaction with id : " + transaction.getTransactionId();
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage());
			return "Failed due to : " + e.getLocalizedMessage();
		}
	}
}
