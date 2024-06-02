package no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects;

import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.common.valueobjects.Quantity;

public record ProductRequest(ID productId, Money price, Quantity quantity) {
}
