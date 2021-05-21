package bigproject.demo.repository;

import bigproject.demo.model.entities.CategoryEntity;
import bigproject.demo.model.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory_Id(Long id);
}
