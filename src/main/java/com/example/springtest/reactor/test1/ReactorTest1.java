package com.example.springtest.reactor.test1;

import java.time.Duration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * 비동기 처리를 보여주기 위한 샘플
 * A는 101개이고 B를 가져오는 2초 걸리므로 동기식이면 A개당 2초가 걸려야 하는데 비동기식이라서 그러지 않다.
 */
public class ReactorTest1 {
    private static final Logger logger = LoggerFactory.getLogger(ReactorTest1.class);

    public static void main(final String[] args) {
        AbReactiveService underTest = new TestAbReactiveService(new TestAReactiveService(), new TestBReactiveService());

        final var startTimeMillis = System.currentTimeMillis();
        final var result = underTest.retrieveAll()
                .collectList()
                .block();

        logger.info("result = {}", result);

        final var elapsedTimeMills = System.currentTimeMillis() - startTimeMillis;
        logger.info("elapsedTimeMills - {} ", elapsedTimeMills);
    }

    static class TestAbReactiveService implements AbReactiveService {
        private final AReactiveService aReactiveService;
        private final BReactiveService bReactiveService;

        TestAbReactiveService(
                final AReactiveService aReactiveService,
                final BReactiveService bReactiveService) {
            this.aReactiveService = Objects.requireNonNull(aReactiveService);
            this.bReactiveService = Objects.requireNonNull(bReactiveService);
        }

        @Override
        public Flux<Ab> retrieveAll() {
            return aReactiveService.retrieveAll()
                    .flatMapSequential(it -> Mono.just(it)
                            .zipWith(bReactiveService.retrieveById(it.getBId())))
                    .map(tuple -> TestAbReactiveService.toAb(tuple.getT1(), tuple.getT2()));
            // .map(function(TestAbReactiveService::toAb)); 이렇게 해도 됨
        }

        private static Ab toAb(final A a, final B b) {
            return new Ab(a.getId(), a.getName(), b.getId(), b.getName());
        }

    }

    /*
     * 0부터 99깢지 순서를 유지하며 발행, 첫번째 엘리먼트 2초 지연 후 발행
     */
    static class TestAReactiveService implements AReactiveService {

        @Override
        public Flux<A> retrieveAll() {
            return Flux.concat(
                    Mono.just(0).delayElement(Duration.ofSeconds(2L)),
                    Flux.range(1, 100))
                    .map(it -> new A(
                            new AId(it),
                            "AName-" + it,
                            new BId("B" + it)));
        }

    }

    /*
     * 매 요청마다 2초 지연 후 발행
     */
    static class TestBReactiveService implements BReactiveService {

        @Override
        public Mono<B> retrieveById(BId id) {
            return Mono.just(new B(id, "BName-" + id.value()))
                    .delayElement(Duration.ofSeconds(2L));
        }

    }

}