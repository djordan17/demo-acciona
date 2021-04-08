package com.demo.acciona.manage.application.tweet.service;

import java.util.List;
import java.util.Optional;

import com.demo.acciona.manage.domain.Tweet;

public interface TweetService
{

    List<Tweet> findAll(Optional<Long> user, Optional<Boolean> validation);

    Tweet update(final Integer id, final Tweet tweetRequest) throws Exception;
}
