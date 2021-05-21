package bigproject.demo.repository;


import bigproject.demo.model.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long > {


    Optional<Supplier> findSupplierBySupplierName(String name);
    Optional<Supplier> findSupplierByUid(String uid);
    Optional<Supplier> findSupplierByIban(String iban);
    Optional<Supplier> findSupplierByVat(String vat);
    Optional<Supplier> findSupplierByCode(String code);

}
