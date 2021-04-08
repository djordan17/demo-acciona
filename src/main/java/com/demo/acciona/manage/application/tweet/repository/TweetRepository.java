package com.demo.acciona.manage.application.tweet.repository;

import java.util.List;
import java.util.Optional;

import com.demo.acciona.manage.domain.Tweet;

public interface TweetRepository
{
    List<Tweet> findAll(Optional<Long> user, Optional<Boolean> validation);
    Tweet save(Tweet tweet);
    Optional<Tweet> findById(Integer id);

}
