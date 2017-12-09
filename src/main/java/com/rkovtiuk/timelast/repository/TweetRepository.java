package com.rkovtiuk.timelast.repository;


import com.rkovtiuk.timelast.common.entity.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {
    Mono<Void> save(Mono<Tweet> entity);
}
