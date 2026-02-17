package org.example.webstore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootWebstoreApplicationTests {

    @BeforeAll
    static void boforeAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @Test
    void contextLoads1() {
        System.out.println("contextLoads1");
    }

    @Test
    void contextLoads2() {
        System.out.println("contextLoads2");
    }


//    Before All
//      .   ____          _            __ _ _
//     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
//     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//      '  |____| .__|_| |_|_| |_\__, | / / / /
//     =========|_|==============|___/=/_/_/_/
//
//    :: Spring Boot ::                (v3.4.1)
//    ...
//    StartupListener PostConstruct done.
//    StartupListener2 PostConstruct done.
//    2026-02-17T14:49:08.994+01:00  INFO 21340 --- [spring-boot-webstore] [           main] o.e.w.SpringBootWebstoreApplicationTests : Started SpringBootWebstoreApplicationTests in 13.833 seconds (process running for 16.675)
//    ApplicationStartedEvent fired!
//    Application started!
//    Application is ready!
//    OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
//    Before Each
//    contextLoads1
//    Before Each
//    contextLoads2
}
