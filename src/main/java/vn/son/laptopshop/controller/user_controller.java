package vn.son.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.son.laptopshop.service.user_service;

import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// public class user_controller {

//     @RequestMapping("/")

//     public String getHomePage() {
//         return "hello from controller";
//     }
// }

@RestController
public class user_controller {

    private user_service UserService;

    public user_controller(user_service userService) {
        this.UserService = userService;
    }

    @GetMapping("")
    public String gethomePage() {
        return this.UserService.handlHello();
    }

}
