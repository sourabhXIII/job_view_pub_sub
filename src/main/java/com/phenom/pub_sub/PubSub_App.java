package com.phenom.pub_sub;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class PubSub_App {

    private static final Logger logger = LogManager.getLogger(PubSub_App.class);

    public static boolean checkKafkaStatus() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("connections.max.idle.ms", 10000);
        properties.put("request.timeout.ms", 5000);

        try (AdminClient client = KafkaAdminClient.create(properties))
        {
            ListTopicsResult topics = client.listTopics();
            Set<String> names = topics.names().get();
            if (names.isEmpty())
            {
                // case: if no topic found.
                logger.warn("No topic found.");
            }
            logger.info("Topics found:", names);
            return true;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("No running Kafka found.", e);
        }
        return false;
    }
    public static void main(String[] args) {
        boolean k = checkKafkaStatus();
        if(!k) {
            logger.error("No running Kafka found.");
            return;
        }
        SpringApplication.run(PubSub_App.class, args);
    }
}