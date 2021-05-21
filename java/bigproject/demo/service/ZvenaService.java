package bigproject.demo.service;

import bigproject.demo.model.entities.ZvenaEntity;

import java.util.List;

public interface ZvenaService {


    void seedZvena();

    List<ZvenaEntity> getAllZvena();

    ZvenaEntity findZvenoById(Long zvenoId);
}
