package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;

import java.util.UUID;

@Entity
@Table(name = "product_tbl")
@Data
public class ProductData  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long productId;


    @Column(name = "product_uuid")
    public UUID productUuid;

    @Embedded
    @AttributeOverrides(value = {   @AttributeOverride(name = "value", column = @Column(name = "price")) })
    public Money price;
}
