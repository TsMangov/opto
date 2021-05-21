package bigproject.demo.model.viewModels;

import bigproject.demo.model.entities.TopicEntity;
import bigproject.demo.model.entities.UserEntity;

import java.time.LocalDateTime;

public class CommentAndLogs {

    private String commentText;

    private LocalDateTime commentDateTime;

    private TopicEntity topic;

    private UserEntity userEntity;

    public CommentAndLogs() {
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentAndLogs setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public LocalDateTime getCommentDateTime() {
        return commentDateTime;
    }

    public CommentAndLogs setCommentDateTime(LocalDateTime commentDateTime) {
        this.commentDateTime = commentDateTime;
        return this;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public CommentAndLogs setTopic(TopicEntity topic) {
        this.topic = topic;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CommentAndLogs setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
