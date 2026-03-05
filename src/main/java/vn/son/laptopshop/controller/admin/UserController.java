package vn.son.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import vn.son.laptopshop.domain.User;
import vn.son.laptopshop.service.UploadService;
import vn.son.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;

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
        return "/admin/user/show";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "/admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String geCreatetUserPage(Model model) {

        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")

    public String createUserPage(Model model, @ModelAttribute("newUser") User son,
            @RequestParam("hoidanitFile") MultipartFile file) {

        String avatar = this.uploadService.handlSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(son.getPassword());
        son.setAvatar(avatar);
        son.setPassword(hashPassword);
        son.setRole(this.userService.getRoleByName(son.getRole().getName()));
        this.userService.handlSaveUser(son);

        // this.userService.handlSaveUser(son);

        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User son) {
        User currentUser = this.userService.getUserById(son.getId());
        if (currentUser != null) {
            currentUser.setAddress(son.getAddress());
            currentUser.setFullName(son.getFullName());
            currentUser.setPhone(son.getPhone());
            this.userService.handlSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User son) {
        this.userService.deleteUser(son.getId());
        return "redirect:/admin/user";
    }

}
