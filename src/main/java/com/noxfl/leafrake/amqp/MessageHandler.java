package com.noxfl.leafrake.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noxfl.leafrake.schema.momijimessage.Job;
import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;

public class MessageHandler {

    private MessageSender messageSender;

    public void handle(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MomijiMessage momijiMessage = objectMapper.readValue(message, MomijiMessage.class);

        Job job = momijiMessage.getJob();

        System.out.println("Received job: " + momijiMessage.getJob().getName());

        // Immediately ends current function flow if job is not requesting scrape detail.
        if(!job.isScrapeDetail()) {
            messageSender.send(message);
            return;
        }

        System.out.println("Process message here");

    }

}
