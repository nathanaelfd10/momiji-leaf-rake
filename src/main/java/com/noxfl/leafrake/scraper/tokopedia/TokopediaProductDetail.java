package com.noxfl.leafrake.scraper.tokopedia;

import com.noxfl.leafrake.schema.momijimessage.Content;
import com.noxfl.leafrake.schema.momijimessage.Job;
import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;
import com.noxfl.leafrake.scraper.SiteScraper;

import java.util.Optional;

public class TokopediaProductDetail implements SiteScraper {

    public String getUrl(MomijiMessage momijiMessage) throws NoSuchFieldException {
        Job job = momijiMessage.getJob();

        Optional<Content> optUrlContent = job.getContents().stream().filter(content -> content.getName().equalsIgnoreCase("URL")).findFirst();

        if(optUrlContent.isEmpty()) throw new NoSuchFieldException();

        return optUrlContent.get().getContent();
    }



    @Override
    public String fetchProduct(MomijiMessage momijiMessage) throws NoSuchFieldException {

        String url = getUrl(momijiMessage);

        


        return null;
    }
}
