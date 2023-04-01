package com.noxfl.leafrake.scraper;

import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface SiteScraper {

    public String fetchProduct(MomijiMessage momijiMessage) throws NoSuchFieldException, IOException, URISyntaxException;

}
