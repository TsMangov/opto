package bigproject.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_titles")
public class JobTitleEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;

    public JobTitleEntity() {
    }

    public String getTitle() {
        return title;
    }

    public JobTitleEntity setTitle(String title) {
        this.title = title;
        return this;
    }
}
