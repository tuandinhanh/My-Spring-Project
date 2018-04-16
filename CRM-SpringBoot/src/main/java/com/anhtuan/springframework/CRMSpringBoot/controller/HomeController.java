package com.anhtuan.springframework.CRMSpringBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class HomeController {

    private final Map<String, SseEmitter> sses = new ConcurrentHashMap<>();

    @GetMapping(value = "/")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/security")
    public UserDetails security(@AuthenticationPrincipal final UserDetails userDetails){

        String login = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return userDetails;
    }

    @GetMapping(value = "/stream/{name}")
    public SseEmitter stream(@PathVariable String name){
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L);
        sses.put(name, sseEmitter);
        return sseEmitter;
    }

    @Bean
    public IntegrationFlow inboundFlow(@Value("${input-dir:file://${HOME}/Desktop/in}") File in){
        return IntegrationFlows.from(Files.inboundAdapter(in).autoCreateDirectory(true),
                poller -> poller.poller(pollerFactory -> pollerFactory.fixedDelay(1000L)))
                .transform(File.class, File::getAbsolutePath)
                .handle(String.class, (path, map) -> {
                    sses.forEach((s, sseEmitter) -> {
                        try {
                            sseEmitter.send(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return null;
                })
                .get();
    }
}
