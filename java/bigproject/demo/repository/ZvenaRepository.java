package bigproject.demo.repository;

import bigproject.demo.model.entities.ZvenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZvenaRepository extends JpaRepository<ZvenaEntity, Long> {
}
