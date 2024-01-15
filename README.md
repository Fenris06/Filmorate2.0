# java-filmorate 2.0
Это вторая реализация моего учебного проека java-filmorate. Данный сервис позволяет хранить описание фильмов,  ставить лайки фильмам и получать топ фильмов по лайкам. У пользователей реализована возможность дружбы. Сервир разбит на три модуля: 
* **validatiom-service** осуществляет валидацию полей входящих Dto объектов. Валидация происходи при помощи javax.validation.constraints и кастомных аннотаций. В случаи не ошибки в полях выбрасывается перехватываемое исключение и сервис отпровлят ответ с кодом 400 и кратким описанием ошибки. При успешном прохождении валидации сервис при помощи веб клиента webClient отпровляеи запров в основной сервис. Так же в этом сервесы добавлено автоматическая генирация документации в  swager.
* **Filmorate-Utilits** хнанит общие классы которые используются в двух сервисах.
* **Filmorate-service** основной сервис приложения. В нем содержится вся бизнесслогика и реализована взаимодестивие с базой данны через ORM framework Hibernate.
# Technologies Used
* Java 11
* Springboot 7.7.9
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
* Реализовать общий webClient для всех классов с исполхованием дженериков.
* Реализовать аутентификацию через JWT токен и Spring Security
