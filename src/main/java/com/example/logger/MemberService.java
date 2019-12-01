package com.example.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final Logger log = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private UserService userService;

    public String getUserName(long id) {

        log.info(" Looking for user in service by id: {}", id);
        return userService.getUserById(id);

    }

    @Async
    @Cacheable("members")
    public String getUserNameAsync(long id) {
        log.info(" (ASYNC)Looking for user in service by id: {}", id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String userById = userService.getUserById(id);
        return userById;

    }


}
