package com.demo.acciona.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.acciona.dto.TweetRsDto;
import com.demo.acciona.dto.UpdateTweetRqDto;
import com.demo.acciona.entity.Tweet;
import com.demo.acciona.repository.TwitterRepository;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BasicTwitterService implements TwitterService
{

    private final TwitterRepository twitterRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TweetRsDto> findAll(Boolean valid)
    {
        Specification specification = isValid(valid);
        final Optional<Iterable<Tweet>> tweets = Optional.ofNullable(twitterRepository.findAll());

        if (tweets.isPresent()) {
            return tweets.stream()
                         .map(tweet -> modelMapper.map(tweet, TweetRsDto.class))
                         .collect(Collectors.toList());
        }

        return Collections.EMPTY_LIST;
    }

    @Transactional
    @Override
    public TweetRsDto update(final Integer id, final UpdateTweetRqDto updateTweetRqDto) throws Exception
    {
        final Optional<Tweet> tweet = twitterRepository.findById(id);

        final Tweet tweetSave = tweet.orElseThrow(() -> new Exception("Not found"));
        tweetSave.setValidation(updateTweetRqDto.getIsValid());
        final Tweet tweetSaved =  twitterRepository.save(tweetSave);

        return modelMapper.map(tweetSaved, TweetRsDto.class);

    }

    private static Specification<Tweet> isValid(Boolean valid) {
        return (tweet, cq, cb) -> valid == null ? cb.conjunction() : cb.equal(tweet.get("validation"), valid);
    }
}
