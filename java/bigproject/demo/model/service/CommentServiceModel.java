package bigproject.demo.model.service;

public class CommentServiceModel {
    private String commentText;
    private String username;
    private Long topicID;

    public CommentServiceModel() {
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentServiceModel setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CommentServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getTopicID() {
        return topicID;
    }

    public CommentServiceModel setTopicID(Long topicID) {
        this.topicID = topicID;
        return this;
    }
}
