# RESTful API для отслеживания почтовых отправлений

## Описание проекта

Проект представляет собой RESTful API для отслеживания почтовых отправлений. Система позволяет:
- Регистрировать почтовые отправления.
- Отслеживать их перемещения через почтовые отделения.
- Изменять статусы отправлений.
- Получать полную историю их перемещений.

## Подготовка и настройка проекта

### Клонирование репозитория

Для начала клонируйте репозиторий с проектом:

git clone https://github.com/username/repository.git
cd repository

### Настройка базы данных:
Убедитесь, что PostgreSQL установлен и запущен. Создайте базу данных и пользователя:
CREATE DATABASE mediasoft_test;
CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE mediasoft_test TO postgres;

### Настройка переменных окружения:
Перед запуском приложения установите переменные окружения:

В PowerShell
$env:DB_NAME="mediasoft_test"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"

В Linux/MacOS:
export DB_NAME=mediasoft_test
export DB_USERNAME=postgres
export DB_PASSWORD=postgres

### Сборка проекта:
Сборка с Gradle: 

./gradlew clean build

## Запуск приложения:
После сборки вы получите war файл в build/libs директории. Запустите его следующим образом:

java -jar build/libs/MediaSoftTest-0.0.1-SNAPSHOT.war

## Доступ к Swagger UI:
После запуска приложения откройте Swagger UI для ознакомления с доступными API и тестирования запросов по адресу: http://localhost:8080/swagger-ui.html.