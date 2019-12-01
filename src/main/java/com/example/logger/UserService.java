package com.example.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    //@Async
    public String getUserById(long id) {

        log.info("   Get user from DB by id: {}", id);
        return "USER_"+id+"_AA";
    }

}
