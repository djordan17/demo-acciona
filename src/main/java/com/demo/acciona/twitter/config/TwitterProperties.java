package com.demo.acciona.twitter.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "twitter4j")
@Getter
@Setter
public class TwitterProperties
{

    private String apiKey;
    private String apiKeySecret;
    private String accessToken;
    private String accessTokenSecret;

}
