package com.example.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class LoggerApplication {

    private final Logger log = LoggerFactory.getLogger(LoggerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
    }


    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setMaxPoolSize(20);
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(20);

        log.info("Thread pool size: {}", executor.getCorePoolSize());
        executor.setThreadNamePrefix("asyncDao-");
        executor.initialize();

        executor.setTaskDecorator(new LoggerTaskDecorator());
        return executor;
    }

}
