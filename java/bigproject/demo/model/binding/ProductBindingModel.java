package bigproject.demo.model.binding;

import bigproject.demo.model.entities.CategoryEntity;
import bigproject.demo.model.entities.enums.GenderEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductBindingModel {
    @NotBlank
    @Size(min = 3, max = 60, message = "Pet name length must be more than 3 and less than 60 characters")
    private String name;
    @NotNull(message = "You must enter pet price")
    @DecimalMin(value = "0.0", message = "Price can not be below 0")
    private BigDecimal price;
    @NotBlank
    @Size(min = 3, max = 60, message = "Breed length must be more than 3 and less than 60 characters")
    private String breed;
    @NotNull(message = "You must choose one of the pet types")
    private String category;
    @NotNull(message = "You must choose a picture of the pet")
    private MultipartFile imgInput;
    @NotNull(message = "You must select PET gender")
    private GenderEnum gender;
    @PastOrPresent(message = "Date cant be in the future")
    @NotNull(message = "You must enter valid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public ProductBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public MultipartFile getImgInput() {
        return imgInput;
    }

    public ProductBindingModel setImgInput(MultipartFile imgInput) {
        this.imgInput = imgInput;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public ProductBindingModel setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ProductBindingModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public ProductBindingModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
