package no.minde.ecommerce.hexagon.order_management.framework.adapters_outbound.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.order_management.application.outbund.persistence.PersistenceOutboundPort;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;
import no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.repositories.OrderRepository;

import javax.inject.Inject;

@ApplicationScoped
public class PersistenceDatabaseAdapter implements PersistenceOutboundPort<OrderAggregate, ID> {

    private final OrderRepository orderRepository;

    @Inject
    public PersistenceDatabaseAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void persist(OrderAggregate entity) {
        orderRepository.persistAggregate(entity);
    }

    @Override
    public OrderAggregate getById(ID orderId) throws NotFoundException {
        return orderRepository.readAggregate(orderId);
    }

    @Override
    public void delete(ID orderId) {
        orderRepository.delegateAggregate(orderId);
    }
}
