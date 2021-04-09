package com.demo.acciona.manage.application.tweet.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.demo.acciona.manage.application.tweet.repository.TweetRepository;
import com.demo.acciona.manage.domain.Tweet;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasicTweetServiceTest
{

    @Mock
    private TweetRepository tweetRepository;
    private PodamFactory podamFactory;
    private List<Tweet> tweetsMock;
    private Optional<Tweet> tweetMock;
    private BasicTweetService basicTweetService;

    @BeforeEach
    public void setUp()
    {
        podamFactory = new PodamFactoryImpl();
        basicTweetService = new BasicTweetService(tweetRepository);

        tweetsMock = podamFactory.manufacturePojo(List.class, Tweet.class);
        tweetMock = Optional.of(podamFactory.manufacturePojo(Tweet.class));
        tweetMock.get().setId(5);

    }

    @Test
    void should_return_tweets_when_user_or_and_validation_is_empty()
    {

        doReturn(tweetsMock).when(tweetRepository).findAll(eq(Optional.empty()), eq(Optional.empty()));

        List<Tweet> tweetsService = basicTweetService.findAll(Optional.empty(), Optional.empty());

        verify(tweetRepository, atLeastOnce()).findAll(eq(Optional.empty()), eq(Optional.empty()));
        assertTrue(() -> tweetsService.size() > 0);
    }

    @Test
    void should_return_tweets_when_user_or_and_validation_is_not_empty()
    {

        Optional<Long> user = Optional.of(123456L);
        doReturn(tweetsMock).when(tweetRepository).findAll(eq(user), eq(Optional.empty()));

        List<Tweet> tweetsService = basicTweetService.findAll(user, Optional.empty());

        verify(tweetRepository, atLeastOnce()).findAll(eq(user), eq(Optional.empty()));
        assertTrue(() -> tweetsService.size() > 0);
    }

    @Test
    void should_return_collection_tweets_empty_when_user_or_and_validation_is_not_empty()
    {

        Optional<Long> user = Optional.of(123456L);
        tweetsMock = Collections.EMPTY_LIST;
        doReturn(tweetsMock).when(tweetRepository).findAll(eq(user), eq(Optional.empty()));

        List<Tweet> tweetsService = basicTweetService.findAll(user, Optional.empty());

        verify(tweetRepository, atLeastOnce()).findAll(eq(user), eq(Optional.empty()));
        assertEquals(tweetsService.size(), 0);
    }

    @Test
    void should_return_tweet_updated_when_search_by_id_and_find_it() throws Exception
    {

        final Integer id = 5;
        Tweet tweetRq = new Tweet();
        tweetRq.setId(5);
        tweetRq.setText("text");
        tweetRq.setUser(123456L);
        tweetRq.setLocatization("localization");
        tweetRq.setValidation(true);
        doReturn(tweetMock).when(tweetRepository).findById(id);
        doReturn(tweetRq).when(tweetRepository).save(eq(tweetRq));


        final Tweet tweetService = basicTweetService.update(id, tweetRq);

        verify(tweetRepository, atLeastOnce()).findById(eq(id));
        verify(tweetRepository, atLeastOnce()).save(eq(tweetRq));
        assertEquals(tweetService, tweetRq);
    }

    @Test
    void should_return_exception_when_search_by_id_and_not_find_it() throws Exception
    {

        final Integer id = 5;
        Tweet tweetRq = new Tweet();
        tweetRq.setId(5);
        tweetRq.setText("text");
        tweetRq.setUser(123456L);
        tweetRq.setLocatization("localization");
        tweetRq.setValidation(true);
        doReturn(Optional.empty()).when(tweetRepository).findById(id);

        Exception exception = assertThrows(Exception.class, () -> basicTweetService.update(id, tweetRq));
        assertEquals("Not found", exception.getMessage());
        verify(tweetRepository, atLeastOnce()).findById(eq(id));
    }

}

