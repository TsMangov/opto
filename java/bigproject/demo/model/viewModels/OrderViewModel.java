package bigproject.demo.model.viewModels;

import bigproject.demo.model.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class OrderViewModel {
    private Long id;
    private String phone;
    private String town;
    private String street;
    private String username;
    private String fullName;
    private Boolean finished;
    private BigDecimal totalPrice;

    public OrderViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public OrderViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTown() {
        return town;
    }

    public OrderViewModel setTown(String town) {
        this.town = town;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public OrderViewModel setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OrderViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Boolean getFinished() {
        return finished;
    }

    public OrderViewModel setFinished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderViewModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public OrderViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
