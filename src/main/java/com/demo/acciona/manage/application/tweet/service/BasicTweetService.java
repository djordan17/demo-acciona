package com.demo.acciona.manage.application.tweet.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.demo.acciona.manage.application.tweet.repository.TweetRepository;
import com.demo.acciona.manage.domain.Tweet;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class BasicTweetService implements TweetService
{

    private final TweetRepository tweetRepository;

    @Override
    public List<Tweet> findAll(Optional<Long> user,
                               Optional<Boolean> validation)
    {
        final List<Tweet> tweets = tweetRepository.findAll(user, validation);

        if (tweets.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return tweets;

    }

    @Transactional
    @Override
    public Tweet update(final Integer id,
                        final Tweet tweetRequest) throws Exception
    {
        tweetRepository.findById(id).orElseThrow(() -> new Exception("Not found"));
        return tweetRepository.save(tweetRequest);
    }

}
