package com.noxfl.leafrake.scraper.tokopedia;

import com.noxfl.leafrake.connection.ApiFetcher;
import com.noxfl.leafrake.schema.momijimessage.Content;
import com.noxfl.leafrake.schema.momijimessage.Job;
import com.noxfl.leafrake.schema.momijimessage.MomijiMessage;
import com.noxfl.leafrake.scraper.SiteScraper;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Optional;

public class TokopediaProductDetail implements SiteScraper {

    public static final String TOKOPEDIA_GRAPHQL_ENDPOINT = "https://gql.tokopedia.com";

    private ApiFetcher apiFetcher;

    @Autowired
    public TokopediaProductDetail(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public String getUrl(MomijiMessage momijiMessage) throws NoSuchFieldException {
        // Reusable function, move somewhere else.

        Job job = momijiMessage.getJob();

        Optional<Content> optUrlContent = job.getContents().stream()
                .filter(content -> content.getName().equalsIgnoreCase("URL"))
                .findFirst();

        if(optUrlContent.isEmpty()) throw new NoSuchFieldException("No URL Content found in message.");

        return optUrlContent.get().getContent();
    }

    public static String cleanseUrlFromParameters(String url) throws URISyntaxException, URISyntaxException {
        return new URIBuilder(url).clearParameters().build().toString();
    }

    private String getShopFromUrl(String url) throws URISyntaxException {
        return new URIBuilder(url).getPathSegments().get(0);
    }

    private String getProductKeyFromUrl(String url) throws URISyntaxException {
        return new URIBuilder(url).getPathSegments().get(1);
    }

    private JSONObject buildQueryHeader(String url) throws URISyntaxException {

        String pdpGetLayoutQueryTemplate = "{\"operationName\":\"PDPGetLayoutQuery\",\n" +
                "            \"variables\":{\n" +
                "            \"shopDomain\":\"%s\",\n" +
                "            \"productKey\":\"%s\",\n" +
                "            \"layoutID\":\"\",\n" +
                "            \"apiVersion\":1,\n" +
                "            \"userLocation\":{\"cityID\":\"176\",\"addressID\":\"0\",\"districtID\":\"2274\",\"postalCode\":\"\",\"latlon\":\"\"},\"extParam\":\"\"},\"query\":\"fragment ProductVariant on pdpDataProductVariant {\\n  errorCode\\n  parentID\\n  defaultChild\\n  sizeChart\\n  totalStockFmt\\n  variants {\\n    productVariantID\\n    variantID\\n    name\\n    identifier\\n    option {\\n      picture {\\n        urlOriginal: url\\n        urlThumbnail: url100\\n        __typename\\n      }\\n      productVariantOptionID\\n      variantUnitValueID\\n      value\\n      hex\\n      stock\\n      __typename\\n    }\\n    __typename\\n  }\\n  children {\\n    productID\\n    price\\n    priceFmt\\n    optionID\\n    optionName\\n    productName\\n    productURL\\n    picture {\\n      urlOriginal: url\\n      urlThumbnail: url100\\n      __typename\\n    }\\n    stock {\\n      stock\\n      isBuyable\\n      stockWordingHTML\\n      minimumOrder\\n      maximumOrder\\n      __typename\\n    }\\n    isCOD\\n    isWishlist\\n    campaignInfo {\\n      campaignID\\n      campaignType\\n      campaignTypeName\\n      campaignIdentifier\\n      background\\n      discountPercentage\\n      originalPrice\\n      discountPrice\\n      stock\\n      stockSoldPercentage\\n      startDate\\n      endDate\\n      endDateUnix\\n      appLinks\\n      isAppsOnly\\n      isActive\\n      hideGimmick\\n      isCheckImei\\n      minOrder\\n      __typename\\n    }\\n    thematicCampaign {\\n      additionalInfo\\n      background\\n      campaignName\\n      icon\\n      __typename\\n    }\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment ProductMedia on pdpDataProductMedia {\\n  media {\\n    type\\n    urlOriginal: URLOriginal\\n    urlThumbnail: URLThumbnail\\n    urlMaxRes: URLMaxRes\\n    videoUrl: videoURLAndroid\\n    prefix\\n    suffix\\n    description\\n    variantOptionID\\n    __typename\\n  }\\n  videos {\\n    source\\n    url\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment ProductHighlight on pdpDataProductContent {\\n  name\\n  price {\\n    value\\n    currency\\n    __typename\\n  }\\n  campaign {\\n    campaignID\\n    campaignType\\n    campaignTypeName\\n    campaignIdentifier\\n    background\\n    percentageAmount\\n    originalPrice\\n    discountedPrice\\n    originalStock\\n    stock\\n    stockSoldPercentage\\n    threshold\\n    startDate\\n    endDate\\n    endDateUnix\\n    appLinks\\n    isAppsOnly\\n    isActive\\n    hideGimmick\\n    __typename\\n  }\\n  thematicCampaign {\\n    additionalInfo\\n    background\\n    campaignName\\n    icon\\n    __typename\\n  }\\n  stock {\\n    useStock\\n    value\\n    stockWording\\n    __typename\\n  }\\n  variant {\\n    isVariant\\n    parentID\\n    __typename\\n  }\\n  wholesale {\\n    minQty\\n    price {\\n      value\\n      currency\\n      __typename\\n    }\\n    __typename\\n  }\\n  isCashback {\\n    percentage\\n    __typename\\n  }\\n  isTradeIn\\n  isOS\\n  isPowerMerchant\\n  isWishlist\\n  isCOD\\n  isFreeOngkir {\\n    isActive\\n    __typename\\n  }\\n  preorder {\\n    duration\\n    timeUnit\\n    isActive\\n    preorderInDays\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment ProductCustomInfo on pdpDataCustomInfo {\\n  icon\\n  title\\n  isApplink\\n  applink\\n  separator\\n  description\\n  __typename\\n}\\n\\nfragment ProductInfo on pdpDataProductInfo {\\n  row\\n  content {\\n    title\\n    subtitle\\n    applink\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment ProductDetail on pdpDataProductDetail {\\n  content {\\n    title\\n    subtitle\\n    applink\\n    showAtFront\\n    isAnnotation\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment ProductDataInfo on pdpDataInfo {\\n  icon\\n  title\\n  isApplink\\n  applink\\n  content {\\n    icon\\n    text\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment ProductSocial on pdpDataSocialProof {\\n  row\\n  content {\\n    icon\\n    title\\n    subtitle\\n    applink\\n    type\\n    rating\\n    __typename\\n  }\\n  __typename\\n}\\n\\nquery PDPGetLayoutQuery($shopDomain: String, $productKey: String, $layoutID: String, $apiVersion: Float, $userLocation: pdpUserLocation, $extParam: String) {\\n  pdpGetLayout(shopDomain: $shopDomain, productKey: $productKey, layoutID: $layoutID, apiVersion: $apiVersion, userLocation: $userLocation, extParam: $extParam) {\\n    requestID\\n    name\\n    pdpSession\\n    basicInfo {\\n      alias\\n      createdAt\\n      isQA\\n      id: productID\\n      shopID\\n      shopName\\n      minOrder\\n      maxOrder\\n      weight\\n      weightUnit\\n      condition\\n      status\\n      url\\n      needPrescription\\n      catalogID\\n      isLeasing\\n      isBlacklisted\\n      menu {\\n        id\\n        name\\n        url\\n        __typename\\n      }\\n      category {\\n        id\\n        name\\n        title\\n        breadcrumbURL\\n        isAdult\\n        isKyc\\n        minAge\\n        detail {\\n          id\\n          name\\n          breadcrumbURL\\n          isAdult\\n          __typename\\n        }\\n        __typename\\n      }\\n      txStats {\\n        transactionSuccess\\n        transactionReject\\n        countSold\\n        paymentVerified\\n        itemSoldFmt\\n        __typename\\n      }\\n      stats {\\n        countView\\n        countReview\\n        countTalk\\n        rating\\n        __typename\\n      }\\n      __typename\\n    }\\n    components {\\n      name\\n      type\\n      position\\n      data {\\n        ...ProductMedia\\n        ...ProductHighlight\\n        ...ProductInfo\\n        ...ProductDetail\\n        ...ProductSocial\\n        ...ProductDataInfo\\n        ...ProductCustomInfo\\n        ...ProductVariant\\n        __typename\\n      }\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}\n";

        String shopDomain;
        String productKey;

        try {
            url = cleanseUrlFromParameters(url);
            shopDomain =  getShopFromUrl(url);
            productKey = getProductKeyFromUrl(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String pdpGetLayoutQuery = String.format(pdpGetLayoutQueryTemplate, shopDomain, productKey;

        return new JSONObject(pdpGetLayoutQuery);

    }

    @Override
    public String fetchProduct(MomijiMessage momijiMessage) throws NoSuchFieldException, IOException, URISyntaxException {

        String productUrl = getUrl(momijiMessage);

        JSONObject query = buildQueryHeader(productUrl);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "en-US,en;q=0.9,id-ID;q=0.8,id;q=0.7,ja-JP;q=0.6,ja;q=0.5");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
        headers.put("x-device", "desktop");
        headers.put("x-source", "tokopedia-lite");
        headers.put("x-tkpd-akamai", "pdpGetLayout");
        headers.put("x-tkpd-lite-service", "zeus");
        headers.put("x-version", "40bd9a6"); // Might be constantly updated?

        return String.valueOf(apiFetcher.fetchPost(query.toString(), headers,TOKOPEDIA_GRAPHQL_ENDPOINT));
    }
}
