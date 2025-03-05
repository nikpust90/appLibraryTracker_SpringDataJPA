package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private String lastMessage = null;

    @RabbitListener(queues = "peopleQueue")
    public void receiveMessage(String message) {
        System.out.println("Получено из RabbitMQ: " + message);
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
