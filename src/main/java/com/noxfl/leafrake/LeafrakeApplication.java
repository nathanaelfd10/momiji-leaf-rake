package com.noxfl.leafrake;

import com.noxfl.leafrake.amqp.MessageReceiver;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LeafrakeApplication {

	@Bean
	public LeafRakeRunner runner() {
		return new LeafRakeRunner();
	}

	@Bean
	public Queue queue() {
		return new Queue("leaf-rake");
	}

	@Bean
	public MessageReceiver messageReceiver() {
		return new MessageReceiver();
	}

	public static void main(String[] args) {
		SpringApplication.run(LeafrakeApplication.class, args);
	}

}
