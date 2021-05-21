package bigproject.demo.model.viewModels;

import bigproject.demo.model.entities.enums.JobStatus;

public class TopicViewModel {

    private Long id;
    private String topicName;
    private String topicDescription;
    private String dateTime;
    private String fullNameOfUser;
    private JobStatus status;
    private String client;

    public TopicViewModel() {
    }

    public Long getId() {
        return id;
    }

    public TopicViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public TopicViewModel setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public TopicViewModel setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public TopicViewModel setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getFullNameOfUser() {
        return fullNameOfUser;
    }

    public TopicViewModel setFullNameOfUser(String fullNameOfUser) {
        this.fullNameOfUser = fullNameOfUser;
        return this;
    }

    public JobStatus getStatus() {
        return status;
    }

    public TopicViewModel setStatus(JobStatus status) {
        this.status = status;
        return this;
    }

    public String getClient() {
        return client;
    }

    public TopicViewModel setClient(String client) {
        this.client = client;
        return this;
    }
}
