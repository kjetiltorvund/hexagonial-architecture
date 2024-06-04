package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.repositories;

import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;

public interface IAggregateRepository<T, T1> {
    OrderAggregate readAggregate(ID id) throws NotFoundException;

    ID persistAggregate(OrderAggregate aggregateRoot);

    void delegateAggregate(ID id);
}
