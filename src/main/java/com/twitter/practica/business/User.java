package com.twitter.practica.business;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final Long id;

    private String email;

    private String password;

    private List<Twit> twits;

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.twits = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void twit(Twit twiw) {
        twits.add(twiw);
    }

    public List<Twit> getTwits() {
        return twits;
    }

    public Twit giveMeTheTwit(Long twitId) {
        return twits.stream()
                .filter(t -> t.getId().equals(twitId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}

