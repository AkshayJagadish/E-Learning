package tr.com.jowl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.com.jowl.model.Task;
import tr.com.jowl.model.User;
import tr.com.jowl.model.CourseVideos;
import tr.com.jowl.model.registered_courses;
import tr.com.jowl.service.CourseVideosService;
import tr.com.jowl.service.InstructorService;
import tr.com.jowl.service.RegisterService;
import tr.com.jowl.service.TaskService;
import tr.com.jowl.service.UserService;
import tr.com.jowl.utils.PassEncoding;
import tr.com.jowl.utils.Roles;
import tr.com.jowl.utils.Status;

/**
 * The UserController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GlobalController globalController;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;
    
    @Autowired
    InstructorService instructorService;
    
    @Autowired
    RegisterService regService;
    
    @Autowired
    CourseVideosService vidService;
    
    
    

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("root");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("login");
        return "login";
    }

    @RequestMapping("/tutors/home")
    public String home(Model model) {
        Task task =new Task();
        User user = new User(); 
        model.addAttribute("reqTask", task);
        model.addAttribute("User",user);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));

        model.addAttribute("allTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "home";
    }
    
    @RequestMapping("/student/home")
    public String studenthome(Model model) {
        Task task =new Task();
        User user = new User();
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("allTaskreg", regService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "student_home";
    }
    
    @RequestMapping("/student/home/CourseReg/{course_id}")
    public String courseReg(@PathVariable int course_id,Model model) {
        Task task =new Task();
        User user = new User();
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("contextcourse", taskService.findById(course_id));
       // model.addAttribute("contextreg", regService.findById(course_id));
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "RegCourses";
    }
    
    
    @RequestMapping("/student/home/ViewCourse/{course_id}")
    public String Viewcourse(@PathVariable int course_id,Model model) {
        Task task =new Task();
        User user = new User();
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("contextcourse", taskService.findById(course_id));
        model.addAttribute("AllVids", vidService.findAll());
       // model.addAttribute("contextreg", regService.findById(course_id));
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "ViewCourse";
    }
    
    @RequestMapping("/home/courses")
    public String courses(Model model) {
        Task task =new Task();
        model.addAttribute("reqTask", task);
      
        model.addAttribute("allTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("courses");
        return "courses";
    }
    
    @RequestMapping("/task/editTask/addVidTutorials/{id}")
    public String addVid(@PathVariable int id, Model model) {
        Task task =new Task();
        User user= new User();

        CourseVideos vid=new CourseVideos();
        model.addAttribute("reqTask", task);
        model.addAttribute("reqVid",vid);
        model.addAttribute("currentUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("contextcourse", taskService.findById(id));
        model.addAttribute("allTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("Controller called Successfully");
        return "AddVid";
    }
    
    @RequestMapping("/admin/users_manager")
    public String usermanager(Model model) {
        Task task =new Task();
        User user= new User();
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("allUser", userService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("course_manager");
        return "user_manager";
    }
    
    @RequestMapping("/admin/registered_courses")
    public String registeredCOurses(Model model) {
        Task task =new Task();
        User user= new User();
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("allUser", userService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allTaskreg", regService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("course_manager");
        return "registered_courses";
    }
    
    
    @RequestMapping("/admin/course_manager")
    public String coursemanager(Model model) {
        Task task =new Task();
        User user= new User();
        model.addAttribute("reqTask", task);
        model.addAttribute("User",user);
        model.addAttribute("allUser", userService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("course_manager");
        return "course_manager";
    }
    
    

    @RequestMapping("/admin")
    public String admin(Model model) {
        Task task =new Task();
        User user = new User(); 
        model.addAttribute("reqTask", task);
        model.addAttribute("User",user);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("allinst", instructorService.findAll());

        model.addAttribute("allTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("admin");
        return "adminn";
    }

    @RequestMapping("/tutor/register")
    public String tutorregister(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("registerTutor");
        return "TutorRegister";
    }
    
    @RequestMapping("/aa")
    public String aa(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("registerStudent");
        return "StudentRegister";
    }
    
    @RequestMapping("/student/register")
    public String studentregister(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("registerStudent");
        return "StudentRegister";
    }

    @RequestMapping(value = {"/user/tutor/register"}, method = RequestMethod.POST)
    public String registertutor(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {

        logger.info("/user/register");
        User user = userService.findByUserName(reqUser.getUsername());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/login";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/login";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));
        reqUser.setRole(Roles.ROLE_TUTOR.getValue());

        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/login";
    }

    @RequestMapping(value = {"/user/student/register"}, method = RequestMethod.POST)
    public String registerstudent(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {

        logger.info("/user/login");
        User user = userService.findByUserName(reqUser.getUsername());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/login";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/login";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));
        reqUser.setRole(Roles.ROLE_STUDENT.getValue());

        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/login";
    }


}
