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
public class WordpressPublisher implements Publisher {

    @Override
    public void publish(PostModel post) {
        String title = post.getRenderedTitle();
        String mainText = post.getRenderedFullText();
        String api_url = post.privProperties.getProperty("WORDPRESS_API_URL");
        if (!api_url.endsWith("/")) {
            api_url = api_url + "/";
        }
        String authenticationCookie = post.privProperties.getProperty("WORDPRESS_AUTHENTICATON_COOKIE");

        HttpResponse<JsonNode> nonceResponse = null;
        try {
            nonceResponse = Unirest.get(api_url + "get_nonce")
                    .queryString("controller", "posts")
                    .queryString("method", "create_post")
                    .asJson();
        } catch (UnirestException e) {

        }
        JSONObject nonceData = nonceResponse.getBody().getObject();
        String nonce = nonceData.getString("nonce");

        HttpResponse<JsonNode> postResponse = null;
//        try {
//            postResponse = Unirest.post();
//        } catch (UnirestException e) {
//
//        }

    }

}
