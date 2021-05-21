package bigproject.demo.model.service;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

public class CategoryServiceModel {
    private String name;
    private MultipartFile imgInput;

    public CategoryServiceModel() {
    }

    public String getName() {
        return name;
    }

    public CategoryServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getImgInput() {
        return imgInput;
    }

    public CategoryServiceModel setImgInput(MultipartFile imgInput) {
        this.imgInput = imgInput;
        return this;
    }
}
