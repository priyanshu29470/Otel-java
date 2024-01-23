package com.example.otel.Controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;


@RestController
@RequestMapping("/java")
public class OtelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OtelController.class);

    // private final Tracer tracer;

    // public OtelController(Tracer tracer) {
    //     this.tracer = tracer;
    // }
    
    @GetMapping("/test")
    String getAPi(){
        Context currentContext = Context.current();
        Span currentSpan = Span.fromContext(currentContext);
        currentSpan.setAttribute("seller_id", "java123");
        LOGGER.error("Some error from Spring Boot app");
        return "Spring boot application is working Fine";
    }

    protected String sellerIdGenerator() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
