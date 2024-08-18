# RESTful API для отслеживания почтовых отправлений

## Описание проекта

Проект представляет собой RESTful API для отслеживания почтовых отправлений. Система позволяет:
- Регистрирация почтового отправления.
- Его прибытие в промежуточное почтовое отделение
- Его убытие из почтового отделения 
- Его получение адресатом
- Просмотр статуса и полной истории движения почтового отправления

## Подготовка и настройка проекта

### Клонирование репозитория

Для начала клонируйте репозиторий с проектом:

```bash
git clone https://github.com/username/repository.git
cd repository
```

### Настройка базы данных:
Убедитесь, что PostgreSQL установлен и запущен. Создайте базу данных и пользователя:
```sql
CREATE DATABASE mediasoft_test;
CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE mediasoft_test TO postgres;
```

### Настройка переменных окружения:
Перед запуском приложения установите переменные окружения:

В PowerShell
```powershell
$env:DB_NAME="mediasoft_test"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
```
В Linux/MacOS:
```powershell
export DB_NAME=mediasoft_test
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
```

### Сборка проекта:
Сборка с Gradle: 
```powershell
./gradlew clean build
```
## Запуск приложения:
После сборки вы получите war файл в build/libs директории. Запустите его следующим образом:
```powershell
java -jar build/libs/MediaSoftTest-0.0.1-SNAPSHOT.war
```
## Доступ к Swagger UI:
После запуска приложения откройте Swagger UI для ознакомления с доступными API и тестирования запросов по адресу: http://localhost:8080/swagger-ui.html.

## Описание API
1. Регистрация почтового отправления
   - Endpoint: POST /api/v1/postal-items
   - Request Body:
   ```json
   {
       "type": "LETTER",
       "recipientIndex": 123456,
       "recipientAddress": "123 Main St",
       "recipientName": "John Doe",
       "postOfficeId": 2
   }
   ```
   - Response:
   ```json
   {
       "id": 1,
       "type": "LETTER",
       "recipientIndex": 123456,
       "recipientAddress": "123 Main St",
       "recipientName": "John Doe"
   }
   ```
2. Изменение статуса отправления
   Для прибытия в промежуточное отделение:
   - Endpoint: POST /api/v1/status-change/arrivalAtIntermediatePoint
   - Request Body:
   ```json
   {
      "postItemId": 1,
      "postOfficeId": 2
   }
   ```
   Для отправления из промежуточного отделения:
   - Endpoint: POST /api/v1/status-change/departureFromIntermediatePoint
   - Request Body:
   ```json
   {
      "id": 1
   }
   ```
   Для получения адресатом:
   - Endpoint: POST /api/v1/status-change/receiptByAddressee
   - Request Body:
   ```json
   {
      "id": 1
   }
   ```
3. Получение статуса и истории
   - Endpoint: GET /api/v1/tracking/{id}
   - Response:
   ```json
   {
      "status": "RECEIVED",
      "historyTracking": [
      {
         "timestamp": "2024-08-17T21:37:38.79434",
         "status": "RECEIVED",
         "postOffice": "Адрес пункта: Ульяны Громовой - Индекс пункта: 432027"
         },
         {
         "timestamp": "2024-08-17T21:35:05.254899",
         "status": "READY_TO_RECEIVE",
         "postOffice": "Адрес пункта: Ульяны Громовой - Индекс пункта: 432027"
         },
         {
         "timestamp": "2024-08-17T21:35:05.208371",
         "status": "ARRIVED_AT_THE_POST_OFFICE",
         "postOffice": "Адрес пункта: Ульяны Громовой - Индекс пункта: 432027"
         },
         {
         "timestamp": "2024-08-17T21:21:59.489304",
         "status": "LEFT_FROM_THE_POST_OFFICE",
         "postOffice": "Адрес пункта: Нариманова - Индекс пункта: 432030"
         },
         {
         "timestamp": "2024-08-17T20:55:49.530797",
         "status": "REGISTER",
         "postOffice": "Адрес пункта: Нариманова - Индекс пункта: 432030"
         }
      ]
   }
   ```