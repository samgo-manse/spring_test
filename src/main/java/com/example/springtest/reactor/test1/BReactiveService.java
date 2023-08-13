package com.example.springtest.reactor.test1;

import reactor.core.publisher.Mono;

public interface BReactiveService {
    Mono<B> retrieveById(BId id);
}
