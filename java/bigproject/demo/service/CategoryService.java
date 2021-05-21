package bigproject.demo.service;

import bigproject.demo.model.entities.CategoryEntity;
import bigproject.demo.model.service.CategoryServiceModel;
import bigproject.demo.model.service.CommentServiceModel;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void initCategory();

    CategoryEntity findByName(String name);

    List<CategoryEntity> getAll();


    void saveCategory(CategoryServiceModel model) throws IOException;
}
