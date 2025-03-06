package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.rabbitMQ;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Config.RabbitMQConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Getter
@Slf4j
@Service
public class RabbitMQConsumer {


    private String lastMessage = null;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        log.info("Получено из RabbitMQ: {}", message); // Логируем полученное сообщение
        lastMessage = message;
    }

}