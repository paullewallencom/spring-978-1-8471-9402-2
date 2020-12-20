package it.freshfruits.domain.vo;

import it.freshfruits.domain.entity.FruitType;

import java.math.BigDecimal;

public class OrderItemMap implements OrderItem {

    public BigDecimal getAmountItem() {
        return fruit.getPrice().multiply(new BigDecimal(quantity.toString()));
    }

    public FruitType getFruitType() {
        return fruit;
    }

    public OrderItemMap() {
    }

    public OrderItemMap(OrderItem item) {
        this.fruit = item.getFruitType();
        this.idOrder = item.getIdOrder();
        this.quantity = item.getQuantity();
    }

    public FruitType getFruit() {
        return fruit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setFruit(FruitType fruit) {
        this.fruit = fruit;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    private FruitType fruit;
    private Integer quantity, idOrder;
}
