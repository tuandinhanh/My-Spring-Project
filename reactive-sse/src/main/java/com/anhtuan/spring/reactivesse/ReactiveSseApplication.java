package com.anhtuan.spring.reactivesse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.Map;

@SpringBootApplication
@RestController
public class ReactiveSseApplication {

	@Bean
	SubscribableChannel fileChannel() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean
	IntegrationFlow integrationFlow(@Value("${input-dir:file://${HOME}/Desktop/in}") File in) {
		return IntegrationFlows.from(Files.inboundAdapter(in).autoCreateDirectory(true),
				poller -> poller.poller(spec -> spec.fixedDelay(1000L)))
				.transform(File.class, File::getAbsolutePath)
                .channel(fileChannel())
				.get();
	}

	@GetMapping(value = "/file/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> file(@PathVariable String name) {
		return Flux.create(fluxSink -> {
		    MessageHandler handler = message -> fluxSink.next(String.class.cast(message.getPayload()));
		    fileChannel().subscribe(handler);
            fluxSink.onCancel(() -> fileChannel().unsubscribe(handler));

        });
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSseApplication.class, args);
	}
}
