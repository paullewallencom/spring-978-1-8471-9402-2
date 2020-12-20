package it.freshfruits.domain.vo;

public class QuantityAndItemVO {

    public QuantityAndItemVO(OrderItem item) {
        this.item = item;
        this.quantity = item.getQuantity();
    }

    public Boolean minus(Integer minusQuantiy) {
        Boolean result = false;
        if (quantity > minusQuantiy) {
            quantity = quantity - minusQuantiy;
            result = true;
        }
        return result;
    }

    public void add(Integer addQuantiy) {
        quantity = quantity + addQuantiy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderItem getItem() {
        return item;
    }

    private Integer quantity;
    private OrderItem item;
}
