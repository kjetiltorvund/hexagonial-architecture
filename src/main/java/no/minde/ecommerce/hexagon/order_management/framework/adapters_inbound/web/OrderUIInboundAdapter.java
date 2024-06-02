package no.minde.ecommerce.hexagon.order_management.framework.adapters_inbound.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;
import no.minde.ecommerce.hexagon.common.exceptions.CheckoutException;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.CheckoutRequest;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.OrderRequest;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.ProductRequest;

import java.net.URI;

// This adapter serves the port interface and acts as an old school controller.
// Orders come from the users, presentation layer and is passed to the core business logic
// Inbound (Driver) adapter

@Path("order-service/hexagon/v1/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderUIInboundAdapter implements OrderWebUIInboundPort {
    @Override
    @Path("/{orderId}")
    public Response getOrder(String orderId) throws NotFoundException, BusinessException {
        OrderAggregate order = null; //Call Application Layer to Get  the Order
        return Response.ok(order).build();
    }

    @POST
    @Override
    public Response createOrder(OrderRequest orderRequest) throws BusinessException, NotFoundException {
        ID newOrderId = null; //Call Application Layer to Create  the Order and return the ID

        return Response.created(URI.create("/" + newOrderId.getUuid().toString())).build();
    }

    @Override
    public Response addProductToOrder(String orderId, ProductRequest productRequest) throws NotFoundException, BusinessException {
        return null;
    }

    @PUT
    @Path("/{orderId}/addProduct")
    @Override
    public Response removeProductFromOrder(String orderId, ProductRequest productRequest) throws NotFoundException, BusinessException {
        OrderAggregate order = null; //Call Application Layer to Remove a Product to  the Order

        return Response.ok(URI.create("/checkout-success/" + order.getId().getUuid().toString())).build();
    }

    @Override
    public Response checkout(String orderId, CheckoutRequest checkoutRequest) throws NotFoundException, BusinessException, CheckoutException {
        return null;
    }

    @Override
    public Response clearOrder(String orderId) throws NotFoundException, BusinessException {
        return null;
    }
}
