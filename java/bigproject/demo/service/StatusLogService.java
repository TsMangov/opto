package bigproject.demo.service;

import bigproject.demo.model.entities.StatusLog;
import bigproject.demo.model.entities.TopicEntity;
import bigproject.demo.model.entities.UserEntity;

import java.util.List;

public interface StatusLogService {


    void addToLog(TopicEntity entity, UserEntity user, String result);

    List<StatusLog> getAllStatusLogsByTopicId(Long id);

}
