package no.minde.ecommerce.hexagon.order_management.application.outbund.persistence;

import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;

public interface PersistenceOutboundPort<T, K> {
    void persist(T entity);
    T getById(K key) throws NotFoundException;
    void delete(K key);
}
