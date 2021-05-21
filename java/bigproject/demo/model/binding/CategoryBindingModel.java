package bigproject.demo.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryBindingModel {

    @NotBlank
    @Size(min = 3, max = 60)
    private String name;
    private MultipartFile imgInput;

    public CategoryBindingModel() {
    }

    public String getName() {
        return name;
    }

    public CategoryBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getImgInput() {
        return imgInput;
    }

    public CategoryBindingModel setImgInput(MultipartFile imgInput) {
        this.imgInput = imgInput;
        return this;
    }
}
