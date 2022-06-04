package com.powerorg.powerplant.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
public class Battery {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be empty")
    private String name;
    @NotNull(message = "postcode must not be null")
    @NotBlank(message = "postcode must be four digits")
    @Digits(integer = 4, fraction = 0)
    private String postcode;
    @NotNull(message = "capacity must not be null")
    @Range(min = 1, message = "capacity must be a positive integer")
    private Integer capacity;

    Battery() {

    }

    Battery(String name, String postcode, Integer capacity) {
        this.name = name;
        this.postcode = postcode;
        this.capacity = capacity;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Battery))
            return false;
        Battery battery = (Battery) o;
        return Objects.equals(this.id, battery.id) && Objects.equals(this.name, battery.name) && Objects.equals(this.postcode, battery.postcode)
                && Objects.equals(this.capacity, battery.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.postcode, this.capacity);
    }

    @Override
    public String toString() {
        return "Battery{" + "id=" + this.id + ", name='" + this.name + '\'' + ", postCode='" + this.postcode + '\'' + ", wattCapacity='" + this.capacity + '\'' + '}';
    }

}
