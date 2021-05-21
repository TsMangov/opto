package bigproject.demo.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntity{
    @OneToOne
    private ProductEntity product;
    @OneToOne
    private TopicEntity topicEntity;
    @Column
    private Integer count;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private OrderEntity orderEntity;

    public CartItemEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public CartItemEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public CartItemEntity setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public CartItemEntity setCount(Integer count) {
        this.count = count;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CartItemEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public CartItemEntity setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
        return this;
    }
}
