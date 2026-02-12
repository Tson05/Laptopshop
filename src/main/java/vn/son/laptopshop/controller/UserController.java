package vn.son.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.son.laptopshop.domain.User;
import vn.son.laptopshop.repository.UserRepository;
import vn.son.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> allUsers = this.userService.getAllUserByEmail("1@gmail.com");
        System.out.println(allUsers);
        String test = this.userService.handlHello();

        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "/admin/user/table-user";
    }

    @RequestMapping("/admin/user/create")
    public String geCreatetUserPage(Model model) {

        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)

    public String createUserPage(Model model, @ModelAttribute("newUser") User son) {

        this.userService.handlSaveUser(son);
        return "redirect:/admin/user";
    }

}
