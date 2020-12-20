package it.freshfruits.domain.entity;

import java.math.BigDecimal;

public interface FruitType extends NamedEntity {

    public String getLocation();

    public String getColor();

    public String getFlavour();

    public BigDecimal getPrice();

    public void save();
}
