/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Emailtruong;
import com.qldrl.services.EmailTruongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author PC
 */
@Controller
public class EmailTruongController {
    @Autowired
    private EmailTruongService emailService;
    
    @GetMapping("emails")
    public String index(Model model) {
        model.addAttribute("emails", this.emailService.getEmailTruong());

        return "emails";
    }
    
    @GetMapping("emails/add-email")
    public String addEmailView(Model model) {
        model.addAttribute("email", new Emailtruong());
        return "add-email";
    }
    
    @PostMapping("emails/add-email")
    public String addEmail(@ModelAttribute(value = "email") Emailtruong e) {
//        if (!rs.hasErrors()) {
            try {
                this.emailService.addOrUpdateEmail(e);
                return "redirect:/emails";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
//        }
        return "add-email";
    }
    
    @GetMapping("emails/{emailId}")
    public String updateUserView(Model model, @PathVariable(value = "emailId") int id){
        model.addAttribute("email", this.emailService.getEmailById(id));
        return "add-email";
    }
}
