//package com.phenom.pub_sub;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.SubscribableChannel;
//
//public interface ViewsProcessorBinder {
//
//    String INPUT = "job_board_views";
//    String OP_JOB_BOARDS = "job_boards";
//    String OP_USERS = "users";
//
//    @Autowired
//    @Input(INPUT)
//    SubscribableChannel job_board_views();
//
//    @Autowired
//    @Output(OP_JOB_BOARDS)
//    MessageChannel outboundJobBoards();
//
//    @Autowired
//    @Output(OP_USERS)
//    MessageChannel outboundUsers();
//}