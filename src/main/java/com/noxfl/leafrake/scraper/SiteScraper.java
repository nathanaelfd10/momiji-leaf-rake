package com.noxfl.leafrake.scraper;

import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface SiteScraper {

    public String fetchProduct(MomijiMessage momijiMessage) throws NoSuchFieldException, IOException, URISyntaxException;

}
