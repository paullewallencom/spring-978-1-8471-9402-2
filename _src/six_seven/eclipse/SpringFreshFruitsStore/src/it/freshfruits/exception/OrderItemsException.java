package it.freshfruits.exception;

public class OrderItemsException extends RuntimeException {

    public OrderItemsException(String message) {
        super(message);
    }

    public OrderItemsException(String message, Throwable cause) {
        super(message, cause);
    }

}
