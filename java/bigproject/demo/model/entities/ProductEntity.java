package bigproject.demo.model.entities;

import bigproject.demo.model.entities.enums.GenderEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    //todo add active boolean to replace delete

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @ManyToOne(optional = false)
    private CategoryEntity category;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public ProductEntity() {
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public ProductEntity setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ProductEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ProductEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public ProductEntity setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
