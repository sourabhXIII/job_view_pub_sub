package com.phenom.pub_sub;

import com.phenom.bean.JobBoards;
import com.phenom.bean.Users;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.springframework.data.mongodb.core.query.Query.query;


@Service
public class Consumer {

    private final Logger logger = LogManager.getLogger(Consumer.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "job_boards", groupId = "foo")
    public void receiveJobBoards(ConsumerRecord<?, ?> consumerRecord) {
//        TODO: Can make this a class member.
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        JobBoards board = gson.fromJson((String) consumerRecord.value(), JobBoards.class);
        logger.info(gson.toJson(board));
        latch.countDown();
    }

    @KafkaListener(topics = "users", groupId = "foo")
    public void receiveUser(ConsumerRecord<?, ?> consumerRecord) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Users user = gson.fromJson((String) consumerRecord.value(), Users.class);
        logger.info(gson.toJson(user));
        latch.countDown();

        try {
            MongoOperations mongoOps = new DBTemplate().connection();
            // insert
            mongoOps.insert(user);
            // find
            List<Users> users = mongoOps.findAll(Users.class);
            logger.info(gson.toJson(users));
            logger.info("Found "+ users.size() +" entries.");

//            logger.info("Removed all the documents: " +mongoOps.remove(new Query(), Users.class));
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
