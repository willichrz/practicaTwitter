package com.twitter.practica.services;

import com.twitter.practica.business.Twit;
import com.twitter.practica.business.User;
import com.twitter.practica.dto.TwitterCreationDTO;
import com.twitter.practica.repository.TwitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitService {

    private static Long twitCount = 1L;

    private final TwitRepository twitRepository;

    public TwitService(TwitRepository twitRepository) {
        this.twitRepository = twitRepository;
    }

    public void createTwit(Long userId, TwitterCreationDTO request){
        User user = twitRepository.getUser(userId);

        Twit twit = new Twit(twitCount, request.getTwit());
        user.twit(twit);

        twitCount++;
    }

    public List<Twit> getTwits(Long userId){
        User user = twitRepository.getUser(userId);
        List<Twit> twits = user.getTwits();
        return twits;
    }

    public User getUser(Long userId){
        return twitRepository.getUser(userId);
    }

    public void like(Long userId, Long twitId){
        User user = twitRepository.getUser(userId);
        Twit twit = user.giveMeTheTwit(twitId);
        twit.like();
    }

}
