package bigproject.demo.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
    @Column
    private String phone;
    @Column
    private String town;
    @Column
    private String street;
    @ManyToOne
    private UserEntity userEntity;
    @Column
    private Boolean finished;
    @Column
    private BigDecimal totalPrice;

    public OrderEntity() {
        this.finished = false;
    }

    public String getPhone() {
        return phone;
    }

    public OrderEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTown() {
        return town;
    }

    public OrderEntity setTown(String town) {
        this.town = town;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public OrderEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public OrderEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public Boolean getFinished() {
        return finished;
    }

    public OrderEntity setFinished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
