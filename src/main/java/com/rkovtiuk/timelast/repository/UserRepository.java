package com.rkovtiuk.timelast.repository;

import com.rkovtiuk.timelast.entity.User;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Publisher<Void> save(Mono<User> user);
}
