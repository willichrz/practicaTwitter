package com.twitter.practica.controllers;

import com.twitter.practica.business.Twit;
import com.twitter.practica.business.User;
import com.twitter.practica.dto.TwitResponseDTO;
import com.twitter.practica.dto.TwitterCreationDTO;
import com.twitter.practica.services.TwitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TwitController {

    private final static List<User> users = Arrays.asList(
            new User(1L, "geronimocorti@gmail.com", "123456"),
            new User(2L, "willichrz@gmail.com", "asdasd")
    );


    private final TwitService twitService;


    @Autowired
    public TwitController(TwitService twitService){
        this.twitService = twitService;
    }


    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/twits")
    public ResponseEntity<?> createTwit(@RequestBody TwitterCreationDTO request, @PathVariable Long userId){

        twitService.createTwit(userId, request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/twits")
    public ResponseEntity<List<TwitResponseDTO>> getTwits(@PathVariable Long userId){
        User user = getuser(userId);

        List<Twit> twits = user.getTwits();

        List<TwitResponseDTO> twitResponse = twits.stream()
                .map(t -> new TwitResponseDTO(t.getId(), t.getContent(), t.getCreationDate().toLocalDate(), t.getLikes()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(twitResponse);
    }

    @PatchMapping("/user/{userid}/twits/{twitId}/like")
    public ResponseEntity<?> like(@PathVariable Long userId, @PathVariable Long twitId){
        User user = getuser(userId);
        Twit twit = user.giveMeTheTwit(twitId);
        twit.like();
        return ResponseEntity.ok().build();
    }

    private User getuser(Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}

