package com.management;

import com.management.web.ManagementApiController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan("com.management")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
