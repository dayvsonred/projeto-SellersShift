package com.sellers.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidEmailQueue {
    @Value("${rabbitmq.valid-email.queue}")
    private String queue;

    @Value("${rabbitmq.valid-email.exchange}")
    private String exchange;

    @Value("${rabbitmq.valid-email.routing}")
    private String routingKey;

    @Bean
    Queue createQueue() {
        return new Queue(queue);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).
                to(exchange).with(routingKey);
    }

    @Bean
    SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
