package it.freshfruits.domain.entity;

import java.math.BigDecimal;

public interface FruitView {

    public Integer getId();

    public BigDecimal getPrice();

    public String getColor();

    public String getFlavour();

    public String getLocation();

    public String getName();

}