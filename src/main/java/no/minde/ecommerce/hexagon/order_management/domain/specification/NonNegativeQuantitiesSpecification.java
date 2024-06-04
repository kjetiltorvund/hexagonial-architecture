package no.minde.ecommerce.hexagon.order_management.domain.specification;

import no.minde.ecommerce.hexagon.common.domain.ISpecification;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;

public class NonNegativeQuantitiesSpecification implements ISpecification<OrderAggregate> {
    @Override
    public boolean isSatisfiedBy(OrderAggregate candidate) {
        return candidate.getOrderItems().stream().allMatch(orderItem -> orderItem.getOrderItemQuantity().value >= 0);
    }

    @Override
    public String getDescription() {
        return "Quantities of all order items should be non-negative.";
    }
}
