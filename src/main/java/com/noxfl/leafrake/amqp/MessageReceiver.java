package com.noxfl.leafrake.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noxfl.leafrake.LeafRakeConfiguration;
import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;
import com.noxfl.leafrake.scraper.SiteScraperFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

public class MessageReceiver {

    private SiteScraperFactory siteScraperFactory;

    @Autowired
    public void setSiteCrawlerFactory(SiteScraperFactory siteScraperFactory) {
        this.siteScraperFactory = siteScraperFactory;
    }

    private Queue queue;

    @Autowired
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    private MessageHandler messageHandler;

    @Autowired
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @RabbitHandler
    @RabbitListener(queues = LeafRakeConfiguration.INPUT_QUEUE_NAME)
    public void receive(String message) throws IOException, NoSuchFieldException, URISyntaxException {
        System.out.println("[*] Received new message");

        messageHandler.handle(message);
    }


}
