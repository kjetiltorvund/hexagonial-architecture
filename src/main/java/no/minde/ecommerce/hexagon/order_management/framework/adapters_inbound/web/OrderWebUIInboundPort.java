package no.minde.ecommerce.hexagon.order_management.framework.adapters_inbound.web;

import jakarta.ws.rs.core.Response;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;

import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.OrderRequest;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.ProductRequest;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.CheckoutRequest;

import no.minde.ecommerce.hexagon.common.exceptions.CheckoutException;

// This is the inbound port which define the interface for user-side operations.
// Its goal is to separate the presentation layer from the core business logic.
public interface OrderWebUIInboundPort {
    Response getOrder(String orderId) throws NotFoundException, BusinessException;
    Response createOrder(OrderRequest orderRequest) throws BusinessException, NotFoundException;
    Response addProductToOrder(String orderId, ProductRequest productRequest) throws NotFoundException, BusinessException;
    Response removeProductFromOrder(String orderId, ProductRequest productRequest) throws NotFoundException, BusinessException;
    Response checkout(String orderId, CheckoutRequest checkoutRequest) throws NotFoundException, BusinessException, CheckoutException;
    Response clearOrder(String orderId) throws NotFoundException, BusinessException;

}
