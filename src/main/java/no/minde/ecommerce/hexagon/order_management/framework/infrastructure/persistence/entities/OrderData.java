package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import no.minde.ecommerce.hexagon.common.valueobjects.Description;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.order_management.domain.status.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ord_tbl")
@Data
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;


    @Column(name = "order_uuid")
    public UUID orderUuid;

    @Embedded
    @AttributeOverrides(value = {   @AttributeOverride(name = "value", column = @Column(name = "description")) })
    public Description orderFriendlyName;


    @Enumerated(EnumType.STRING)
    public OrderStatus orderStatus;


    @Column(name = "customer_uuid")
    public UUID customerUuid;

    @Embedded
    @AttributeOverrides(value = {   @AttributeOverride(name = "value", column = @Column(name = "order_total_cost")) })
    public Money orderTotalCost;


    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public ZonedDateTime created;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    public Set<OrderItemData> orderItems = new HashSet<>();


    public void setOrderUuid(java.util.UUID orderUuid) {
        this.orderUuid = orderUuid;
    }
}
