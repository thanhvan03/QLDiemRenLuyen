/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.User;
import com.qldrl.services.KhoaServices;
import com.qldrl.services.UserServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@Controller
//@ControllerAdvice
public class UserController {

    @Autowired
    private UserServices userService;
    @Autowired
    private KhoaServices khoaService;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("khoa", this.khoaService.getAllKhoa());
    }

    @GetMapping("users")
    public String index(Model model, @RequestParam(name = "userRole", required = false) String userRole) {
//        model.addAttribute("users", this.userService.getUser());
//        return "users";
        List<User> users;
        if (userRole != null) {
            users = this.userService.getUsersByUserRole(userRole);
        } else {
            users = this.userService.getUser();
        }
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("users/add-user")
    public String addUserView(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("users/add-user")
    public String addUser(@ModelAttribute(value = "user")  User u) {
            try {
                this.userService.addOrUpdateUser(u);
                return "redirect:/users";
            } catch (Exception ex) {
                System.out.println("Lỗi");
            }
        return "add-user";
    }
    
    @GetMapping("users/{userId}")
    public String updateUserView(Model model, @PathVariable(value = "userId") int id){
        model.addAttribute("user", this.userService.getUserById(id));
        return "add-user";
    }
}
