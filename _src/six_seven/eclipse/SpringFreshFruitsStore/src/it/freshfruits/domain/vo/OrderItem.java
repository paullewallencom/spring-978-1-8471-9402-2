package it.freshfruits.domain.vo;

import it.freshfruits.domain.entity.FruitType;

import java.math.BigDecimal;

public interface OrderItem {

    public FruitType getFruitType();

    public Integer getQuantity();

    public Integer getIdOrder();

    public BigDecimal getAmountItem();
}
