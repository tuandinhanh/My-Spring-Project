package com.anhtuan.spring.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@EnableIntegration
@IntegrationComponentScan
@ComponentScan

public class HomeController {

    private final Map<String, SseEmitter> sses = new ConcurrentHashMap<>();

    @RequestMapping(value = "/")
    public String homeThymeleaf() {
        return "thymeleaf/home.html";
    }

    @RequestMapping(value = "/home")
    public String homeJSP() {
        return "jsp/home.jsp";
    }

    @RequestMapping(value = "/test", produces = "application/json")
    @ResponseBody
    public ResponseEntity test() {
        String s = "asdasda";
        for (int i = 0; i < 10; i++) {
            sses.forEach((s1, sseEmitter) -> {
                try {
                    sseEmitter.send(s1 + " : fdfsdf");
                    Thread.sleep(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return new ResponseEntity(s, HttpStatus.OK);
    }



    @RequestMapping(value = "/file/{name}")
    public SseEmitter files(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L);
        sses.put(name, sseEmitter);
        return sseEmitter;
    }

    @Bean
    IntegrationFlow inboundFlow(@Value("${input-dir:file://${HOME}/Pictures/omg}")File in) {
        return IntegrationFlows.from(Files.inboundAdapter(in).autoCreateDirectory(true),
                poller -> poller.poller(spec -> spec.fixedDelay(1000L)))
                .transform(File.class, File::getAbsolutePath)
                .handle(String.class, (path, map) -> {

                    sses.forEach((k,sse) -> {
                        try {
                            sse.send(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    return null;
                })
                .get();
    }
}
