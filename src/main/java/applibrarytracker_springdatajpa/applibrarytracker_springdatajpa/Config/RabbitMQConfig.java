package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "peopleQueue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true); // делаем очередь долговечной (durable)
    }
}
