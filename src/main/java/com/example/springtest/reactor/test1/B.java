package com.example.springtest.reactor.test1;

import java.util.Objects;

public class B {
    private final BId id;
    private final String name;

    B(final BId id,
            final String name) {
        this.id = id;
        this.name = name;
    }

    BId getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof B)) {
            return false;
        }
        B b = (B) o;
        return Objects.equals(id, b.id) && Objects.equals(name, b.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }

}
