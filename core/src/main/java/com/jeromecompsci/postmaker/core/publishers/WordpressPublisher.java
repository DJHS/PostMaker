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
        String authenticationCookie = post.privProperties.getProperty("WORDPRESS_AUTHENTICATION_COOKIE");
//        String cookieName = post.privProperties.getProperty("WORDPRESS_COOKIE_NAME");
//        String cookieValue = post.privProperties.getProperty("WORDPRESS_COOKIE_VALUE");

        HttpResponse<JsonNode> nonceResponse = null;
        try {
            nonceResponse = Unirest.get(api_url + "get_nonce")
                    .queryString("controller", "posts")
                    .queryString("method", "create_post")
                    .queryString("cookie", authenticationCookie)
                    .asJson();
        } catch (UnirestException e) {

        }
        JSONObject nonceData = nonceResponse.getBody().getObject();
        String nonce = nonceData.getString("nonce");

        HttpResponse<JsonNode> postResponse = null;
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

        }
        JSONObject postDataContained = postResponse.getBody().getObject();
        JSONObject postData = postDataContained.getJSONObject("post");

        String url = postData.getString("url");

        post.primaryPresenceLink = url;

//        String status = postData.getString("status");
//        System.out.println("status = " + status);
//        System.out.println(postData.toString());
//
//        int id = postData.getInt("id");
//        String url = postData.getString("url");
//        String slug = postData.getString("slug");
//
//        System.out.println("id = " + id);
//        System.out.println("url = " + url);
//        System.out.println("slug = " + slug);

    }

//    public static void main(String[] args) throws IOException {
//        PostModel post = new PostModel();
//        post.loadAllExternalResources();
//
//        post.titleSource = "Test Post 3";
//        post.fullTextSource = "#### Test Post 3\nHello World! This was made using PostMaker.";
//        post.blurbTextSource = "Test Post 3: Hello World";
//        post.categories = "updates";
//
//        post.render();
//
//        new WordpressPublisher().publish(post);
//    }
}
