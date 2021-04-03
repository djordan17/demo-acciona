package com.demo.acciona.service;

import java.util.List;

import com.demo.acciona.dto.TweetRsDto;
import com.demo.acciona.dto.UpdateTweetRqDto;

public interface TwitterService
{

    List<TweetRsDto> findAll(Boolean isValid);

    TweetRsDto update(final Integer id,
                      final UpdateTweetRqDto updateTweetRqDto) throws Exception;

}
