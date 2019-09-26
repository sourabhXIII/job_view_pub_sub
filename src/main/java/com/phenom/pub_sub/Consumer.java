package com.phenom.pub_sub;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


@Service
public class Consumer {

    private final Logger logger = LogManager.getLogger(Consumer.class);
    private static final String TOPIC = "job_board_views";

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }


    @KafkaListener(topics = TOPIC, groupId = "foo")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        logger.info("#### -> received payload='{}'", consumerRecord.value());
//        Gson gson = new GsonBuilder().create();
//        JsonObject incoming_msg = new Gson().fromJson( "{"+(String)consumerRecord.value()+"}", JsonObject.class);
//
//        logger.info("Received payload name: " + incoming_msg);
        latch.countDown();
    }
}
