package com.demo.acciona.listener;

import lombok.AllArgsConstructor;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TwitterRecieve
{

    //private TwitterConfig config;

    /*public void recieve() {
        StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                System.out.println(status.getUser().getName() + " : " + status.getText());
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}

            @Override
            public void onScrubGeo(long l,
                                   long l1)
            {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning)
            {

            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        config.getTwitterinstance();
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
        twitterStream.sample();
    }*/



}
