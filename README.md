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

* @EnableR2dbcRepositories 적용

## 5. R2DBC Transaction manager 

* @EnableTransactionManagement 적용 후 ReactiveTransactionManager 을 구현한 R2dbcTransactionManager bean 등록

```java
@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement // annotation 적용
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

    
    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new R2dbcTransactionManager(connectionFactory); // bean 등록
    }
}
```

## 6. ReactiveCrudRepository 사용

* spring-data-repository에서 ReactiveCrudRepository 를 사용하여 r2dbc 사용이 가능함.
* 현재 r2dbc는 메소드명으로 쿼리가 동적으로 작성되지 않는다. 
  * @Query를 사용하여 쿼리를 직접 작성해야함.
  
```java
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    @Query("SELECT * FROM person WHERE name = :name")
    Flux<Person> findByName(String name);
}
```

## 7. 테스트를 위한 schema 생성 설정

* ConnectionFactoryInitializer 를 사용하여 schema와 초기 데이터를 셋팅 가능함.

```java
@Configuration
public class TestDataBaseConfig {

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory){
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("sql/db-schema.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
```

## 현재 발생한 문제점

* spring 내부에서 SimpleR2dbcRepository 를 사용하여 쿼리를 실행함.
* save 호출시 Entity에 선언된 @Id를 기준으로 값이 null 이면 insert null이 아니면 update 호출.
  * jpa 와 유사한 방식으로 사용할 방안이 없나 리서치 중