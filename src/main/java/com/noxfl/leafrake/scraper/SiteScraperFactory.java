package com.noxfl.leafrake.scraper;

import com.noxfl.leafrake.schema.momijimessage.DetailPageType;
import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;

public interface SiteScraperFactory {

    public SiteScraper getSiteScraper(DetailPageType detailPageType);

}
