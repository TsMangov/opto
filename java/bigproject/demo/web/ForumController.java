package bigproject.demo.web;


import bigproject.demo.model.binding.CommentBindingModel;
import bigproject.demo.model.binding.TopicBindingModel;
import bigproject.demo.model.service.CommentServiceModel;
import bigproject.demo.model.service.TopicServiceModel;
import bigproject.demo.service.CartItemService;
import bigproject.demo.service.CommentService;
import bigproject.demo.service.SupplierService;
import bigproject.demo.service.TopicService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/forum")
public class ForumController {
    private final TopicService topicService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final CartItemService cartItemService;
    private final SupplierService supplierService;

    public ForumController(TopicService topicService, CommentService commentService, ModelMapper modelMapper, CartItemService cartItemService, SupplierService supplierService) {
        this.topicService = topicService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.cartItemService = cartItemService;
        this.supplierService = supplierService;
    }

    @GetMapping("/all")
    public String forum(Model model) {
        model.addAttribute( "allTopics", this.topicService.getAllTopics() );
        System.out.println();
        return "forum";
    }

//    @GetMapping("/topic")
//    public String topic() {
//        return "topic";
//    }


    @GetMapping("/add-topic")
    public String addTopic(Model model){
        if (!model.containsAttribute("topicBindingModel")){
            model.addAttribute("topicBindingModel", new TopicBindingModel());
        }
        model.addAttribute("allSuppliersList", this.supplierService.findAll());

        return "add-topic";
    }

    @PostMapping("/add-topic")
    public String addTopicConfirm(
        @Valid TopicBindingModel topicBindingModel,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes,
        Principal principal) {

        System.out.println();
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("topicBindingModel", topicBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.topicBindingModel", bindingResult);

                return "redirect:add-topic";
            }
        topicBindingModel.setUsername(principal.getName());
        this.topicService.saveTopic(modelMapper.map(topicBindingModel, TopicServiceModel.class));
       // System.out.println();
        return "redirect:all";
    }

    @GetMapping("/topic/{id}")
    public String goToTopic(@PathVariable Long id, Model model){
        model.addAttribute("allComments", this.commentService.findAllCommentsAndLogsByTopicId(id) );
        model.addAttribute("topic", this.topicService.findById(id) );
        model.addAttribute("listOfOrdersForTopic", this.cartItemService.listOfOrdersForTopic(id) );
        if (!model.containsAttribute("commentBindingModel")){
            model.addAttribute("commentBindingModel", new CommentBindingModel());
        }
        if (!model.containsAttribute("StatusChangeResult")){
            model.addAttribute("StatusChangeResult", null);
        }
        model.addAttribute("topicId", id);
        return "topic";
    }


    @PostMapping("/add-comment-to-topic/{id}")
    public String addCommentConfirm(@PathVariable Long id,
            @Valid CommentBindingModel commentBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal, Model model) {

        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentBindingModel", commentBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentBindingModel", bindingResult);

            return "redirect:/forum/topic/{id}";
        }

        commentBindingModel.setUsername(principal.getName());
        commentBindingModel.setTopicID(id);
        this.commentService.saveComment(modelMapper.map(commentBindingModel, CommentServiceModel.class));
        model.addAttribute("topicId", id);

        return "redirect:/forum/topic/{id}";
    }

    @PostMapping("/zakupen/{topicId}")
    public String changeStatusFromStartToZakupen(@PathVariable Long topicId, Principal principal, RedirectAttributes redirectAttributes, Model model) {

        String result =  this.topicService.changeStatusFromStartToZakupen(topicId, principal.getName());
        model.addAttribute("topicId", topicId);
        redirectAttributes.addFlashAttribute( "StatusChangeResult" , result);
        return "redirect:/forum/topic/{topicId}";
    }

    @PostMapping("/izrabotvan/{topicId}")
    public String changeStatusFromStartToIzrabotvan(@PathVariable Long topicId, Principal principal, RedirectAttributes redirectAttributes, Model model) {

        String result =  this.topicService.changeStatusFromStartToIzrabotvan(topicId, principal.getName());
        model.addAttribute("topicId", topicId);
        redirectAttributes.addFlashAttribute( "StatusChangeResult" , result);
        return "redirect:/forum/topic/{topicId}";
    }

    @PostMapping("/proveren/{topicId}")
    public String changeStatusFromStartToProveren(@PathVariable Long topicId, Principal principal, RedirectAttributes redirectAttributes, Model model) {

        String result =  this.topicService.changeStatusFromStartToProveren(topicId, principal.getName());
        model.addAttribute("topicId", topicId);
        redirectAttributes.addFlashAttribute( "StatusChangeResult" , result);
        return "redirect:/forum/topic/{topicId}";
    }

}
