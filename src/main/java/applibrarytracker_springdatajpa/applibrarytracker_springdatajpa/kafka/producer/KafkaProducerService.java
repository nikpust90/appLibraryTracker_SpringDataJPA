package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.springframework.stereotype.Service;
import java.util.Properties;

/**
 * Сервис для отправки уведомлений через Kafka.
 */
@Service
public class KafkaProducerService {
    private final Producer<String, String> producer;

    public KafkaProducerService() {
        // Настройки Kafka-продюсера
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Адрес брокера Kafka
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Создаем продюсер Kafka
        this.producer = new KafkaProducer<>(props);
    }

    /**
     * Отправка сообщения в Kafka-топик
     * @param email Email пользователя
     * @param message Сообщение для отправки
     */
    public void sendNotification(String email, String message) {
        producer.send(new ProducerRecord<>("user-notifications", email, message));
        System.out.println("Отправлено уведомление: " + message + " для " + email);
    }
}
