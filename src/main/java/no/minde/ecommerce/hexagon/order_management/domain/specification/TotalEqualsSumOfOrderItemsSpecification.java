package no.minde.ecommerce.hexagon.order_management.domain.specification;

import no.minde.ecommerce.hexagon.common.domain.ISpecification;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;

import java.math.BigDecimal;

public class TotalEqualsSumOfOrderItemsSpecification implements ISpecification<OrderAggregate> {
    @Override
    public boolean isSatisfiedBy(OrderAggregate order) {
        BigDecimal calculatedSum = order.getOrderItems()
                .stream().map(x -> x.getOrderItemCost().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return order.getOrderTotalCost().equals(calculatedSum);
    }

    @Override
    public String getDescription() {
        return "Spec:Total Cost Equals Sum of Order Line Items";
    }
}
