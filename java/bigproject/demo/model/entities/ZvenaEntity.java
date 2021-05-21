package bigproject.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zvena")
public class ZvenaEntity extends BaseEntity {
    @Column(nullable = false)
    private String zvenoName;

    public ZvenaEntity() {
    }

    public String getZvenoName() {
        return zvenoName;
    }

    public ZvenaEntity setZvenoName(String zvenoName) {
        this.zvenoName = zvenoName;
        return this;
    }
}
