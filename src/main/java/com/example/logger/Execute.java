package com.example.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Execute implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Execute.class);

    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) {

        MDC.put("request_id", "128");
        MDC.put("application", "runner");

        log.info("HELLO 123");

        String userName = memberService.getUserName(123L);


        MDC.put("request_id", "225-");
        String userNameAsync1 = memberService.getUserNameAsync(225L);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MDC.put("request_id", "25-");
        String userNameAsync2 = memberService.getUserNameAsync(25L);
    }

}
