package com.rkovtiuk.timelast.repository;

import com.rkovtiuk.timelast.common.entity.User;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Publisher<Void> save(Mono<User> user);
}
