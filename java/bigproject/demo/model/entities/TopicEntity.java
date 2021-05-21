package bigproject.demo.model.entities;

import bigproject.demo.model.entities.enums.JobStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topic")
public class TopicEntity extends BaseEntity{
    @Column
    private String topicName;
    @Column(columnDefinition = "TEXT")
    private String topicDescription;
    @Column
    private LocalDateTime dateTime;
    @Enumerated
    private JobStatus status;
    @ManyToOne( cascade = CascadeType.MERGE)
    private UserEntity userEntity;
    @ManyToOne
    private Supplier supplier;

    public TopicEntity() {

    }

    public String getTopicName() {
        return topicName;
    }

    public TopicEntity setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public TopicEntity setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TopicEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public JobStatus getStatus() {
        return status;
    }

    public TopicEntity setStatus(JobStatus status) {
        this.status = status;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public TopicEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public TopicEntity setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }
}
