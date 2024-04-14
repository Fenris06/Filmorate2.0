# film-searcher
Данный сервис позволяет хранить описание фильмов, ставить лайки фильмам и получать топ фильмов по лайкам. У пользователей реализована возможность дружбы. Сервис разбит на три модуля:
* **validation-service** осуществляет валидацию полей входящих Dto объектов. Валидация происходи при помощи javax.validation.constraints и кастомных аннотаций. В случаи ошибки в полях Dto объектов выбрасывается перехватываемое исключение и сервис отправляет ответ с кодом 400 и кратким описанием ошибки. При успешном прохождении валидации сервис при помощи веб клиента webClient отправляет запрос в основной сервис. Так же в этом сервисе добавлено автоматическая генерация документации в swager.
* **film-searcher-utils** хранит общие классы которые используются в двух сервисах.
* **searcher-service** основной сервис приложения. В нем содержится бизнес логика и реализовано взаимодействие с базой данных через ORM framework Hibernate.
# Technologies Used
* Java 11
* Springboot 2.7.9
* Hibernate
* Lombok
* PostgreSql
* H2 database for test
* Spring webClien
* Swagger
* Maven
* Mockito
* JUnit 5
* Docker
# Database diagram
![database diagram](https://github.com/Fenris06/java-filmorate/blob/main/updatedatabase.png)
# Next updates
* Реализовать общий webClient для всех классов с использованием дженериков.
* Реализовать аутентификацию через JWT токен и Spring Security
