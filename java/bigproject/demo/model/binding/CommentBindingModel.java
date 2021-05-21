package bigproject.demo.model.binding;

import bigproject.demo.model.entities.UserEntity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CommentBindingModel {

    @NotBlank
    @Size(min = 3, max = 300)
    private String commentText;
    private String username;
    private Long topicID;

    public CommentBindingModel() {
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentBindingModel setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CommentBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getTopicID() {
        return topicID;
    }

    public CommentBindingModel setTopicID(Long topicID) {
        this.topicID = topicID;
        return this;
    }
}
