package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import ch.qos.logback.classic.model.LevelModel;
import lombok.Getter;
import no.minde.ecommerce.hexagon.common.domain.DomainEntity;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.common.valueobjects.Quantity;
import no.minde.ecommerce.hexagon.order_management.domain.status.OrderItemStatus;

import java.math.BigDecimal;

@Getter
public class OrderItem extends DomainEntity {
    private ProductId product;
    private Quantity orderItemQuantity;
    private Money orderItemCost;
    private OrderItemStatus orderItemStatus;

    public OrderItem(ProductId product, Quantity quantity) {
        super(ID.generateRandom());
        this.product = product;
        this.orderItemQuantity = quantity;
        orderItemCost = new Money(BigDecimal.ZERO);
        calculateOrderItemCost();
    }

    public void setQuantity(Quantity quantity) {
        this.orderItemQuantity = quantity;
        calculateOrderItemCost();
    }

    private void calculateOrderItemCost() {
        this.orderItemCost.value = product.getPrice().value.multiply(BigDecimal.valueOf(this.orderItemQuantity.value));
    }

    public Money getOrderItemCost() {
        return orderItemCost;
    }

    public Quantity getOrderItemQuantity() {
        return orderItemQuantity;
    }
}
