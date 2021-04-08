package com.demo.acciona.manage.infraestructure.tweet.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.acciona.manage.application.tweet.service.TweetService;
import com.demo.acciona.manage.domain.Tweet;
import com.demo.acciona.manage.infraestructure.tweet.dto.TweetDto;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("tweets")
public class TwitterController
{

    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<TweetDto> findAll(@RequestParam Optional<Long> user,
                                  @RequestParam Optional<Boolean> validation)
    {
        return tweetService.findAll(user, validation)
                           .stream()
                           .map(tweet -> modelMapper.map(tweet, TweetDto.class))
                           .collect(Collectors.toUnmodifiableList());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TweetDto update(@PathVariable Integer id,
                           @RequestBody TweetDto tweetDto) throws Exception
    {
        return modelMapper.map(
                tweetService.update(id,
                                    modelMapper.map(tweetDto, Tweet.class)
                                   ), TweetDto.class);
    }

}
