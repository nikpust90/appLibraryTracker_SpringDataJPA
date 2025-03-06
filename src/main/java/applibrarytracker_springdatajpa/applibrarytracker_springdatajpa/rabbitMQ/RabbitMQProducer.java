package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.rabbitMQ;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class RabbitMQProducer {


    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("peopleQueue", message);
        log.info("Отправлено в RabbitMQ: {}", message); // Логируем отправленное сообщение
    }
}

