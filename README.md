# java-explore-with-me
Сервис-афиша, где можно предложить какое-либо событие и собрать компанию для участия в нём

Веб сервис написан на Java 11 на базе Spring Boot, имеет многомодульную структуру. В разработке использованы REST API, PostgreSQL, Hibernate ORM, Lombok, Docker

Краткое описание
Свободное время — ценный ресурс. Ежедневно мы планируем, как его потратить — куда и с кем сходить. Сложнее всего в таком планировании поиск информации и переговоры. Какие намечаются мероприятия, свободны ли в этот момент друзья, как всех пригласить и где собраться. EWM поможет организовать любое мероприятие, а также подскажет, куда можно пойти на выходных или в какой компании провести вечер. Создавайте события, принимайте в них желающих по-участвовать, а также участвуйте в событиях других пользователей и оставляйте свои комментарии к ним.

Запустить сервис можно в IntelliJ IDEA:

выполнив docker-compose.yml (для запуска в Docker)
или выполнить:
java-explore-with-me/ewm-service/src/main/resourses/schema.sql -> "run" java-explore-with-me/stats-server/src/main/resourses/schema.sql -> "run" (для инициализации БД главного сервиса и сервера статистики).
затем поочерёдно запустите StatsService (сервер статистики) и MainService (главный сервис) 
java-explore-with-me/stat-service/stat-server/src/main/java/ru/practicum/StatServiceApp 
java-explore-with-me/main-service/src/main/java/ru/practicum/MainServiceApp
