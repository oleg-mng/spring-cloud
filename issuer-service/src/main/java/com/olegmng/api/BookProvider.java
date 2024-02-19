package com.olegmng.api;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookProvider {
    // HttpClient - java.net
    // RestTemplate - spring.web
    //WebClient - spring.reactive

    private final WebClient webClient;
//    private final EurekaClient eurekaClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
//        this.eurekaClient = eurekaClient;
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public UUID getRandomBookId() {
        BookResponse randomBlock = webClient.get()
//                .uri("http://localhost:8180/api/book/random")
//                .uri(getBookServiceIp() + "/api/book/random")
                .uri( "http://book-service/api/book/random")
                .retrieve()
                .bodyToMono(BookResponse.class)
                .block();

        return randomBlock.getId();

    }

//    public String getBookServiceIp() {
//        Application application = eurekaClient.getApplication("BOOK-SERVICE");
//        List<InstanceInfo> instances = application.getInstances();
//
//        int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
//        InstanceInfo randomInstance = instances.get(randomIndex);
//        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
//
//    }


    @Data
    private static class BookResponse {
        private UUID id;
    }
}

