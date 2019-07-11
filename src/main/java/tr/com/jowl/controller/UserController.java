package tr.com.jowl.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.ApplicationException;
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
import tr.com.jowl.model.profilepic;
import tr.com.jowl.model.CourseVideos;
import tr.com.jowl.model.registered_courses;
import tr.com.jowl.service.CourseVideosService;
import tr.com.jowl.service.RegisterService;
import tr.com.jowl.service.TaskService;
import tr.com.jowl.service.UserService;
import tr.com.jowl.service.profilepicService;
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
    RegisterService regService;
    
    @Autowired
    CourseVideosService vidService;
    
    @Autowired
    profilepicService ppService;
    
    
    

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
        
        User user1=userService.findById(globalController.getLoginUser().getId());
        try
        {
        byte[] byteimg=user1.getPp();
        String enc = Base64.getEncoder().encodeToString(byteimg);
        logger.info("controller checkpoint2");
        model.addAttribute("encodedimg",enc);
        logger.info(enc);
        }
        catch(NullPointerException e)
        {
        	System.out.println("Null pointer Exception caught");
        }
        
        
        model.addAttribute("reqTask", task);
        model.addAttribute("User",user);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));

        model.addAttribute("allTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "tutors-home";
    }
    
    @RequestMapping("/student/home")
    public String studenthome(Model model) {
        Task task =new Task();
        User user = new User();
        registered_courses reg = new registered_courses();
        
        User user1=userService.findById(globalController.getLoginUser().getId());
        try
        {
        byte[] byteimg=user1.getPp();
        String enc = Base64.getEncoder().encodeToString(byteimg);
        logger.info("controller checkpoint2");
        model.addAttribute("encodedimg",enc);
        logger.info(enc);
        }
        catch(NullPointerException e)
        {
        	System.out.println("Null pointer Exception caught");
        }
        
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
    
    @RequestMapping("/student/home/editProfile")
    public String editProfile(Model model) {
        Task task =new Task();
        User user = new User();
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        model.addAttribute("currUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("allTaskreg", regService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "student-home-editProfile";
    }
    
   /* @RequestMapping("/tutor/home/changeprofpic")
    public String editprofilepic(Model model, HttpServletResponse response, @ModelAttribute("reqpp") profilepic reqpp) throws IOException {
    	logger.info("controller entered");
    	Task task =new Task();
        User user = new User();
        profilepic pp = new profilepic();
     
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        logger.info("controller checkpoint1");
        model.addAttribute("reqpp",pp);
        model.addAttribute("currpp",ppService.findById(1));
        
        
        /*User user1=userService.findById(globalController.getLoginUser().getId());
        byte[] byteimg=user1.getPp();
        String enc = Base64.getEncoder().encodeToString(byteimg);
        logger.info("controller checkpoint2");
        model.addAttribute("encodedimg",enc);
        logger.info(enc);*/
        
      /*  profilepic ppg=ppService.findById(1);
        byte[] imageData = ppg.getPpfile();
        String encoded = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("encoded",encoded);
        System.out.print("asdasda");
        logger.info(encoded);*/

        
        /*model.addAttribute("currUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("allTaskreg", regService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "tutor-home-editProfilePicture";}*/
    
    
    /*@RequestMapping("/pic")
    public String editprofilepic11(Model model, HttpServletResponse response, @ModelAttribute("reqpp") profilepic reqpp) throws IOException, ApplicationException {
        Task task =new Task();
        User user = new User();
        profilepic pp = new profilepic();
     
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        model.addAttribute("reqpp",pp);
        model.addAttribute("currpp",ppService.findById(1));
        profilepic ppg=ppService.findById(4);
        byte[] imageData = ppg.getPpfile();
        String encoded = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("encoded",encoded);
        System.out.print("asdasda");
        logger.info(encoded);
        /*response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); 
        response.setHeader("Cache-Control", "max-age=2628000"); 
        
        //obtaining bytes from DB 
        //ppService.
        byte[] imageData = ppg.getPpfile();
        //ByteArrayToImage(imageData);
        
        imageData = scale(imageData,50,50);
        //Some conversion 
        //Maybe to base64 string or something else 
        //Pay attention to encoding (UTF-8, etc) 
        
        //write result to http response 
        try (OutputStream out = response.getOutputStream()) { 
            out.write(imageData); */
        
      /*  model.addAttribute("currUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("allTaskreg", regService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "tutor-home-editProfilePicture";
        }
    
    
    public byte[] scale(byte[] fileData, int width, int height) throws ApplicationException {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth(); 
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            throw new ApplicationException("IOException in scale", null);
        }
    }*/
    
    
    	 /*  public void ByteArrayToImage(byte[] data) throws IOException {
    	      BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
    	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	      ImageIO.write(bImage, "jpg", bos );
    	      
    	      ByteArrayInputStream bis = new ByteArrayInputStream(data);
    	      BufferedImage bImage2 = ImageIO.read(bis);
    	      ImageIO.write(bImage2, "jpg", new File("output.jpg") );
    	      System.out.println("image created");
    	      
    	   }*/
    	
    
    @RequestMapping("/tutor/home/editProfile")
    public String editTutorProfile(Model model) {
        Task task =new Task();
        User user = new User();
        registered_courses reg = new registered_courses();
        
        model.addAttribute("reqTask", task);
        model.addAttribute("reqUser",user);
        model.addAttribute("reqReg",reg);
        model.addAttribute("currUser", userService.findById(globalController.getLoginUser().getId()));
        model.addAttribute("allTaskreg", regService.findAll());
        model.addAttribute("allTask", taskService.findAll());
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("home");
        return "tutor-home-editProfile";
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
        return "student-home-CourseReg-id";
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
    
    @RequestMapping("/tutors/home/ViewCourse/{course_id}")
    public String Viewcourse2(@PathVariable int course_id,Model model) {
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
    
    @RequestMapping("/tutors/home/task/editTask/addVidTutorials/{id}")
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
        return "tutors-home-task-edittask-addVidTutorials-id";
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
        return "admin-user_manager";
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
        return "admin-registered_courses";
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
        return "admin-course_manager";
    }
    
    

    @RequestMapping("/admin")
    public String admin(Model model) {
        Task task =new Task();
        User user = new User(); 
        model.addAttribute("reqTask", task);
        model.addAttribute("User",user);
        model.addAttribute("allUser", userService.findById(globalController.getLoginUser().getId()));

        model.addAttribute("allTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveTask", taskService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("admin");
        return "admin";
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
