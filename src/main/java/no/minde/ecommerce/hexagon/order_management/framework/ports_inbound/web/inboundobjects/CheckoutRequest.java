package no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects;

import no.minde.ecommerce.hexagon.common.valueobjects.*;

public record CheckoutRequest(ID customerId, Contact contact, NameSurname shippingNameSurname,
                              Payment payment, Address shippingAddress) {
}
