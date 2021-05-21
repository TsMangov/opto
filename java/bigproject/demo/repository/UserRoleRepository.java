package bigproject.demo.repository;


import bigproject.demo.model.entities.UserRoleEntity;
import bigproject.demo.model.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

  Optional<UserRoleEntity> findByRole(UserRole role);
}
