package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import no.minde.ecommerce.hexagon.common.valueobjects.ID;

public class OrderAggregate implements IAggregateRoot {
    private ID orderId;

    public ID getId() {
        return orderId;
    }
}
