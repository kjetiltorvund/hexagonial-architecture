package no.minde.ecommerce.hexagon.order_management.application.ports_inbound;

import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.common.valueobjects.Quantity;
import no.minde.ecommerce.hexagon.order_management.application.outbund.persistence.PersistenceOutboundPort;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.CustomerId;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregateRecord;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.ProductRequest;

import javax.inject.Inject;
import java.util.List;

public class OrderManagementService implements OrderManagementInboundPort {

    private final PersistenceOutboundPort<OrderAggregate,ID> persistenceOutboundAdapter;

    @Inject
    public OrderManagementService(PersistenceOutboundPort<OrderAggregate, ID> persistenceOutboundAdapter) {
        this.persistenceOutboundAdapter = persistenceOutboundAdapter;
    }

    @Override
    public ID createOrder(ID customerId, List<ProductRequest> productList) throws NotFoundException, BusinessException {
        OrderAggregate order = OrderAggregate.builder(new CustomerId(customerId));
        ID newOrderId = order.getOrderId();

        for(ProductRequest product : productList) {
            order.addProduct(product.productId(), product.price(), product.quantity());
        }

        persistenceOutboundAdapter.persist(order);

        order = persistenceOutboundAdapter.getById(newOrderId);

        return order.getId();
    }

    @Override
    public OrderAggregateRecord addProductToOrder(ID orderId, ID productId, Money price, Quantity quantity) throws NotFoundException, BusinessException {
        OrderAggregate order = persistenceOutboundAdapter.getById(orderId);
        order.addProduct(productId, price, quantity);

        persistenceOutboundAdapter.persist(order);

        order = persistenceOutboundAdapter.getById(order.getId());

        return order.getImmutable();
    }

    @Override
    public OrderAggregateRecord removeProductFromOrder(ID orderId, ID productId) throws NotFoundException, BusinessException {
        OrderAggregate order = persistenceOutboundAdapter.getById(orderId);
        order.removeProduct(productId);

        persistenceOutboundAdapter.persist(order);

        order = persistenceOutboundAdapter.getById(order.getId());
        return order.getImmutable();
    }

    @Override
    public OrderAggregateRecord clearOrder(ID orderId) throws NotFoundException, BusinessException {
        OrderAggregate order = persistenceOutboundAdapter.getById(orderId);
        order.clearOrder();

        persistenceOutboundAdapter.persist(order);

        order = persistenceOutboundAdapter.getById(order.getId());
        return order.getImmutable();
    }

    @Override
    public OrderAggregateRecord getOrder(ID orderId) throws NotFoundException, BusinessException {
        OrderAggregate order = persistenceOutboundAdapter.getById(orderId);
        return order.getImmutable();
    }
}
