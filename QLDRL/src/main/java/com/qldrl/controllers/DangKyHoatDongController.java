/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.services.DangKyHoatDongServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author DELL
 */
@Controller
public class DangKyHoatDongController {
    @Autowired
    private DangKyHoatDongServices dkServices;
    
    @GetMapping("tlsv/danhsachdangky/{hoatdongId}")
    public String xemDanhSachDangKy(Model model, @PathVariable("hoatdongId") int hoatdongId) {
        Hoatdong hoatdong = new Hoatdong();
        hoatdong.setId(hoatdongId);
        List<Dangkyhoatdong> dsDangKy = dkServices.getAllDangKyByHoatDong(hoatdong);
        model.addAttribute("hoatdong", hoatdong);
        model.addAttribute("dsDangKy", dsDangKy);
        return "danhsachdangky";
    }
}
