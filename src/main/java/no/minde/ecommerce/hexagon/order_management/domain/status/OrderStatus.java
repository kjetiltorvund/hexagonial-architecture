package no.minde.ecommerce.hexagon.order_management.domain.status;

public enum OrderStatus {
    PENDING(1),
    AWAITING_PAYMENT(2),
    PAID(3),
    AWAITING_WAREHOUSE_PACKAGING(4),
    PACKAGED(5),
    AWAITING_DELIVERY(6),
    DELIVERED(7),
    COMPLETED(8),
    ERROR_PAYMENT_FAILED(9),
    ERROR_RESERVE_STOCK_FAILED(10),
    ERROR_DELIVERY_FAILED(11),
    ERROR_WAREHOUSE_PROCESSING(12);

    OrderStatus(int code){
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }

    public static OrderStatus fromCode(int code) throws IllegalArgumentException {
        for (OrderStatus curstate : OrderStatus.values()) {
            if (curstate.code==code) {
                return curstate;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus value: " + code);
    }


}
