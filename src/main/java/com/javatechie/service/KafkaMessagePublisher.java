package com.javatechie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void SendMessageToTopic(String message){
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("javatechie-demo1", message); // creates topic with name javatechie-demo1 with default partion and offshets
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("javatechie-demo-2", message); // created topic from CLI interface with required partions 3 and offsets
          CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("javatechie-demo-3", message); // created topic from Configuration file of KafkaProducerConfig partions 5 and offsets
        future.whenComplete((result, ex) ->{
            if (ex == null) {
                System.out.println("Sent Message=[" + message +"] with offset=[" +result.getRecordMetadata().offset() + "]"
                );
            }
            else{
                System.out.println("Unable to send message =[" + message +"] due to " + ex.getMessage());
            }
        } );
    }
}
