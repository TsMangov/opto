package bigproject.demo.service.impl;


import bigproject.demo.model.entities.CommentEntity;
import bigproject.demo.model.entities.StatusLog;
import bigproject.demo.model.service.CommentServiceModel;
import bigproject.demo.model.viewModels.CommentAndLogs;
import bigproject.demo.repository.CommentRepository;
import bigproject.demo.service.CommentService;
import bigproject.demo.service.StatusLogService;
import bigproject.demo.service.TopicService;
import bigproject.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final TopicService topicService;
    private final UserService userService;
    private final StatusLogService statusLogService;

    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository, TopicService topicService, UserService userService, StatusLogService statusLogService) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.topicService = topicService;
        this.userService = userService;
        this.statusLogService = statusLogService;
    }

    @Override
    public void seedComments() {
        CommentEntity commentEntity = new CommentEntity().setCommentText("tekst na coment 1").setUserEntity(userService.getUserByUsername("admin"))
                .setCommentDateTime(LocalDateTime.now()).setTopic( this.topicService.findTopicById( Long.valueOf("1") ));
        CommentEntity commentEntity1 = new CommentEntity().setCommentText("tekst na coment 2").setUserEntity(userService.getUserByUsername("admin"))
                .setCommentDateTime(LocalDateTime.now()).setTopic( this.topicService.findTopicById( Long.valueOf("1") ));
        CommentEntity commentEntity2 = new CommentEntity().setCommentText("tekst na coment 3").setUserEntity(userService.getUserByUsername("user"))
                .setCommentDateTime(LocalDateTime.now()).setTopic( this.topicService.findTopicById( Long.valueOf("2") ));
        this.commentRepository.save(commentEntity);
        this.commentRepository.save(commentEntity1);
        this.commentRepository.save(commentEntity2);
    }

    @Override
    public CommentEntity getById(Long idComment) {
        return this.commentRepository.findById(idComment).orElseThrow();
    }

    @Override
    public List<CommentEntity> findAllCommentsByTopicId(Long id) {

        return this.commentRepository.findAllByTopicId(id);
    }

    @Override
    public void saveComment(CommentServiceModel comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentText(comment.getCommentText());
        commentEntity.setCommentDateTime(LocalDateTime.now());
        commentEntity.setTopic( this.topicService.findTopicById( comment.getTopicID() ) );
        commentEntity.setUserEntity(userService.getUserByUsername(comment.getUsername()));
        System.out.println();
        this.commentRepository.save(commentEntity);

    }

    @Override
    public List<CommentAndLogs> findAllCommentsAndLogsByTopicId(Long id) {

        List<CommentEntity> allCommentsByTopicId = this.commentRepository.findAllByTopicId(id);
        List<StatusLog> allStatusLogsByTopicId = this.statusLogService.getAllStatusLogsByTopicId( id);

        List<CommentAndLogs> result = new ArrayList<>();
        for (CommentEntity commentEntity : allCommentsByTopicId) {
            CommentAndLogs entity = new CommentAndLogs()
                    .setUserEntity(commentEntity.getUserEntity())
                    .setCommentDateTime(commentEntity.getCommentDateTime())
                    .setCommentText(commentEntity.getCommentText())
                    .setTopic(commentEntity.getTopic());
            result.add(entity);
        }
        for (StatusLog statusLog : allStatusLogsByTopicId) {
            CommentAndLogs entity = new CommentAndLogs()
                    .setUserEntity(statusLog.getUserEntity())
                    .setCommentDateTime(statusLog.getStatusChangeDateAndTime())
                    .setCommentText(statusLog.getStatusChangeText())
                    .setTopic(statusLog.getTopic());
            result.add(entity);
        }


        Collections.sort(result, new Comparator<CommentAndLogs>() {
            @Override
            public int compare(CommentAndLogs u1, CommentAndLogs u2) {
                return u1.getCommentDateTime().compareTo(u2.getCommentDateTime());
            }
        });


        System.out.println();
        return result;

    }


}
