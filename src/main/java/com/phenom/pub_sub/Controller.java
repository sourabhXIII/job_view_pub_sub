package com.phenom.pub_sub;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/pub-sub")
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final Producer producer;

    @GetMapping("home")
    public String index() {
        logger.info("Hits home url.");
        return "Greetings from pub-sub service!\n";
    }

    @Autowired
    Controller(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody Map<String, Object> payload) {
        this.producer.sendMessage(payload);
    }
}