package bigproject.demo.model.service;

public class TopicServiceModel {

    private String topicName;
    private String topicDescription;
    private String username;
    private Long client;

    public TopicServiceModel() {
    }

    public String getTopicName() {
        return topicName;
    }

    public TopicServiceModel setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public TopicServiceModel setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public TopicServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getClient() {
        return client;
    }

    public TopicServiceModel setClient(Long client) {
        this.client = client;
        return this;
    }
}
