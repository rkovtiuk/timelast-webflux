package com.rkovtiuk.timelast.handler;

import com.rkovtiuk.timelast.common.entity.Tweet;
import com.rkovtiuk.timelast.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        Mono<Tweet> time = request.bodyToMono(Tweet.class);
        return ServerResponse.ok().build(this.repository.save(time));
    }

    public Mono<ServerResponse> all(ServerRequest request) {
        Flux<Tweet> models = this.repository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(models, Tweet.class);
    }

    public Mono<ServerResponse> one(ServerRequest request) {
        String id = String.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Tweet> timeMono = this.repository.findById(id);
        return timeMono
                .flatMap(time -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(time)))
                .switchIfEmpty(notFound);
    }
}
