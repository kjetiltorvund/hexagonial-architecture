package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;
import no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.entities.OrderData;
import no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.mappers.OrderDataMapper;

import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderData,Long>, IAggregateRepository<ID, OrderAggregate>  {

    OrderDataMapper entityMapper;

    @Inject
    public OrderRepository(OrderDataMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    @Override
    public OrderAggregate readAggregate(ID id) throws NotFoundException {
        return mapOrderDataToAggregate(findOrderByUUID(id.getUuid()));
    }

    private OrderData findOrderByUUID(UUID orderUuid) throws NotFoundException {
        return find("orderUuid", orderUuid)
                .firstResultOptional()
                .orElseThrow(() -> new NotFoundException("No order found for UUID: " + orderUuid));
    }

    private OrderData findOrderById(Long orderId) throws NotFoundException {
        return findByIdOptional(orderId).orElseThrow(() -> new NotFoundException("No order found for Local DB ID: " + orderId));
    }

    private OrderAggregate mapOrderDataToAggregate(OrderData orderData) {
        return entityMapper.unmarshalToDomain(orderData);
    }

    @Override
    public ID persistAggregate(OrderAggregate aggregateRoot) {
        persist(entityMapper.marshalToPersistence(aggregateRoot));
        return null;
    }

    @Override
    public void delegateAggregate(ID id) {
        deleteById(id.getId());
    }
}
