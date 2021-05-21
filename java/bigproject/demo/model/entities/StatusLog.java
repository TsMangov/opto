package bigproject.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Status_logs")
public class StatusLog extends BaseEntity {

    @Column
    private String statusChangeText;
    @Column
    private LocalDateTime statusChangeDateAndTime;
    @ManyToOne
    private TopicEntity topic;
    @ManyToOne
    private UserEntity userEntity;

    public StatusLog() {
    }

    public String getStatusChangeText() {
        return statusChangeText;
    }

    public StatusLog setStatusChangeText(String statusChangeText) {
        this.statusChangeText = statusChangeText;
        return this;
    }

    public LocalDateTime getStatusChangeDateAndTime() {
        return statusChangeDateAndTime;
    }

    public StatusLog setStatusChangeDateAndTime(LocalDateTime statusChangeDateAndTime) {
        this.statusChangeDateAndTime = statusChangeDateAndTime;
        return this;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public StatusLog setTopic(TopicEntity topic) {
        this.topic = topic;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public StatusLog setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
