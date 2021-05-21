package bigproject.demo.repository;

import bigproject.demo.model.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findAllByUserEntity_Username(String username);

    List<CartItemEntity> findAllByOrderEntity_Id(Long id);

    List<CartItemEntity> findAllByTopicEntity_Id(Long id);
}
