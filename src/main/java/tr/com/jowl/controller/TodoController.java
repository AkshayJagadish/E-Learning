package tr.com.jowl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tr.com.jowl.model.CourseVideos;
import tr.com.jowl.model.Task;
import tr.com.jowl.model.User;

import tr.com.jowl.model.registered_courses;
import tr.com.jowl.service.CourseVideosService;
import tr.com.jowl.service.RegisterService;
import tr.com.jowl.service.TaskService;
import tr.com.jowl.service.UserService;
import tr.com.jowl.utils.Status;

import java.time.LocalDateTime;

/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RegisterService regService;
    
    @Autowired
    private CourseVideosService courseVideosService;
    
    
    
    @Autowired
    private GlobalController globalController;
    

    @RequestMapping(value = {"/tutors/home/task/saveTask"}, method = RequestMethod.POST)
    public String saveTodo(@ModelAttribute("reqTask") Task reqTask,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/task/save");
        try {
            reqTask.setCreateDate(LocalDateTime.now());
            reqTask.setStatus(Status.ACTIVE.getValue());
            reqTask.setUserId(globalController.getLoginUser().getId());
            taskService.save(reqTask);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/tutors/home";
    }
    
    @RequestMapping(value = {"/admin/task/saveTask"}, method = RequestMethod.POST)
    public String AdminSaveCourse(@ModelAttribute("reqTask") Task reqTask,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/task/save");
        try {
            reqTask.setCreateDate(LocalDateTime.now());
            reqTask.setStatus(Status.ACTIVE.getValue());
            reqTask.setUserId(globalController.getLoginUser().getId());
            taskService.save(reqTask);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:admin/course_manager";
    }
    
    @RequestMapping(value = {"/task/saveVid"}, method = RequestMethod.POST)
    public String saveVid(@ModelAttribute("reqVid") CourseVideos reqVid,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/task/save");
        try {
           
            //reqTask.setUserId(globalController.getLoginUser().getId());
            courseVideosService.save(reqVid);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/tutors/home";
    }
    
   /* @RequestMapping(value = {"/tutors/home/changeprofpic/changepp"}, method = RequestMethod.POST)
    public String savepp1(@RequestParam("files") MultipartFile files, @ModelAttribute("reqpp") profilepic reqpp,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/task/save");
        try {
           
            //reqTask.setUserId(globalController.getLoginUser().getId());
        	reqpp.setPpfile(files.getBytes());
            //ppService.save(reqpp);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/tutors/home";
    }*/
    
   
    
    @RequestMapping(value = {"student/home/reg/saveReg"}, method = RequestMethod.POST)
    public String saveTodo1(@ModelAttribute("reqReg") registered_courses reqReg,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/reg/saveReg");
        try {
        	//registered_courses regc = regService.findById(id);
        	//regc.setId(id);
        	//regc.setUserId();//
        	//reqReg.setCourseId(id);
            reqReg.setUser_id(globalController.getLoginUser().getId());
            reqReg.setUsername(globalController.getLoginUser().getUsername());
            regService.save(reqReg);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/student/home";
    }
    
    @RequestMapping(value = {"/reg/editReg"}, method = RequestMethod.POST)
    public String editReg(@ModelAttribute("reqReg") registered_courses reqReg,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/reg/editReg");
        try {
        	//registered_courses regc = regService.findById(id);
        	//regc.setId(id);
        	//regc.setUserId();//
        	//reqReg.setCourseId(id);
            //reqReg.setUser_id(globalController.getLoginUser().getId());
            //reqReg.setUsername(globalController.getLoginUser().getUsername());
            regService.update(reqReg);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/admin/registered_courses";
    }
    
    /*@RequestMapping(value = {"/tutor/home/updateprofpic"}, method = RequestMethod.POST)
    public String updateprofilepic(@RequestParam("files") MultipartFile files, @ModelAttribute("currUser") User currUser,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/reg/editReg");
        try {
        	//registered_courses regc = regService.findById(id);
        	//regc.setId(id);
        	//regc.setUserId();//
        	//reqReg.setCourseId(id);
            //reqReg.setUser_id(globalController.getLoginUser().getId());
            //reqReg.setUsername(globalController.getLoginUser().getUsername());
        	currUser.setPp(files.getBytes());
            userService.update(currUser);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/tutors/home";
    }*/
    
    
    @RequestMapping(value = {"/user/saveUser"}, method = RequestMethod.POST)
    public String saveTodo1(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/task/save");
        try {
           
           // reqUser.setUserId(globalController.getLoginUser().getId());
            userService.save(reqUser);
            redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }
        return "home";
    }

    @RequestMapping(value = {"/tutors/task/editTask"}, method = RequestMethod.POST)
    public String editTodo(@ModelAttribute("editTask") Task editTask, Model model) {
        logger.info("/task/editTask");
        model.addAttribute("contextcourse", taskService.findById(editTask.getId()));
        try {
            Task task = taskService.findById(editTask.getId());
            if (!task.equals(editTask)) {
                taskService.update(editTask);
                model.addAttribute("msg", "success");
            } else {
                model.addAttribute("msg", "same");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "fail");
            logger.error("editUser: " + e.getMessage());
        }
        model.addAttribute("editTodo", editTask);
        return "tutors-home-task-edit-id";
    }
    
    @RequestMapping(value = {"/admin/task/editTask"}, method = RequestMethod.POST)
    public String AdmineditTask(@ModelAttribute("editTask") Task editTask, Model model) {
        logger.info("CONTROLLER USED");
        model.addAttribute("contextcourse", taskService.findById(editTask.getId()));
        try {
            Task task = taskService.findById(editTask.getId());
            if (!task.equals(editTask)) {
                taskService.update(editTask);
                model.addAttribute("msg", "success");
                return "admin";

            } else {
                model.addAttribute("msg", "same");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "fail");
            logger.error("editUser: " + e.getMessage());
        }
        model.addAttribute("editTodo", editTask);
        return "admin";
    }
    
    
    
    @RequestMapping(value = {"/admin/user/editUser"}, method = RequestMethod.POST)
    public String editTodo1(@ModelAttribute("editTask") User editUser, Model model) {
        logger.info("/task/editTask");
        try {
            User user = userService.findById(editUser.getId());
            if (!user.equals(editUser)) {
                userService.update(editUser);
                model.addAttribute("msg", "success");
            } else {
                model.addAttribute("msg", "same");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "fail");
            logger.error("editUser: " + e.getMessage());
        }
        model.addAttribute("editTodo", editUser);
        return "redirect:/admin/users_manager";
    }
    
    @RequestMapping(value = {"/student/home/user/currUser"}, method = RequestMethod.POST)
    public String editTodo11(@RequestParam("files") MultipartFile files,@ModelAttribute("currUser") User currUser, Model model) {
        logger.info("/task/editTask");
        User user1 = userService.findById(globalController.getLoginUser().getId());

        try {
            	if(files.isEmpty())
            	{
            		currUser.setPp(user1.getPp());
            		userService.update(currUser);
            	}
            	else
            	{
            	currUser.setPp(files.getBytes());
                userService.update(currUser);
                model.addAttribute("msg", "success");
            	}
            }
          catch (Exception e) {
            model.addAttribute("msg", "fail");
            logger.error("editUser: " + e.getMessage());
        }
        model.addAttribute("editTodo", currUser);
        return "redirect:/student/home";
    }
    
    @RequestMapping(value = {"/tutor/home/user/currUser"}, method = RequestMethod.POST)
    public String editTodo111(@RequestParam("files") MultipartFile files, @ModelAttribute("currUser") User currUser, Model model) {
        logger.info("profile pic controller entered");
        User user1 = userService.findById(globalController.getLoginUser().getId());
        try {
        	if(files.isEmpty())
        	{
        		currUser.setPp(user1.getPp());
        		userService.update(currUser);
        	}
        	else
        	{
        	currUser.setPp(files.getBytes());
            userService.update(currUser);
            model.addAttribute("msg", "success");
        	}
        }
      catch (Exception e) {
        model.addAttribute("msg", "fail");
        logger.error("editUser: " + e.getMessage());
    }
    model.addAttribute("editTodo", currUser);
        return "redirect:/tutors/home";
    }


    @RequestMapping(value = "/tutors/home/task/{operation}/{id}", method = RequestMethod.GET)
    public String todoOperation(@PathVariable("operation") String operation,
                                @PathVariable("id") int id, final RedirectAttributes redirectAttributes,
                                Model model) {

        logger.info("/task/operation: {} ", operation);
        if (operation.equals("trash")) {
            Task task = taskService.findById(id);
            if (task != null) {
                task.setStatus(Status.PASSIVE.getValue());
                taskService.update(task);
                redirectAttributes.addFlashAttribute("msg", "trash");
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        if (operation.equals("restore")) {
            Task task = taskService.findById(id);
            if (task != null) {
                task.setStatus(Status.ACTIVE.getValue());
                taskService.update(task);
                redirectAttributes.addFlashAttribute("msg", "active");
                redirectAttributes.addFlashAttribute("msgText", "Task " + task.getTaskName() + " Restored Successfully.");
            } else {
                redirectAttributes.addFlashAttribute("msg", "active_fail");
                redirectAttributes.addFlashAttribute("msgText", "Task Activation failed !!! Task:" + task.getTaskName());

            }
        } else if (operation.equals("delete")) {
            if (taskService.delete(id)) {
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Task deleted permanently");
                
            } else {
                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
            }
        } else if (operation.equals("edit")) {
            Task editTask = taskService.findById(id);
            if (editTask != null) {
                model.addAttribute("editTask", editTask);
                return "tutors-home-task-edit-id";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        return "redirect:/tutors/home";
    }
    

    @RequestMapping(value = "/tutors/ViewVid/{operation}/{course_id}/{id}", method = RequestMethod.GET)
    public String VidDelete(@PathVariable("operation") String operation,
                                @PathVariable("id") int id, @PathVariable("course_id") int course_id, final RedirectAttributes redirectAttributes,
                                Model model) {
        if (operation.equals("delete")) {
        	courseVideosService.delete(id);
        }
        return "redirect:/tutors/home/ViewCourse/{course_id}";
    	
    }
    
    @RequestMapping(value = "/admin/course_manager/task/{operation}/{id}", method = RequestMethod.GET)
    public String adminCourseEdit(@PathVariable("operation") String operation,
                                @PathVariable("id") int id, final RedirectAttributes redirectAttributes,
                                Model model) {

        logger.info("/task/operation: {} ", operation);
        if (operation.equals("trash")) {
            Task task = taskService.findById(id);
            if (task != null) {
                task.setStatus(Status.PASSIVE.getValue());
                taskService.update(task);
                redirectAttributes.addFlashAttribute("msg", "trash");
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        if (operation.equals("restore")) {
            Task task = taskService.findById(id);
            if (task != null) {
                task.setStatus(Status.ACTIVE.getValue());
                taskService.update(task);
                redirectAttributes.addFlashAttribute("msg", "active");
                redirectAttributes.addFlashAttribute("msgText", "Task " + task.getTaskName() + " Restored Successfully.");
            } else {
                redirectAttributes.addFlashAttribute("msg", "active_fail");
                redirectAttributes.addFlashAttribute("msgText", "Task Activation failed !!! Task:" + task.getTaskName());

            }
        } else if (operation.equals("delete")) {
            if (taskService.delete(id)) {
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Task deleted permanently");
                
            } else {
                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
            }
        } else if (operation.equals("edit")) {
            Task editTask = taskService.findById(id);
            if (editTask != null) {
                logger.info("edit task entered");

                model.addAttribute("editTask", editTask);
                return "admin-course_manager-task-edit-id";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        return "redirect:/admin/course_manager";
    }
    
    @RequestMapping(value = "/admin/users_manager/user/{operation}/{id}", method = RequestMethod.GET)
    public String todoOperation1(@PathVariable("operation") String operation,
                                @PathVariable("id") int id, final RedirectAttributes redirectAttributes,
                                Model model) {

        logger.info("/user/operation: {} ", operation);
        if (operation.equals("trash")) {
            User user = userService.findById(id);
            if (user != null) {
                //user.setStatus(Status.PASSIVE.getValue());
                userService.update(user);
                redirectAttributes.addFlashAttribute("msg", "trash");
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        if (operation.equals("restore")) {
            User user = userService.findById(id);
            if (user != null) {
                //user.setStatus(Status.ACTIVE.getValue());
                userService.update(user);
                redirectAttributes.addFlashAttribute("msg", "active");
                redirectAttributes.addFlashAttribute("msgText", "Task " + user.getUsername() + " Restored Successfully.");
            } else {
                redirectAttributes.addFlashAttribute("msg", "active_fail");
                redirectAttributes.addFlashAttribute("msgText", "Task Activation failed !!! Task:" + user.getUsername());

            }
        } else if (operation.equals("delete")) {
            if (userService.delete(id)) {
            	logger.info("delete in controller 1");

                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Task deleted permanently");
            } else {
                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
            }
        } else if (operation.equals("edit")) {
        	logger.info("edit operation worked!!!!");
            User editUser = userService.findById(id);
            logger.info("edit operation worked2!!!!");
            if (editUser != null) {
            	logger.info("edit operation worked3!!!!");
                model.addAttribute("editUser", editUser);
               
                logger.info("all good!!!!");
                return "admin-users_manager-user-edit-id";
            } else {
            	
            	logger.info("the model attribute editUser is null !!!!");
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        return "redirect:/admin/users_manager";
    }
    
    @RequestMapping(value = "/admin/regcourse/{operation}/{course_id}/{id}", method = RequestMethod.GET)
    public String todoOperation2(@PathVariable("operation") String operation,
                                @PathVariable("id") int id, @PathVariable("course_id") int course_id, final RedirectAttributes redirectAttributes,
                                Model model) {

        logger.info("/user/operation: {} ", operation);
        if (operation.equals("trash")) {
        	registered_courses reg = regService.findById(id);
            if (reg != null) {
                //user.setStatus(Status.PASSIVE.getValue());
                regService.update(reg);
                redirectAttributes.addFlashAttribute("msg", "trash");
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        if (operation.equals("restore")) {
        	registered_courses reg = regService.findById(id);
            if (reg != null) {
                //user.setStatus(Status.ACTIVE.getValue());
                regService.update(reg);
                redirectAttributes.addFlashAttribute("msg", "active");
                redirectAttributes.addFlashAttribute("msgText", "Task " + reg.getUsername() + " Restored Successfully.");
            } else {
                redirectAttributes.addFlashAttribute("msg", "active_fail");
                redirectAttributes.addFlashAttribute("msgText", "Task Activation failed !!! Task:" + reg.getUsername());

            }
        } else if (operation.equals("delete")) {
            if (regService.delete(id)) {
            	logger.info("delete in controller 1");

                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Task deleted permanently");
            } else {
                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                
            }
        } else if (operation.equals("edit")) {
        	logger.info("edit operation worked!!!!");
        	registered_courses reqReg = regService.findById(id);
            logger.info("edit operation worked2!!!!");
            if (reqReg != null) {
            	logger.info("edit operation worked3!!!!");
                model.addAttribute("reqReg", reqReg);
                model.addAttribute("contextcourse", taskService.findById(course_id));
                logger.info("all good!!!!");
                return "admin_EditRegCourses";
            } else {
            	
            	logger.info("the model attribute editUser is null !!!!");
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        return "redirect:/admin/registered_courses";
    }

}
