package com.noxfl.leafrake;

import org.apache.http.client.utils.URIBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

@SpringBootTest
class LeafrakeApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	private String getShopFromUrl(String url) throws URISyntaxException {
		return new URIBuilder(url).getPathSegments().get(0);
	}

	private String getProductKeyFromUrl(String url) throws URISyntaxException {
		return new URIBuilder(url).getPathSegments().get(1);
	}

	@Test
	public void extractShopUsernameFromurl() throws URISyntaxException {
		String url = "https://www.tokopedia.com/samsung/samsung-galaxy-s23-ultra-5g-12-256gb-green-06f7";

		String shopUsername = getShopFromUrl(url);
		String productKey = getProductKeyFromUrl(url);

		Assertions.assertThat(shopUsername).isEqualTo("samsung");
		Assertions.assertThat(productKey).isEqualTo("samsung-galaxy-s23-ultra-5g-12-256gb-green-06f7");
	}

}
