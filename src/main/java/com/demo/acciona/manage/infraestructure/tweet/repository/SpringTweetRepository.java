package com.demo.acciona.manage.infraestructure.tweet.repository;

import java.util.List;
import java.util.Optional;

import com.demo.acciona.manage.infraestructure.tweet.entity.TweetEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringTweetRepository extends CrudRepository<TweetEntity, Integer>, JpaSpecificationExecutor<TweetEntity>
{
    List<TweetEntity> findByUserAndValidation(Optional<Long> user, Optional<Boolean> validation);

}
