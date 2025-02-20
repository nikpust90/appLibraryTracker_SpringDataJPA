package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.kafka.producer;

import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.producer.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Properties;



/**
 * Сервис для отправки уведомлений через Kafka.
 */
@Service
public class KafkaProducerService {
    private final Producer<String, String> producer;

    public KafkaProducerService(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        // Настройки Kafka-продюсера
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers); // Читаем из application.properties или application.yml
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all"); // Гарантированная доставка
        props.put("retries", 3); // Если ошибка - попробуем повторно
        props.put("linger.ms", 5); // Минимальная задержка для отправки пакетов

        this.producer = new KafkaProducer<>(props);
    }

    /**
     * Отправка сообщения в Kafka-топик
     * @param email Email пользователя
     * @param message Сообщение для отправки
     */
    public void sendNotification(String email, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>("user-notifications", email, message);

        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Уведомление отправлено: " + message + " для " + email);
            } else {
                System.err.println("Ошибка отправки Kafka-сообщения: " + exception.getMessage());
                throw new RuntimeException("Ошибка отправки Kafka: ", exception);
            }
        });

        producer.flush(); // Принудительно отправляем сообщения
    }

    @PreDestroy
    public void close() {
        producer.close();
    }
}


