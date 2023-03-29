/**
 * 
 */
package com.noxfl.leafrake.schema.momijimessage;

import com.noxfl.leafrake.scraper.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Fernando Nathanael
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {

	private String name;
	private Site site;
	private Category category;
	private String targetUrl;
	private PageType pageType;
	private DetailPageType detailPageType;
	private int minPage;
	private int maxPage;
	private String splitPath;
	private boolean scrapeDetail;
	private List<ContentParsingGuide> contentParsingGuides;
	private List<Content> contents;

}
