package com.phenom.pub_sub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Producer {
    private static final Logger logger = LogManager.getLogger(Producer.class);
    private static final String TOPIC = "job_board_views";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Map<String, Object> message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message.toString());
    }
}