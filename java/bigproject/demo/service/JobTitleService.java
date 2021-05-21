package bigproject.demo.service;

import bigproject.demo.model.entities.JobTitleEntity;

import java.util.List;

public interface JobTitleService {
    List<JobTitleEntity> getAllJobTitles();

    void seedJobTitles();

    JobTitleEntity findJobByTitle(Long jobTitle);
}
