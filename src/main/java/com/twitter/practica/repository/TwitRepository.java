package com.twitter.practica.repository;

import com.twitter.practica.business.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TwitRepository {

    private final static List<User> users = Arrays.asList(
            new User(1L, "cortigeronimo@gmail.com", "1234"),
            new User(2L, "willi@gmail.com", "4321")
    );


}
