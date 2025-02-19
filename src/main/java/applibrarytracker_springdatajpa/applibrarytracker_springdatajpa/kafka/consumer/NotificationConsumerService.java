package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Сервис, который слушает Kafka-топик и отправляет email-уведомления пользователям.
 */
@Service
public class NotificationConsumerService {
    private final JavaMailSender mailSender;
    private final KafkaConsumer<String, String> consumer;

    @Autowired
    public NotificationConsumerService(JavaMailSender mailSender) {
        this.mailSender = mailSender;

        // Настройки Kafka-консьюмера
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Адрес брокера Kafka
        props.put("group.id", "notification-group"); // Группа консьюмеров
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Создаем консьюмера Kafka
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("user-notifications")); // Подписка на топик

        // Запускаем в отдельном потоке
        new Thread(this::consumeMessages).start();
    }

    /**
     * Слушает Kafka и обрабатывает полученные сообщения
     */
    private void consumeMessages() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Получено сообщение: " + record.value() + " для " + record.key());
                sendEmail(record.key(), "Новое уведомление", record.value());
            }
        }
    }

    /**
     * Отправляет email-уведомление пользователю
     * @param to Email получателя
     * @param subject Тема письма
     * @param text Текст письма
     */
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Отправлено email-уведомление на " + to);
    }
}
