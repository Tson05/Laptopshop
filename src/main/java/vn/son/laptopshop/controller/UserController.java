package vn.son.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.son.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage() {
        String test = this.userService.handlHello();
        return "hello";
    }

}

// @Controller
// public class user_controller {

// @RequestMapping("/")

// public String getHomePage() {
// return "hello from controller";
// }
// }

// @RestController
// public class UserController {

// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("")
// public String gethomePage() {
// String test = this.userService.handlHello();
// return "hello.jsp";
// }

// }
