package bigproject.demo.service;



import bigproject.demo.model.binding.SupplierUpdateBindingModel;
import bigproject.demo.model.entities.Supplier;
import bigproject.demo.model.service.SupplierServiceModel;
import bigproject.demo.model.viewModels.SupplierShortViewModel;

import java.util.List;

public interface SupplierService {
    void initSuppliers();

    boolean saveSupplier(SupplierServiceModel supplierServiceModel);

    Supplier findSupplier(String supplier);

    Supplier findById(Long id);

    void removeSupplierFromDB(Long id);

    boolean updateSupplier(SupplierUpdateBindingModel supplierUpdateBindingModel);


    boolean supplierExists(String supplierName);

    boolean uidExists(String uid);

    boolean vatExists(String vat);

    boolean codeExists(String code);

    boolean ibanExists(String iban);


    boolean cantUpdateName(String supplierName, Long id);

    boolean cantUpdateIban(String iban, Long id);

    boolean cantUpdateUid(String uid, Long id);

    boolean cantUpdateCode(String code, Long id);

    boolean cantUpdateVat(String vat, Long id);

    List<SupplierShortViewModel> findAll();

    Long findSupplierId(String supplierName);
}
