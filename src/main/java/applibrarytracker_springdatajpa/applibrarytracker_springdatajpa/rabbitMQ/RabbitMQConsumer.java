package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
    private String lastMessage = null;

    @RabbitListener(queues = "peopleQueue")
    public void receiveMessage(String message) {
        logger.info("Получено из RabbitMQ: {}", message); // Логируем полученное сообщение
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}