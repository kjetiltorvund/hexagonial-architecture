package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.common.valueobjects.Quantity;

import java.util.UUID;

@Entity
@Table(name = "order_item_tbl")
@Data
public class OrderItemData  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;



    @Column(name = "product_uuid")
    public UUID productUuid;

    @Embedded
    @AttributeOverrides(value = {   @AttributeOverride(name = "value", column = @Column(name = "product_price")) })
    public Money productPrice;

    @Embedded
    @AttributeOverrides(value = {      @AttributeOverride(name = "value", column = @Column(name = "item_total_quantity")) })
    public Quantity orderItemQuantity;

    @Embedded
    @AttributeOverrides(value = {   @AttributeOverride(name = "value", column = @Column(name = "item_total_cost")) })
    public Money orderItemCost;
}
