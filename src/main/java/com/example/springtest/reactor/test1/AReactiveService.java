package com.example.springtest.reactor.test1;

import reactor.core.publisher.Flux;

interface AReactiveService {
    Flux<A> retrieveAll();
}
