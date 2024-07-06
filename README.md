# Gerenciador de Pedidos de Restaurantes

> Desenvolvendo Serviços de Gerenciamento de Pedidos de Restaurantes com Spring Cloud da DIO

## Tecnologias

- Java 21
- Spring Framework / Spring Boot (3.3.1)
- Spring Cloud Netflix / Spring Eureka Server and Client (4.1.2)
- Spring Cloud OpenFeign (Http client)
- Rest API
- H2 Database

### Bibliotecas

Eureka Server:

- spring-cloud-starter-netflix-eureka-server

Eureka clients:

- spring-cloud-starter-netflix-eureka-client
- spring-boot-starter-actuator
- spring-boot-admin-starter-server
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- h2database
- lombok

Order and Delivery services (Eureka clients):

- spring-cloud-starter-openfeign

## Diferenças com o projeto de referência da(s) live(s)

- Uso de DDD (Domain driven design) nos serviços
- Adicionado camada de service ao invés de usar o repositories diretamente nos controllers dos serviços
- Adicionado mais handlers no global exception handler dos serviços
- Adicionado requests e responses DTOs ao invés de usar diretamente as entities nos controllers dos serviços
- Uso do Jakarta validations nos requests DTOs dos serviços
- Chamadas entre microsserviços para validação dos dados usando Spring Cloud OpenFeign

## Documentations links

- [Spring Cloud Netflix](https://docs.spring.io/spring-cloud-netflix/reference/index.html)
- [Spring Cloud Eureka Server](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html#spring-cloud-eureka-server)
- [Spring Cloud OpenFeign](https://docs.spring.io/spring-cloud-openfeign/reference/index.html)
- [Spring Boot Admin](https://docs.spring-boot-admin.com/current/index.html)

## Spring Initializr

- [Eureka Server](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.3.1&packaging=jar&jvmVersion=21&groupId=me.dio.hiokdev&artifactId=restaurant-orders.eureka-server&name=restaurant-orders.eureka-server&description=Gerenciador%20de%20Pedidos%20de%20Restaurantes%20-%20Eureka%20Server&packageName=me.dio.hiokdev.restaurant-orders.eureka-server&dependencies=cloud-eureka-server)
- [Customer service](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.3.1&packaging=jar&jvmVersion=21&groupId=me.dio.hiokdev&artifactId=restaurant-orders.customer-api&name=restaurant-orders.customer-api&description=Gerenciador%20de%20Pedidos%20de%20Restaurantes%20-%20Customer%20Service&packageName=me.dio.hiokdev.restaurant-orders.customer-api&dependencies=cloud-eureka,actuator,codecentric-spring-boot-admin-server,web,data-jpa,h2,validation,lombok)
- [Order service](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.3.1&packaging=jar&jvmVersion=21&groupId=me.dio.hiokdev&artifactId=restaurant-orders.order-service&name=restaurant-orders.order-service&description=Gerenciador%20de%20Pedidos%20de%20Restaurantes%20-%20Order%20Service&packageName=me.dio.hiokdev.restaurant-orders.order-service&dependencies=cloud-eureka,actuator,web,data-jpa,h2,validation,lombok,codecentric-spring-boot-admin-server,cloud-feign)
- [Delivery service](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.3.1&packaging=jar&jvmVersion=21&groupId=me.dio.hiokdev&artifactId=restaurant-orders.delivery-service&name=restaurant-orders.delivery-service&description=Gerenciador%20de%20Pedidos%20de%20Restaurantes%20-%20Delivery%20Service&packageName=me.dio.hiokdev.restaurant-orders.delivery-service&dependencies=cloud-eureka,actuator,web,data-jpa,h2,validation,lombok,codecentric-spring-boot-admin-server,cloud-feign)

## Access

- Eureka server [http://localhost:9090/](http://localhost:9090/)
- Customers admin [http://localhost:8081/applications](http://localhost:8081/applications)

## Rodar

### Requisitos

- Java 21

### Comandos

- na raiz do repositório
- cd restaurant-orders.eureka-server
- ./mvnw package -DskipTests
- java -jar target/restaurant-orders.eureka-server-0.1.0.jar

- na raiz do repositório
- cd restaurant-orders.customer-service
- ./mvnw package -DskipTests
- java -jar target/restaurant-orders.customer-service-0.1.0.jar

- na raiz do repositório
- cd restaurant-orders.order-service
- ./mvnw package -DskipTests
- java -jar target/restaurant-orders.order-service-0.1.0.jar

- na raiz do repositório
- cd restaurant-orders.delivery-service
- ./mvnw package -DskipTests
- java -jar target/restaurant-orders.delivery-service-0.1.0.jar
