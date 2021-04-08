package com.demo.acciona.manage.infraestructure.tweet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import com.demo.acciona.manage.application.tweet.repository.TweetRepository;
import com.demo.acciona.manage.domain.Tweet;
import com.demo.acciona.manage.infraestructure.tweet.entity.TweetEntity;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TweetDboRepository implements TweetRepository
{
    private final SpringTweetRepository springTweetRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Tweet> findAll(Optional<Long> user,
                               Optional<Boolean> validation)
    {
        return springTweetRepository.findAll(filterTweet(user, validation))
                                    .stream()
                                    .map(tweet -> modelMapper.map(tweet, Tweet.class))
                                    .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Tweet save(Tweet tweet)
    {
        return modelMapper.map(
                springTweetRepository.save(
                        modelMapper.map(tweet, TweetEntity.class)),
                Tweet.class
                              );
    }

    @Override
    public Optional<Tweet> findById(Integer id)
    {
        final Optional<TweetEntity> tweet = springTweetRepository.findById(id);

        if (tweet.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(modelMapper.map(tweet.get(), Tweet.class));
    }

    private Specification<TweetEntity> filterTweet(Optional<Long> user,
                                                   Optional<Boolean> validation)
    {

        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (user.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get("user"), user.get()));
            }
            if (validation.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get("validation"), validation.get()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
