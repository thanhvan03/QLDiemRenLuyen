/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.services.HoatDongServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author PC
 */
@Controller
public class HomeController {
    
    @Autowired
    private HoatDongServices hdServices;   
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("hd", this.hdServices.getHoatDong());
        return "danhsachhoatdong";
    }
}
