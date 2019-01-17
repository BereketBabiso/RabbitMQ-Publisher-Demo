package com.berry.rabbitmq;

import java.util.concurrent.CompletableFuture;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqDemoPublisherApplication implements CommandLineRunner {

  @Autowired
  private RabbitTemplate rabbitTemplate;
  
  @Bean
  Binding binding() {
    return new Binding("firstqueue", Binding.DestinationType.QUEUE, "TestTopicExchange", "testkey", null);
  }
  
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqDemoPublisherApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
   System.out.println("Application just started.");
   
   CompletableFuture.runAsync(()->{
     rabbitTemplate.convertAndSend("hello there");
   });
    
  }

}

