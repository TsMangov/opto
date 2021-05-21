package bigproject.demo.model.binding;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TopicBindingModel {

  @NotBlank
  @Size(min = 3, max = 60)
  private String topicName;
  @NotBlank
  @Size(min = 3, max = 300)
  private String topicDescription;

  private String username;
  @NotNull
  private Long client;

  public TopicBindingModel() {
  }

  public String getTopicName() {
    return topicName;
  }

  public TopicBindingModel setTopicName(String topicName) {
    this.topicName = topicName;
    return this;
  }

  public String getTopicDescription() {
    return topicDescription;
  }

  public TopicBindingModel setTopicDescription(String topicDescription) {
    this.topicDescription = topicDescription;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public TopicBindingModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public Long getClient() {
    return client;
  }

  public TopicBindingModel setClient(Long client) {
    this.client = client;
    return this;
  }
}
