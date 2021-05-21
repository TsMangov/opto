package bigproject.demo.repository;

import bigproject.demo.model.entities.StatusLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusLogRepository extends JpaRepository<StatusLog, Long> {

    @Query("select s from StatusLog as s where s.topic.id = :id")
    List<StatusLog> findAllByTopicID(@Param("id") Long id);
}
