package bigproject.demo;


import bigproject.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GigaDBApplicationInit implements CommandLineRunner {
  private final ProductService productService;
  private final UserService userService;
  private final TopicService topicService;
  private final CommentService commentService;
  private final CategoryService categoryService;
  private final JobTitleService jobTitleService;
  private final ZvenaService zvenaService;
  private final SupplierService supplierService;

  public GigaDBApplicationInit(ProductService productService, UserService userService, TopicService topicService, CommentService commentService, CategoryService categoryService, JobTitleService jobTitleService, ZvenaService zvenaService, SupplierService supplierService) {
    this.productService = productService;
    this.userService = userService;
    this.topicService = topicService;
    this.commentService = commentService;
    this.categoryService = categoryService;
    this.jobTitleService = jobTitleService;
    this.zvenaService = zvenaService;
    this.supplierService = supplierService;
  }

  @Override
  public void run(String... args) throws Exception {
    this.categoryService.initCategory();
    this.productService.initProducts();
    this.supplierService.initSuppliers();
    userService.seedUsers();
    topicService.seedTopics();
    commentService.seedComments();
    jobTitleService.seedJobTitles();
    zvenaService.seedZvena();

    //todo username in logout button, adress and pic in profile with cloud, izdavane na faktura po order

    /*
    this.commentService.saveComment(new CommentServiceModel()
            .setCommentText("asdasdsad")
            .setTopicID(Long.valueOf("1"))
            .setUsername("user"));
     */

//    topicService.addCommentToTopic(Long.valueOf("1"), Long.valueOf("1"));
//    topicService.addCommentToTopic(Long.valueOf("1"), Long.valueOf("2"));
//    topicService.addCommentToTopic(Long.valueOf("2"), Long.valueOf("3"));

  }
}
