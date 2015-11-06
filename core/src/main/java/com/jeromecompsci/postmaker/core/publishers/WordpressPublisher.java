package com.jeromecompsci.postmaker.core.publishers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import com.jeromecompsci.postmaker.core.PostModel;
import com.jeromecompsci.postmaker.core.Publisher;

import java.io.IOException;

/**
 * @author derek
 */
public class WordpressPublisher extends Publisher {

    @Override
    public void publish(PostModel post) {
        System.out.println("publish() of WordpressPublisher called.");
        initProgress(100);
        updateProgress(10);
        String api_url = post.privProperties.getProperty("WORDPRESS_API_URL");
        if (!api_url.endsWith("/")) {
            api_url = api_url + "/";
        }
        updateInfo("Reading authentication cookie...");
        String authenticationCookie = post.privProperties.getProperty("WORDPRESS_AUTHENTICATION_COOKIE");
        updateProgress(20);

        HttpResponse<JsonNode> nonceResponse = null;
        updateInfo("Requesting nonce...");
        try {
            nonceResponse = Unirest.get(api_url + "get_nonce")
                    .queryString("controller", "posts")
                    .queryString("method", "create_post")
                    .queryString("cookie", authenticationCookie)
                    .asJson();
        } catch (UnirestException e) {
            throwException(e);
        }
        JSONObject nonceData = nonceResponse.getBody().getObject();
        String nonce = nonceData.getString("nonce");
        updateInfo("Nonce obtained.");
        updateProgress(40);

        HttpResponse<JsonNode> postResponse = null;
        updateInfo("Calling create_post...");
        try {
            postResponse = Unirest.post(api_url + "posts/create_post")
//                .header("Cookie", cookieName + "=" + cookieValue)
                .queryString("nonce", nonce)
                .queryString("status", "publish")
                .queryString("title", post.getRenderedTitle())
                .queryString("content", post.getRenderedFullText())
                .queryString("categories", post.categories)
                .queryString("cookie", authenticationCookie)
                .asJson();
        } catch (UnirestException e) {
            throwException(e);
        }
        updateProgress(85);
        JSONObject postDataContained = postResponse.getBody().getObject();
        JSONObject postData = postDataContained.getJSONObject("post");
        updateProgress(95);
        updateInfo("Received response from server.");

        String url = postData.getString("url");

        updateInfo("Storing primary presence URL...");
        post.primaryPresenceLink = url;
        updateProgress(100);
        declareDone();
    }

}
