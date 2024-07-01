    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.services.HoatDongServices;
import com.qldrl.services.HocKyServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DELL
 */
@Controller
public class HoatDongController {

    @Autowired
    private HoatDongServices hdServices;

    @Autowired
    private HocKyServices hkServices;
    
    @Autowired
    private UserServices userServices;

    @GetMapping("tlsv/hoatdongs")
    public String CreateView(Model model) {
        model.addAttribute("hoatdong", new Hoatdong());
        model.addAttribute("hockys", this.hkServices.getHocKy());
        return "hoatdong";
    }
    
    @PostMapping("tlsv/hoatdongs")
    public String createActivity(@ModelAttribute("hoatdong") @Valid Hoatdong hoatdong, 
            BindingResult result, Model model, Principal principal) {
        
        
        if (result.hasErrors()) {
            return "hoatdong";
        } else {
            try {
                String username = principal.getName();
                User currentUser = this.userServices.getUserByUsername(username);                
                hoatdong.setIdUser(currentUser);
                
                this.hdServices.addOrUpdate(hoatdong);
                return "redirect:/";
            } catch (Exception ex) {
                model.addAttribute("errorMessage", "Lỗi: " + ex.getMessage());
                model.addAttribute("hockys", hkServices.getHocKy());
                return "hoatdong";
            }
        }
    }

    @GetMapping("tlsv/hoatdongs/{hoatdongId}")
    public String updateView(Model model, @PathVariable(value = "hoatdongId") int id) {
        model.addAttribute("hoatdong", this.hdServices.getHoatDongById(id));
        model.addAttribute("hockys", this.hkServices.getHocKy());
        return "hoatdong";
    }

    @PostMapping("tlsv/hoatdongs/delete/{hoatdongId}")
    public String deleteHoatDong(@PathVariable(value = "hoatdongId") int id) {
        this.hdServices.xoaHoatDong(id);
        return "redirect:/";
    }
}
