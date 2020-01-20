# WebFlux + R2DBC 을 사용한 simple application

공식 문서

* https://projectreactor.io/docs/core/release/reference/index.html
* https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html
* https://docs.spring.io/spring-data/data-r2dbc/docs/1.1.x/reference/html/#reference

리엑티브 database driver

* https://github.com/r2dbc/r2dbc-h2
* https://spring.io/projects/spring-data-r2dbc
* https://github.com/mirromutth/r2dbc-mysql
* https://github.com/r2dbc/r2dbc-postgresql
* https://github.com/jasync-sql/jasync-sql

## 1. 의존성 설정

spring initializr 를 사용하여 아래 의존성 추가

* Spring Reactive Web
* Spring Data R2DBC
* H2 Database
* Spring REST Docs

## 2. 스프링 자바 bean 설정

AbstractR2dbcConfiguration 클래스를 상속받아서 bean 설정이 가능하다.

* io.r2dbc.spi.ConnectionFactory 스프링을 사용하는 컨테이너와 함께 표준 인터페이스 사용가능.
* R2DBC 의 ExceptionTranslator 를 @Repository 기반에 DataAccessException 계층 구조로 변환하는 이점을 제공.  

```java
@Configuration
public class DataBaseConfig extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER,"h2")
                .option(PROTOCOL, "mem")
                .option(DATABASE,"test")
                .build());

        return connectionFactory;
    }
}
```

## 3. R2DBC 드라이버 방언 

* R2DBC 드라이버는 스프링의 DialectResolver가 SpringFactories 를 사용하여 클래스패스의 방언들을 검색 및 로딩함.

## 4. R2DBC Repository

* @EnableR2dbcRepositories 를 사용
