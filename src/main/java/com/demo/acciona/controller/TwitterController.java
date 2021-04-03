package com.demo.acciona.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.acciona.dto.TweetRsDto;
import com.demo.acciona.dto.UpdateTweetRqDto;
import com.demo.acciona.entity.Tweet;
import com.demo.acciona.service.TwitterService;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TwitterController
{

    private final TwitterService twitterService;

    @GetMapping()
    public List<TweetRsDto> findAll(@RequestParam(name = "isValid") Boolean isValid)
    {
        return twitterService.findAll(isValid);

    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TweetRsDto update(@PathVariable Integer id, @RequestBody UpdateTweetRqDto updateTweetRqDto) throws Exception
    {
        return twitterService.update(id, updateTweetRqDto);
    }

}
