package no.minde.ecommerce.hexagon.order_management.domain.status;

public enum OrderItemStatus {
    PENDING(1),
    ERROR_RESERVE_STOCK_FAILED(2),
    ERROR_DELIVERY_FAILED(3),
    COMPLETED(4),
    ITEM_RETURNED(5);



    OrderItemStatus(int code){
        this.code = code;
    }

    private final int code;


    public int getCode() {
        return code;
    }


    public static OrderItemStatus fromCode(int code) throws IllegalArgumentException {
        for (OrderItemStatus curstate : OrderItemStatus.values()) {
            if (curstate.code==code) {
                return curstate;
            }
        }
        throw new IllegalArgumentException("Invalid OrderItemStatus value: " + code);
    }
}
