package it.freshfruits.domain.entity;

import it.freshfruits.application.repository.FruitTypeRepository;
import it.freshfruits.util.ValidationUtils;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(dependencyCheck = true)
public class FruitTypeImpl implements FruitType, Serializable {

    @Autowired
    public void setFruitTypeRepository(FruitTypeRepository fruitTypeRepository) {
        this.fruitTypeRepository = fruitTypeRepository;
    }

    public void save() {
        if (id == 0) {
            fruitTypeRepository.insert(this);
        } else {
            fruitTypeRepository.update(this);
        }
    }

    public String getColor() {
        return color;
    }

    public String getFlavour() {
        return flavour;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public String toString() {
        return new StringBuilder().append("\nid:").append(id).append("\nname:").append(name).append("\ncolor:").append(color).append("\nflavour:").append(flavour).append("\nlocation:").append(
                location).toString();
    }

    public static class Builder {
        // Required parameters
        private String name = "";
        private Integer id = 0;
        private BigDecimal price;

        // optional parameters
        private String color = "";
        private String flavour = "";
        private String location = "";
        private FruitTypeRepository fruitTypeRepository;

        private void validatePrice(BigDecimal price) {

            if (price == null || (price.compareTo(BigDecimal.ZERO) <= 0))

                throw new IllegalArgumentException("price argument < 0 :" + price);
        }

        private void validateName(String name) {
            if (name == null || name.length() != 0 && name.length() < 3)
                throw new IllegalArgumentException("name argument < 3 :" + name);
        }

        private void validateColor(String color) {
            if (color == null || color.length() != 0 && color.length() < 3)
                throw new IllegalArgumentException("color argument < 3 :" + color);
        }

        private void validateFlavour(String flavour) {
            if (flavour == null || flavour.length() != 0 && flavour.length() < 4)
                throw new IllegalArgumentException("flavour argument < 4 :" + flavour);
        }

        private void validateLocation(String location) {
            if (location == null || location.length() != 0 && location.length() < 3)
                throw new IllegalArgumentException("location argument < 3 :" + location);
        }

        public Builder(String name, Integer id, BigDecimal price) {
            ValidationUtils.validateId(id.toString());
            validateName(name);
            validatePrice(price);
            this.name = name;
            this.id = id;
            this.price = price;
        }

        public Builder color(String val) {
            validateColor(val);
            color = val;
            return this;
        }

        public Builder flavour(String val) {
            validateFlavour(val);
            flavour = val;
            return this;
        }

        public Builder location(String val) {
            validateLocation(val);
            location = val;
            return this;
        }

        public Builder fruitTypeRepository(FruitTypeRepository fruitTypeRepository) {
            this.fruitTypeRepository = fruitTypeRepository;
            return this;
        }

        public FruitTypeImpl build() {
            return new FruitTypeImpl(this);
        }
    }

    private FruitTypeImpl(Builder builder) {
        id = builder.id;
        name = builder.name;
        price = builder.price;
        color = builder.color;
        flavour = builder.flavour;
        location = builder.location;
        fruitTypeRepository = builder.fruitTypeRepository;
    }

    private Integer id;
    private BigDecimal price;
    private String color, flavour, location, name;
    private static final long serialVersionUID = 4133562402526792290L;
    private FruitTypeRepository fruitTypeRepository;
}
