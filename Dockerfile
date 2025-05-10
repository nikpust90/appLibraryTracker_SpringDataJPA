# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл приложения в контейнер
COPY target/appLibraryTracker_SpringDataJPA-0.0.1-SNAPSHOT.jar /app/appLibraryTracker_SpringDataJPA-0.0.1-SNAPSHOT.jar

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "appLibraryTracker_SpringDataJPA-0.0.1-SNAPSHOT.jar"]