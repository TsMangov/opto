package bigproject.demo.model.binding;

import bigproject.demo.model.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class OrderBindingModel {
    @NotBlank
    private String phone;
    @NotBlank
    private String town;
    @NotBlank
    private String street;

    private String userName;

    public OrderBindingModel() {
    }

    public String getPhone() {
        return phone;
    }

    public OrderBindingModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTown() {
        return town;
    }

    public OrderBindingModel setTown(String town) {
        this.town = town;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public OrderBindingModel setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public OrderBindingModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
