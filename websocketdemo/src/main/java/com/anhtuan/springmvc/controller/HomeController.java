package com.anhtuan.springmvc.controller;

import com.anhtuan.springmvc.model.Message;
import com.anhtuan.springmvc.model.OutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import javax.ws.rs.Path;
import java.util.Date;


@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "thymeleaf/home.html";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutputMessage sendMessage(Message message) throws InterruptedException {
        Thread.sleep(2000);
        Date time = new Date();
        System.out.println("--------------------"+ message.toString()+ " : " + time.toString() + "-----------------------");
        return new OutputMessage(message, time);
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ResponseEntity getTest(){
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    @Path("/stats")
    public ResponseEntity<WebSocketMessageBrokerStats> showStats(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
