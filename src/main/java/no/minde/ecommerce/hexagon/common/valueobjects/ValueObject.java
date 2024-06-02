package no.minde.ecommerce.hexagon.common.valueobjects;

public abstract class ValueObject {
    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}
