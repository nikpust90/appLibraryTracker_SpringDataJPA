package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RabbitMQConsumer {


    private String lastMessage = null;

    @RabbitListener(queues = "peopleQueue")
    public void receiveMessage(String message) {
        log.info("Получено из RabbitMQ: {}", message); // Логируем полученное сообщение
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}