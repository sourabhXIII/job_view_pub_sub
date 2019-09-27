//package com.phenom.pub_sub;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.web.bind.annotation.RestController;
//
//@SpringBootApplication
//@RestController
//@EnableBinding(ViewsProcessorBinder.class)
//public class JobBoardsAndUsersProcessor {
//    @Autowired
//    private ViewsProcessorBinder streams;
//    private static final Logger logger = LogManager.getLogger(JobBoardsAndUsersProcessor.class);
//
//    @StreamListener(ViewsProcessorBinder.INPUT)
//    public void handleBoards(String s) {
//        logger.info(s);
//        this.streams.outboundJobBoards().send(message(s));
//        this.streams.outboundUsers().send(message(s));
//    }
//
//    private static final <T> Message<T> message(T val) {
//        return MessageBuilder
//                .withPayload(val)
//                .build();
//    }
//
//    @ServiceActivator(inputChannel = ViewsProcessorBinder.INPUT + ".errors")
//    public void error(Message<?> message) {
//        logger.error("Handling ERROR: " + message);
//    }
//
//    @StreamListener("errorChannel")
//    public void errorGlobal(Message<?> message) {
//        logger.fatal("Handling Global ERROR: " + message);
//    }
//}