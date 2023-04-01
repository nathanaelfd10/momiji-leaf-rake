package com.noxfl.leafrake;

import com.noxfl.leafrake.scraper.SiteScraperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Fernando Nathanael
 *
 */
public class LeafRakeRunner implements CommandLineRunner {

    private ConfigurableApplicationContext context;

    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

//    @Autowired
//    private SiteScraperFactory siteScraperFactory;
//
//    public void setSiteCrawlerFactory(SiteScraperFactory siteScraperFactory) {
//        this.siteScraperFactory = siteScraperFactory;
//    }

    @Override
    public void run(String... args) throws Exception {
        int sleep = 900000;
        String message = String.format("Running for %s", sleep);
        System.out.println(message);
        Thread.sleep(sleep);
        System.out.println(message);

        context.close();
    }
}
