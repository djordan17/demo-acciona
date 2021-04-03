package com.demo.acciona.listener;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.CollectionUtils;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

@Slf4j
public class TwitterMessageProducer extends MessageProducerSupport {

    private final TwitterStream twitterStream;
    private StatusListener statusListener;
    private FilterQuery filterQuery;
    private List<String> terms;

    public TwitterMessageProducer(TwitterStream twitterStream, MessageChannel outputChannel) {
        this.twitterStream = twitterStream;
        setOutputChannel(outputChannel);
    }

    @Override
    protected void onInit() {
        super.onInit();

        statusListener = new StatusListener();

        long[] followsArray = null;

        String[] termsArray = null;
        if (!CollectionUtils.isEmpty(terms)) {
            termsArray = terms.toArray(new String[terms.size()]);
        }

        String [] lang = {"es", "fr", "it"};
        filterQuery = new FilterQuery();
        filterQuery.track(termsArray);
        filterQuery.language(new String[] {"es", "fr", "it"});
    }

    @Override
    public void doStart() {
        twitterStream.addListener(statusListener);
        twitterStream.filter(filterQuery);
    }

    @Override
    public void doStop() {
        twitterStream.cleanUp();
        twitterStream.clearListeners();
    }

    StatusListener getStatusListener() {
        return statusListener;
    }

    FilterQuery getFilterQuery() {
        return filterQuery;
    }

    class StatusListener extends StatusAdapter {

        @Override
        public void onStatus(Status status) {
            sendMessage(MessageBuilder.withPayload(status).build());
        }

        @Override
        public void onException(Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
            log.warn(warning.toString());
        }

    }

    public void setTerms(List<String> terms)
    {
        this.terms = terms;
    }
}