/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.services.EmailTruongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
public class ApiEmailTruongController {
    @Autowired
    private EmailTruongService emailService;
    
    //xóa email
    @DeleteMapping("/emails/{emailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmail(Model model, @PathVariable(value = "emailId") int id){
        this.emailService.deleteEmail(id);
    }
}
