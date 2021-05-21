package bigproject.demo.service.impl;


import bigproject.demo.model.entities.TopicEntity;
import bigproject.demo.model.entities.UserEntity;
import bigproject.demo.model.entities.UserRoleEntity;
import bigproject.demo.model.entities.enums.JobStatus;
import bigproject.demo.model.entities.enums.UserRole;
import bigproject.demo.model.service.TopicServiceModel;
import bigproject.demo.model.viewModels.TopicViewModel;
import bigproject.demo.repository.TopicRepository;
import bigproject.demo.repository.UserRoleRepository;
import bigproject.demo.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final StatusLogService statusLogService;
    private final UserRoleRepository userRoleRepository;
    private final SupplierService supplierService;

    public TopicServiceImpl(TopicRepository topicRepository, ModelMapper modelMapper, UserService userService, StatusLogService statusLogService, UserRoleRepository userRoleRepository, SupplierService supplierService) {
        this.topicRepository = topicRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.statusLogService = statusLogService;
        this.userRoleRepository = userRoleRepository;
        this.supplierService = supplierService;
    }

    @Override
    public void seedTopics() {
        if(this.topicRepository.count() == 0 ){
            TopicEntity topic = new TopicEntity().setTopicName("first topic").setTopicDescription("tetetete tete tasd adada dad sadddd ddddd ddddd ddddd dddd d  ddddd ddddd ddddd dddd dd dd ddddd ddd dddddd dddd")
                    .setDateTime(LocalDateTime.now()).setUserEntity( userService.getUserByUsername("admin") ).setStatus(JobStatus.STARTED).setSupplier(supplierService.findById(Long.valueOf("1")));

            TopicEntity topic1 = new TopicEntity().setTopicName("sedcc topic").setTopicDescription("wwwwwww")
                    .setDateTime(LocalDateTime.now()).setUserEntity( userService.getUserByUsername("admin") ).setStatus(JobStatus.STARTED).setSupplier(supplierService.findById(Long.valueOf("2")));

            TopicEntity topic2 = new TopicEntity().setTopicName("third topic").setTopicDescription("adasd")
                    .setDateTime(LocalDateTime.now()).setUserEntity( userService.getUserByUsername("user") ).setStatus(JobStatus.STARTED).setSupplier(supplierService.findById(Long.valueOf("2")));
            this.topicRepository.saveAndFlush(topic);
            this.topicRepository.saveAndFlush(topic1);
            this.topicRepository.saveAndFlush(topic2);
        }

    }

//    @Override
//    public void addCommentToTopic(Long idTopic, Long idComment) {
//        TopicEntity topicEntity = this.topicRepository.findById(idTopic).orElseThrow();
//        CommentEntity commentEntity = this.commentService.getById(idComment);
//        commentEntity.setTopic(topicEntity);
//        this.commentRepository.save(commentEntity);
//    }

    //todo print all topics
//    @Override
//    public void printAll(){
//        this.topicRepository.findAll().stream().forEach(t->{
//            t.getCommentEntityList().stream().forEach(c->{
//                System.out.println(c.getCommentText() + " -  - " + t.getTopicName());
//            });
//        });
//    }

    @Override
    public void saveTopic(TopicServiceModel topicServiceModel) {
        TopicEntity entity = modelMapper.map(topicServiceModel, TopicEntity.class);
        entity.setDateTime(LocalDateTime.now());
        entity.setStatus(JobStatus.STARTED);
        entity.setUserEntity( this.userService.getUserByUsername( topicServiceModel.getUsername() ) );
        entity.setSupplier( this.supplierService.findById(topicServiceModel.getClient()) );
        this.topicRepository.saveAndFlush(entity);
    }

    @Override
    public List<TopicViewModel> getAllTopics() {
        List<TopicViewModel> result = new ArrayList<>();
        TopicEntity entity = this.topicRepository.findById(Long.valueOf("1")).orElseThrow();
        String a = entity.getUserEntity().getFullname();

        this.topicRepository.findAll().forEach(t -> {
            TopicViewModel topic = modelMapper.map(t, TopicViewModel.class);
            topic.setFullNameOfUser( t.getUserEntity().getFullname()  );
            result.add(topic);
        });
        System.out.println();


        return result;
    }

    @Override
    public TopicViewModel findById(Long id) {
        //todo throw good
        TopicEntity entity = this.topicRepository.findById(id).orElseThrow();
        TopicViewModel result = modelMapper.map(entity, TopicViewModel.class);
        result.setFullNameOfUser(entity.getUserEntity().getFullname());
        result.setClient(entity.getSupplier().getSupplierName());
        return result;
    }

    @Override
    public TopicEntity findTopicById(Long topicID) {
        //todo throw good
        return this.topicRepository.findById(topicID).orElseThrow();
    }

    @Override
    public String changeStatusFromStatusToNext(Long id) {
        //todo fix throw
        TopicEntity entity = topicRepository.findById(id).orElseThrow();
        int statusId = entity.getStatus().getId();
        JobStatus newStatus = JobStatus.getStatusById(statusId + 1);
        if(newStatus == null){
            return "problem s promqnata na statusa";
        }else{
            entity.setStatus(newStatus);
            this.topicRepository.save(entity);
            return "Statusa Uspehsno promenen ot Started na ZAKUPEN";
        }
    }

    @Override
    public String changeStatusFromStartToZakupen(Long topicId, String username) {
        //todo fix throw
        UserEntity user = this.userService.getUserByUsername(username);
        TopicEntity entity = topicRepository.findById(topicId).orElseThrow();
        List<UserRoleEntity> roles = user.getRoles();
        boolean canChangeStatus = false;
        for (UserRoleEntity role : roles) {
            if(role.getRole().equals(UserRole.ADMIN) || role.getRole().equals(UserRole.SKLAD)){
                canChangeStatus = true;
                break;
            }

        }
        String result = "";
        if( canChangeStatus ){

            int statusId = entity.getStatus().getId();
            JobStatus newStatus = JobStatus.ZAKUPEN;
            if( statusId != 1 ){
                result =   "problem s promqnata na statusa";
            }else{
                entity.setStatus(newStatus);
                this.topicRepository.save(entity);
                result =   "Statusa Uspehsno promenen ot ZAKUPEN na ZAKUPEN";
            }
        }else {
            result =   "nqmate prava za promqna na tozi status";
        }
        this.statusLogService.addToLog(entity, user, result);
        return result;
    }

    @Override
    public String changeStatusFromStartToIzrabotvan(Long topicId, String username) {
        //todo fix throw
        UserEntity user = this.userService.getUserByUsername(username);
        TopicEntity entity = topicRepository.findById(topicId).orElseThrow();
        List<UserRoleEntity> roles = user.getRoles();
        boolean canChangeStatus = false;
        for (UserRoleEntity role : roles) {
            if(role.getRole().equals(UserRole.ADMIN) || role.getRole().equals(UserRole.PROIZVODSTVO)){
                canChangeStatus = true;
                break;
            }
        }
        String result = "";
        if( canChangeStatus ){

            int statusId = entity.getStatus().getId();
            JobStatus newStatus = JobStatus.IZRABOTVAN;
            if( statusId != 2 ){
                result =   "problem s promqnata na statusa";
            }else{
                entity.setStatus(newStatus);
                this.topicRepository.save(entity);
                result =   "Statusa Uspehsno promenen ot Started na Izraboten";
            }
        }else {
            result =   "nqmate prava za promqna na tozi status";
        }
        this.statusLogService.addToLog(entity, user, result);
        return result;
    }

    @Override
    public String changeStatusFromStartToProveren(Long topicId, String username) {
        //todo fix throw
        UserEntity user = this.userService.getUserByUsername(username);
        TopicEntity entity = topicRepository.findById(topicId).orElseThrow();
        List<UserRoleEntity> roles = user.getRoles();
        boolean canChangeStatus = false;
        for (UserRoleEntity role : roles) {
            if(role.getRole().equals(UserRole.ADMIN) || role.getRole().equals(UserRole.OTK)){
                canChangeStatus = true;
                break;
            }
        }
        String result = "";
        if( canChangeStatus ){

            int statusId = entity.getStatus().getId();
            JobStatus newStatus = JobStatus.PROVEREN;
            if( statusId != 3 ){
                result = "problem s promqnata na statusa";
            }else{
                entity.setStatus(newStatus);
                this.topicRepository.save(entity);
                result =  "Statusa Uspehsno promenen ot Started na Proveren";
            }
        }else {
            result = "nqmate prava za promqna na tozi status";
        }
        this.statusLogService.addToLog(entity, user, result);
        return result;
    }

    @Override
    public String getTopicTextByTopicId(Long id) {
        //todo fix throw
        TopicEntity topicEntity = this.topicRepository.findById(id).orElseThrow();
        return topicEntity.getTopicName();
    }

}

