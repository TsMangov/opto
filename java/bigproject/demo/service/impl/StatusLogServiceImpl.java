package bigproject.demo.service.impl;

import bigproject.demo.model.entities.StatusLog;
import bigproject.demo.model.entities.TopicEntity;
import bigproject.demo.model.entities.UserEntity;
import bigproject.demo.repository.StatusLogRepository;
import bigproject.demo.service.StatusLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusLogServiceImpl implements StatusLogService {
    private final StatusLogRepository statusLogRepository;


    public StatusLogServiceImpl(StatusLogRepository statusLogRepository) {
        this.statusLogRepository = statusLogRepository;
    }


    @Override
    public void addToLog(TopicEntity entity, UserEntity user, String result) {
        StatusLog statusLog = new StatusLog()
                .setTopic(entity)
                .setUserEntity(user)
                .setStatusChangeDateAndTime(LocalDateTime.now())
                .setStatusChangeText(result);
        statusLogRepository.saveAndFlush(statusLog);
    }

    @Override
    public List<StatusLog> getAllStatusLogsByTopicId(Long id) {
        return statusLogRepository.findAllByTopicID(id);
    }
}
