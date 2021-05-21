package bigproject.demo.repository;


import bigproject.demo.model.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {



    @Query("select c from CommentEntity as c where c.topic.id = :id")
    List<CommentEntity> findAllByTopicId( @Param("id") Long id);
}
