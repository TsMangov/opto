package bigproject.demo.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity{
    //todo add active boolean to replace delete


    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "address")
    private String address;
    @Column(name = "contact_person")
    private String contactPerson;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bic")
    private String bic;
    @Column(name = "iban",unique = true)
    private String iban;
    @Column(name = "uid",unique = true)
    private String uid;
    @Column(name = "vat",unique = true)
    private String vat;
    @Column(unique = true)
    private String code;


    public Supplier() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Supplier setSupplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Supplier setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Supplier setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Supplier setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Supplier setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Supplier setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public Supplier setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getBic() {
        return bic;
    }

    public Supplier setBic(String bic) {
        this.bic = bic;
        return this;
    }

    public String getIban() {
        return iban;
    }

    public Supplier setIban(String iban) {
        this.iban = iban;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public Supplier setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getVat() {
        return vat;
    }

    public Supplier setVat(String vat) {
        this.vat = vat;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Supplier setCode(String code) {
        this.code = code;
        return this;
    }
}
