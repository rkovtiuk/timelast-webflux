package com.rkovtiuk.timelast.handler;

import com.rkovtiuk.timelast.entity.Tweet;
import com.rkovtiuk.timelast.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class TweetHandler {

    private final TweetRepository repository;

    @Autowired
    public TweetHandler(TweetRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Tweet> tweet = request.bodyToMono(Tweet.class);
        return ServerResponse.ok().build(this.repository.save(tweet));
    }

    public Mono<ServerResponse> all(ServerRequest request) {
        Flux<Tweet> tweets = this.repository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(tweets, Tweet.class);
    }

    public Mono<ServerResponse> one(ServerRequest request) {
        String id = String.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Tweet> tweetMono = this.repository.findById(id);
        return tweetMono
                .flatMap(tweet -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(tweet)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> test(ServerRequest request) {
        Tweet tweet = new Tweet();
        tweet.setUser("rkovtiuk");
        tweet.setCreatedAt(new Date());
        tweet.setText("test tweet");
        tweet.setComments(0L);
        tweet.setLikes(0L);
        return ServerResponse.ok().build(this.repository.save(tweet));
    }
}
