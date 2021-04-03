package com.demo.acciona.repository;

import com.demo.acciona.entity.Tweet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends CrudRepository<Tweet, Integer>
{
}
