package bigproject.demo.service.impl;

import bigproject.demo.model.entities.CategoryEntity;
import bigproject.demo.model.service.CategoryServiceModel;
import bigproject.demo.repository.CategoryRepository;
import bigproject.demo.service.CategoryService;
import bigproject.demo.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CloudinaryService cloudinaryService;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CloudinaryService cloudinaryService) {
        this.categoryRepository = categoryRepository;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public void initCategory() {
        if(categoryRepository.count() == 0) {
            CategoryEntity categoryEntity = new CategoryEntity().setName("Cats").setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616611490/eld7sjelihewczmsy842.png");
            CategoryEntity categoryEntity1 = new CategoryEntity().setName("Dogs").setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616611503/hp8xy9bmv7h70gi4na2w.png");
            CategoryEntity categoryEntity2 = new CategoryEntity().setName("Birds").setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616611584/eizmmvkulyc9yi2nsmxi.png");
            CategoryEntity categoryEntity3 = new CategoryEntity().setName("Fish").setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616611599/hnsypzmth5w18ip8szm6.png");
            CategoryEntity categoryEntity4 = new CategoryEntity().setName("Animalias").setImgUrl("https://res.cloudinary.com/dtijhevtf/image/upload/v1616611618/uemn5lgy9krsufkarcb2.png");
            this.categoryRepository.save(categoryEntity);
            this.categoryRepository.save(categoryEntity1);
            this.categoryRepository.save(categoryEntity2);
            this.categoryRepository.save(categoryEntity3);
            this.categoryRepository.save(categoryEntity4);

        }
    }

    @Override
    public CategoryEntity findByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    //todo must return category view not entity
    @Override
    public List<CategoryEntity> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void saveCategory(CategoryServiceModel model) throws IOException {
        MultipartFile img = model.getImgInput();
        String imageUrl = cloudinaryService.uploadImage(img);

        CategoryEntity entity = new CategoryEntity().setName(model.getName()).setImgUrl(imageUrl);
        this.categoryRepository.saveAndFlush(entity);
    }


}
