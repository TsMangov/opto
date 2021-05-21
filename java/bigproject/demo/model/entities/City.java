package bigproject.demo.model.entities;


import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{

    private String cityName;
    private int postcode;
    @ManyToOne
    private Country country;

    public City() {
    }

    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public City setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    @Column(name = "post_code")
    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public Country getCountry() {
        return country;
    }

    public City setCountry(Country country) {
        this.country = country;
        return this;
    }
}
