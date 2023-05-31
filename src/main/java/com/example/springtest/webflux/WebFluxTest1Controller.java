package com.example.springtest.webflux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

@RestController
@RequestMapping("/api/v1/webfluxtest1")
public class WebFluxTest1Controller {

    
    @GetMapping("/test1_Publisher")
    public Flux<Long> test1(){

        System.out.println("=========================================");
        System.out.println("test1_Publisher");
        System.out.println("=========================================");

        return Flux.interval(Duration.ofSeconds(1))
                    .take(10);

    }

    public WebClient webClient() {
        final var client = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .doOnConnected(
                        connection ->
                                connection
                                        .addHandlerLast(new ReadTimeoutHandler(1000L, TimeUnit.SECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(1000L, TimeUnit.SECONDS)));

        return WebClient.builder()
                .baseUrl("http:://localhost:8080/")
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();

    }

    @GetMapping("test1_Subscriber")
    public Flux<Long> test2(){

        System.out.println("=========================================");
        System.out.println("test1_Subscriber");
        System.out.println("=========================================");

        final var client = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .doOnConnected(
                        connection ->
                                connection
                                        .addHandlerLast(new ReadTimeoutHandler(1000L, TimeUnit.SECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(1000L, TimeUnit.SECONDS)));

        WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080")
        .clientConnector(new ReactorClientHttpConnector(client))
        .build();

        return webClient.get()
            .uri("/api/v1/webfluxtest1/test1_Publisher")
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Long.class)
            .filter(number -> number%2 == 0)
            .map(number -> {System.out.println(number); return number;})
            .log();
            
    }
    
}
