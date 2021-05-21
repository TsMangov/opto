package bigproject.demo.service;


import bigproject.demo.model.entities.CommentEntity;
import bigproject.demo.model.service.CommentServiceModel;
import bigproject.demo.model.viewModels.CommentAndLogs;

import java.util.List;

public interface CommentService {
    void seedComments();

    CommentEntity getById(Long idComment);

    // todo make it view model
    List<CommentEntity> findAllCommentsByTopicId(Long id);

    void saveComment(CommentServiceModel comment);

    List<CommentAndLogs> findAllCommentsAndLogsByTopicId(Long id);
}
