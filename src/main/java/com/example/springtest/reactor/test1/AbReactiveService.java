package com.example.springtest.reactor.test1;

import reactor.core.publisher.Flux;

public interface AbReactiveService {
    Flux<Ab> retrieveAll();
}
