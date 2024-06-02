package no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects;

import no.minde.ecommerce.hexagon.common.valueobjects.ID;

import java.util.List;

public record OrderRequest(ID customerId, List<ProductRequest> products) {
}
