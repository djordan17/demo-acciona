package com.demo.acciona.twitter;

import java.util.Arrays;

import com.demo.acciona.manage.infraestructure.tweet.entity.TweetEntity;
import com.demo.acciona.twitter.listener.TwitterMessageProducer;
import com.demo.acciona.manage.infraestructure.tweet.repository.SpringTweetRepository;
import com.demo.acciona.twitter.config.TwitterProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
@EnableConfigurationProperties(TwitterProperties.class)
@AllArgsConstructor
public class TwitterConfig
{

    private TwitterProperties twitterProperties;
    private SpringTweetRepository twitterRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public TwitterMessageProducer twitterMessageProducer(
            TwitterStream twitterStream,
            MessageChannel outputChannel)
    {

        TwitterMessageProducer twitterMessageProducer =
                new TwitterMessageProducer(twitterStream, outputChannel);

        twitterMessageProducer.setTerms(Arrays.asList("java", "spring"));
        twitterMessageProducer.setLanguages(Arrays.asList("es", "fr", "it", "en"));
        return twitterMessageProducer;
    }

    @Bean
    public TwitterStreamFactory twitterStreamFactory()
    {
        final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
          .setOAuthConsumerKey(twitterProperties.getApiKey())
          .setOAuthConsumerSecret(twitterProperties.getApiKeySecret())
          .setOAuthAccessToken(twitterProperties.getAccessToken())
          .setOAuthAccessTokenSecret(twitterProperties.getAccessTokenSecret());
        return new TwitterStreamFactory(configurationBuilder.build());
    }

    @Bean
    public TwitterStream twitterStream(TwitterStreamFactory twitterStreamFactory)
    {
        return twitterStreamFactory.getInstance();
    }

    @Bean
    public MessageChannel outputChannel()
    {
        return MessageChannels.direct().get();
    }

    @Bean
    public DirectChannel saveChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow twitterFlow(MessageChannel outputChannel)
    {
        return IntegrationFlows.from(outputChannel)
                               .filter(Message.class, m -> ((Status)m.getPayload()).getUser().getFollowersCount() > 1500)
                               .log(v -> "Number followers: "+ ((Status)v.getPayload()).getUser().getFollowersCount())
                               .log(v -> "Payload: "+ ((Status)v.getPayload()))
                               .channel(saveChannel())
                               .get();

    }

    @Bean
    public org.springframework.integration.dsl.IntegrationFlow saveFlow() {
        return IntegrationFlows
                .from(saveChannel())
                .handle(message -> {
                    final Status status = ((Status)message.getPayload());
                    twitterRepository.save(
                            TweetEntity.builder()
                                       .user(status.getUser().getId())
                                       .text(status.getText())
                                       .locatization(status.getGeoLocation() != null ? status.getGeoLocation().toString() : null)
                                       .validation(status.getUser().isVerified())
                                       .build());
                })
                .get();
    }

}
