package com.anhtuan.springframework.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class HomeController {

    private final Map<String, SseEmitter> sses = new ConcurrentHashMap<>();

    @Bean
    IntegrationFlow inboundFlow(@Value( "${input:file://${HOME}/Desktop/in}" )File in){
        return IntegrationFlows.from(Files.inboundAdapter(in).autoCreateDirectory(true),
                poller -> poller.poller(pollerFactory -> pollerFactory.fixedDelay(1000L)))
                .transform(File.class, File::getAbsolutePath)
                .handle(String.class, new GenericHandler<String>() {
                    @Override
                    public Object handle(String path, Map<String, Object> map) {
                        sses.forEach((s, sseEmitter) -> {
                            try {
                                sseEmitter.send(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        return null;
                    }
                }).get();
    }

    @GetMapping("/stream/{name}")
    public SseEmitter file(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L);
        sses.put(name, sseEmitter);
        return sseEmitter;
    }

    @GetMapping("/")
    public String test(){
        return "test";
    }


}
