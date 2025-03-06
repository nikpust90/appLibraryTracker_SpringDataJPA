# Этап сборки
FROM openjdk:17-jdk-slim as builder

# Создаем директорию для логов
RUN mkdir -p /app/logs && chmod -R 777 /app/logs
ENV LOG_DIR=/app/logs

# Устанавливаем рабочую директорию
WORKDIR /app

# Используем переменную для имени JAR-файла
ARG JAR_FILE=target/appLibraryTracker_SpringDataJPA-0.0.1-SNAPSHOT.jar

# Копируем JAR-файл в контейнер
COPY ${JAR_FILE} app.jar

# Используем минимальный базовый образ
FROM openjdk:17-jdk-slim

# Создаем пользователя без root-прав
RUN useradd -m appuser
USER appuser

# Устанавливаем рабочую директорию
WORKDIR /app

# Создаем директорию для логов
RUN mkdir -p /app/logs && chmod -R 777 /app/logs
ENV LOG_DIR=/app/logs

# Копируем JAR-файл из предыдущего этапа
COPY --from=builder /app/app.jar app.jar

# Открываем порт
EXPOSE 8081

# Настраиваем параметры JVM для логирования
# Включаем вывод логов в консоль
ENTRYPOINT ["java", "-jar", "app.jar", \
    "--logging.file.name=/app/logs/app.log", \
    "--logging.level.root=INFO", \
    "--logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"]
