package bigproject.demo.service.impl;

import bigproject.demo.model.entities.ZvenaEntity;
import bigproject.demo.repository.ZvenaRepository;
import bigproject.demo.service.ZvenaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZvenaServiceImpl implements ZvenaService {
    private final ZvenaRepository zvenaRepository;


    public ZvenaServiceImpl(ZvenaRepository zvenaRepository) {
        this.zvenaRepository = zvenaRepository;
    }


    @Override
    public void seedZvena() {
        ZvenaEntity zveno = new ZvenaEntity().setZvenoName("Direkciq");
        zvenaRepository.save(zveno);
         zveno = new ZvenaEntity().setZvenoName("Ceh 1 ");
        zvenaRepository.save(zveno);
         zveno = new ZvenaEntity().setZvenoName("Ceh 2");
        zvenaRepository.save(zveno);

    }

    @Override
    public List<ZvenaEntity> getAllZvena() {
        return this.zvenaRepository.findAll();
    }

    @Override
    public ZvenaEntity findZvenoById(Long zvenoId) {
        return zvenaRepository.findById(zvenoId).orElseThrow();
    }
}
