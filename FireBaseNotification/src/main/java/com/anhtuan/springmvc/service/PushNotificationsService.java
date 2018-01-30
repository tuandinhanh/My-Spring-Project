package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.Interceptor.HeaderRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class PushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAA4hg_vcU:APA91bEv6RIeoqPnkZcN-OA0aRhf8vXxMcUaLcvzTLFg7Nor7UAp5SWwtNsf0JECXEAVAzCw3Zi12j_QNXU9HX2AaptEN2YcYtvZFqdCPPkoI6vZd5Cer3ZkppW6-yswm4ehXVnad1V9";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String FIREBASE_SENDER_ID = "971069439429";

    @Async
    public CompletableFuture send(HttpEntity<String> entity) {
        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Sender", "id=" + FIREBASE_SENDER_ID));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
