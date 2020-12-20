package it.freshfruits.domain.vo;

import it.freshfruits.util.ValidationUtils;

public final class AddressImpl implements Address {

    public static class Builder {
        private String street = "";
        private String city = "";
        private String state = "";

        public Builder(String street, String city, String state) {
            ValidationUtils.validateStreet(street);
            ValidationUtils.validateCity(city);
            ValidationUtils.validateState(state);
            this.street = street;
            this.city = city;
            this.state = state;
        }

        public AddressImpl build() {
            return new AddressImpl(this);
        }
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String toString() {
        return new StringBuilder().append("\nstreet:").append(street).append("\ncity:").append(city).append("\nstate:").append(state).toString();
    }

    private AddressImpl(Builder builder) {
        city = builder.city;
        state = builder.state;
        street = builder.street;
    }

    private String street = "";
    private String city = "";
    private String state = "";
}
