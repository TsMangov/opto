package bigproject.demo.service.impl;

import bigproject.demo.model.entities.JobTitleEntity;
import bigproject.demo.repository.JobTitleRepository;
import bigproject.demo.service.JobTitleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleServiceImpl implements JobTitleService {
    private final JobTitleRepository jobTitleRepository;

    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository) {
        this.jobTitleRepository = jobTitleRepository;
    }


    @Override
    public List<JobTitleEntity> getAllJobTitles() {
        //todo make it with view
        return this.jobTitleRepository.findAll();
    }

    @Override
    public void seedJobTitles() {
        JobTitleEntity first = new JobTitleEntity().setTitle("shlosher");
        this.jobTitleRepository.save(first);
         first = new JobTitleEntity().setTitle("strugar");
        this.jobTitleRepository.save(first);
         first = new JobTitleEntity().setTitle("shefche");
        this.jobTitleRepository.save(first);
         first = new JobTitleEntity().setTitle("sekretar");
        this.jobTitleRepository.save(first);
         first = new JobTitleEntity().setTitle("direktor");
        this.jobTitleRepository.save(first);
    }

    @Override
    public JobTitleEntity findJobByTitle(Long jobTitle) {
        //todo fix throw
        return this.jobTitleRepository.findById(jobTitle).orElseThrow();
    }
}
