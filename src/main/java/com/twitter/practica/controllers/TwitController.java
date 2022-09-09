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

        List<Twit> twits = twitService.getTwits(userId);

        List<TwitResponseDTO> twitResponse = twits.stream()
                .map(t -> new TwitResponseDTO(t.getId(), t.getContent(), t.getCreationDate().toLocalDate(), t.getLikes()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(twitResponse);
    }

    @PatchMapping("/user/{userId}/twits/{twitId}/like")
    public ResponseEntity<?> like(@PathVariable Long userId, @PathVariable Long twitId){
        twitService.like(userId, twitId);
        return ResponseEntity.ok().build();
    }

}

