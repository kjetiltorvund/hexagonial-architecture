package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.order_management.domain.status.OrderStatus;

import java.util.List;

public record OrderAggregateRecord(ID id,
                                   CustomerId customerId,
                                   List<OrderItem> orderItems,
                                   OrderStatus orderStatus,
                                   Money orderTotalCost) {
}
