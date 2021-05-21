package bigproject.demo.service.impl;


import bigproject.demo.model.binding.SupplierUpdateBindingModel;
import bigproject.demo.model.entities.BaseEntity;
import bigproject.demo.model.entities.Supplier;
import bigproject.demo.model.service.SupplierServiceModel;
import bigproject.demo.model.viewModels.SupplierShortViewModel;
import bigproject.demo.repository.SupplierRepository;
import bigproject.demo.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;


    @Autowired
    public SupplierServiceImpl(ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void initSuppliers() {
        Supplier sup = new Supplier()
                .setSupplierName("klient 1")
                .setCity("pld")
                .setCountry("bg")
                .setAddress("dunav 3")
                .setContactPerson("ivancho")
                .setPhoneNumber("123")
                .setBankName("rfz")
                .setBic("1")
                .setIban("1")
                .setUid("1")
                .setVat("1")
                .setCode("1");
        this.supplierRepository.save(sup);
        sup = new Supplier()
                .setSupplierName("klient 2")
                .setCity("sofiq")
                .setCountry("bg")
                .setAddress("cenatr 3")
                .setContactPerson("mariika")
                .setPhoneNumber("123")
                .setBankName("obb")
                .setBic("2")
                .setIban("2")
                .setUid("2")
                .setVat("2")
                .setCode("2");
        this.supplierRepository.save(sup);

    }

    @Override
    public boolean saveSupplier(SupplierServiceModel supplierServiceModel) {
        try {
            Supplier supplier = this.modelMapper.map(supplierServiceModel, Supplier.class);
            this.supplierRepository.save(supplier);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Supplier findSupplier(String supplier) {
        return this.supplierRepository.findSupplierBySupplierName(supplier).orElse(null);
    }

    @Override
    public Supplier findById(Long id) {
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void removeSupplierFromDB(Long id) {
            this.supplierRepository.deleteById(id);
    }

    @Override
    public boolean updateSupplier(SupplierUpdateBindingModel supplierUpdateBindingModel) {

        try {
            Supplier supplier = this.supplierRepository.findSupplierBySupplierName(supplierUpdateBindingModel.getSupplierName()).orElse(null);
            assert supplier != null;
            supplier.setSupplierName(supplierUpdateBindingModel.getSupplierName());
            supplier.setAddress(supplierUpdateBindingModel.getAddress());
            supplier.setCity(supplierUpdateBindingModel.getCity());
            supplier.setCountry(supplierUpdateBindingModel.getCountry());
            supplier.setContactPerson(supplierUpdateBindingModel.getContactPerson());
            supplier.setAddress(supplierUpdateBindingModel.getAddress());
            supplier.setBankName(supplierUpdateBindingModel.getBankName());
            supplier.setIban(supplierUpdateBindingModel.getIban());
            supplier.setBic(supplierUpdateBindingModel.getBic());
            supplier.setCode(supplierUpdateBindingModel.getCode());
            supplier.setPhoneNumber(supplierUpdateBindingModel.getPhoneNumber());
            supplier.setUid(supplierUpdateBindingModel.getUid());
            supplier.setVat(supplierUpdateBindingModel.getVat());
            this.supplierRepository.save(supplier);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supplierExists(String supplierName) {
        Supplier supplier = this.supplierRepository.findSupplierBySupplierName(supplierName).orElse(null);
        if(supplier == null){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean uidExists(String uid) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByUid(uid);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean vatExists(String vat) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByVat(vat);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean codeExists(String code) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByCode(code);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean ibanExists(String iban) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByIban(iban);
        if(supplier.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateName(String supplierName, Long id) {
            Supplier supplier = this.supplierRepository.findSupplierBySupplierName(supplierName).orElse(null);
            if(supplier == null || supplier.getId().equals(id)){
                return false;
            }
            else {
                return true;
            }
    }

    @Override
    public boolean cantUpdateIban(String iban, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByIban(iban);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateUid(String uid, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByUid(uid);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateCode(String code, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByCode(code);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean cantUpdateVat(String vat, Long id) {
        Optional<Supplier> supplier = this.supplierRepository.findSupplierByVat(vat);
        if(supplier.isEmpty()  || supplier.get().getId().equals(id)){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<SupplierShortViewModel> findAll() {
        return this.supplierRepository.findAll()
                .stream()
                .map(supplier -> this.modelMapper.map(supplier, SupplierShortViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public Long findSupplierId(String supplierName) {
        Optional<Supplier> bySupplierName = this.supplierRepository.findSupplierBySupplierName(supplierName);

        return bySupplierName.map(BaseEntity::getId).orElse(null);
    }

}
