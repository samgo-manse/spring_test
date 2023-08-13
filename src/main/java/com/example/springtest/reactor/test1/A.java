package com.example.springtest.reactor.test1;

import java.util.Objects;

class A {
    private final AId id;
    private final String name;
    private final BId bId;

    public A(final AId id,
            final String name,
            final BId bId) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.bId = Objects.requireNonNull(bId);
    }

    AId getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    BId getBId() {
        return this.bId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof A)) {
            return false;
        }
        A a = (A) o;
        return Objects.equals(id, a.id) && Objects.equals(name, a.name) && Objects.equals(bId, a.bId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bId);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", bId='" + getBId() + "'" +
                "}";
    }

}
