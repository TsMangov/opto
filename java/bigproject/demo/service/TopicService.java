package bigproject.demo.service;


import bigproject.demo.model.entities.TopicEntity;
import bigproject.demo.model.service.TopicServiceModel;
import bigproject.demo.model.viewModels.TopicViewModel;

import java.util.List;

public interface TopicService {
    void seedTopics();
//    void addCommentToTopic(Long idTopic ,Long idComment);
  //  void printAll();

    void saveTopic(TopicServiceModel topicServiceModel);

    List<TopicViewModel> getAllTopics();

    //todo make it to view
    TopicViewModel findById(Long id);

    TopicEntity findTopicById(Long topicID);

    String changeStatusFromStatusToNext(Long id);

    String changeStatusFromStartToZakupen(Long id, String username);

    String changeStatusFromStartToIzrabotvan(Long topicId, String username);

    String changeStatusFromStartToProveren(Long topicId, String username);

    String getTopicTextByTopicId(Long id);
}
