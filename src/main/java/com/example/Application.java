package com.example;

import cloud.piranha.embedded.EmbeddedPiranhaBuilder;
import cloud.piranha.embedded.EmbeddedRequestBuilder;
import cloud.piranha.embedded.EmbeddedResponseBuilder;
import java.io.IOException;
import javax.servlet.ServletException;

public class Application {

    public static void main(String[] args) throws IOException, ServletException {
        var piranha = new EmbeddedPiranhaBuilder()
                .servlet("HelloWorldServlet", HelloWorldServlet.class.getName())
                .servletMapping("HelloWorldServlet", "/HelloWorldServlet")
                .build()
                .start();

        var request = new EmbeddedRequestBuilder()
                .servletPath("/HelloWorldServlet")
                .build();
        var response = new EmbeddedResponseBuilder().build();
        piranha.service(request, response);

        System.out.println("Response content type: " + response.getContentType());
        System.out.println("Response body: " + System.lineSeparator() + response.getResponseAsString());

        piranha.stop().destroy();
    }
}
