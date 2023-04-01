package com.noxfl.leafrake.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noxfl.leafrake.schema.momijimessage.Content;
import com.noxfl.leafrake.schema.momijimessage.Job;
import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;
import com.noxfl.leafrake.scraper.ContentType;
import com.noxfl.leafrake.scraper.SiteScraperFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class MessageHandler {

    private MessageSender messageSender;

    @Autowired
    private void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    private MomijiMessage parseMessage(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(message, MomijiMessage.class);
    }

//    private SiteScraperFactory siteScraperFactory;
//
//    @Autowired
//    public void setSiteScraperFactory(SiteScraperFactory siteScraperFactory) {
//        this.siteScraperFactory = siteScraperFactory;
//    }

    public void handle(String message) throws IOException, NoSuchFieldException, URISyntaxException {

        MomijiMessage momijiMessage = parseMessage(message);

        Job job = momijiMessage.getJob();

        System.out.println("Received job: " + momijiMessage.getJob().getName());

        // Send original message if not scrape detail.
        if(job.isScrapeDetail()) {
//            String response = siteScraperFactory.getSiteScraper(job.getDetailPageType()).fetchProduct(momijiMessage);
//            String contentName = "product_detail";
//
//            Content content = new Content(contentName, ContentType.JSON, response);
//
//            momijiMessage.getJob().getContents().add(content);
//
//            message = new JSONObject(momijiMessage).toString();
        }

        messageSender.send(message);
    }

}
