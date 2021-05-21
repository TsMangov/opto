package bigproject.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column(columnDefinition = "TEXT")
    private String commentText;
    @Column
    private LocalDateTime commentDateTime;
    @ManyToOne
    private TopicEntity topic;
    @ManyToOne
    private UserEntity userEntity;

    public CommentEntity() {
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentEntity setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public LocalDateTime getCommentDateTime() {
        return commentDateTime;
    }

    public CommentEntity setCommentDateTime(LocalDateTime commentDateTime) {
        this.commentDateTime = commentDateTime;
        return this;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public CommentEntity setTopic(TopicEntity topic) {
        this.topic = topic;
        return this;
    }
}
