package no.minde.ecommerce.hexagon.common.domain;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T candidate);
    String getDescription();
}
