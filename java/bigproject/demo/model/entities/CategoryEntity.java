package bigproject.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String imgUrl;

    public CategoryEntity() {
    }

    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public CategoryEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
