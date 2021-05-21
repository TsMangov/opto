package bigproject.demo.repository;

import bigproject.demo.model.entities.JobTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitleEntity, Long> {
}
