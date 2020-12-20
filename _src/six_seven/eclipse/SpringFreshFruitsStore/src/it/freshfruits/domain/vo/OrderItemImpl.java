package it.freshfruits.domain.vo;

import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.util.ValidationUtils;

import java.math.BigDecimal;

public class OrderItemImpl implements OrderItem {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderItemImpl) {
            OrderItemImpl item = (OrderItemImpl) obj;
            return item.getFruitType().getId() == this.fruit.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.idOrder + new Integer(fruit.getId());
    }

    public static class Builder {
        private FruitType fruit;
        private Integer quantity, idOrder;

        public Builder(FruitType fruit, Integer quantity, String idOrder) {
            ValidationUtils.validateFruitType(fruit);
            ValidationUtils.validateQuantity(quantity);
            this.idOrder = Integer.parseInt(idOrder);
            this.fruit = fruit;
            this.quantity = quantity;
        }

        public OrderItemImpl build() {
            return new OrderItemImpl(this);
        }
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public BigDecimal getAmountItem() {
        return fruit.getPrice().multiply(new BigDecimal(quantity));
    }

    public FruitType getFruitType() {
        return fruit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String toString() {
        return new StringBuilder().append("\nquantity:").append(quantity).append("\n<-fruit->:").append(fruit).toString();
    }

    private OrderItemImpl(Builder builder) {
        fruit = builder.fruit;
        quantity = builder.quantity;
        idOrder = builder.idOrder;
    }

    private FruitType fruit;
    private Integer quantity, idOrder;
}
