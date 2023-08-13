package com.example.springtest.reactor.test1;

import java.util.Objects;

record Ab(
                AId id,
                String aName,
                BId bId,
                String bName) {
        Ab {
                Objects.requireNonNull(id);
                Objects.requireNonNull(aName);
                Objects.requireNonNull(bId);
                Objects.requireNonNull(bName);
        }
}
