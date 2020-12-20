package it.freshfruits.domain.vo;

import it.freshfruits.domain.entity.FruitType;

import java.math.BigDecimal;

public class FruitMap implements FruitType {

    public void save() {
    }

    public FruitMap() {
    }

    public FruitMap(FruitType fruit) {
        this.color = fruit.getColor();
        this.flavour = fruit.getFlavour();
        this.id = new Integer(fruit.getId());
        this.location = fruit.getLocation();
        this.name = fruit.getName();
        this.price = fruit.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer id;
    private BigDecimal price;
    private String color, flavour, location, name;
}
