package bigproject.demo.model.service;

import bigproject.demo.model.entities.enums.GenderEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductServiceModel {

    private String name;

    private BigDecimal price;

    private String breed;

    private MultipartFile imgInput;

    private String category;

    private GenderEnum gender;

    private LocalDate birthDate;


    public ProductServiceModel() {
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public ProductServiceModel setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public MultipartFile getImgInput() {
        return imgInput;
    }

    public ProductServiceModel setImgInput(MultipartFile imgInput) {
        this.imgInput = imgInput;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductServiceModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ProductServiceModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public ProductServiceModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
