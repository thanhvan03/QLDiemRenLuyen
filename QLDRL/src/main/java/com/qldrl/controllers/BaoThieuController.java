/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

/**
 *
 * @author DELL
 */
import com.qldrl.pojo.Baothieu;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.services.BaoThieuServices;
import com.qldrl.services.KhoaServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BaoThieuController {

    @Autowired
    private BaoThieuServices baoThieuService;

    @Autowired
    private KhoaServices khoaServices;

    @GetMapping("tlsv/baothieu/{hoatdongId}")
    public String xemBaoThieu(Model model, @PathVariable("hoatdongId") int hoatdongId) {
        Hoatdong hoatdong = new Hoatdong();
        hoatdong.setId(hoatdongId);
        List<Baothieu> baoThieuList = baoThieuService.getBaoThieuByHoatDong(hoatdong);
        model.addAttribute("hoatdong", hoatdong);
        model.addAttribute("baoThieuList", baoThieuList);
        return "baothieu";
    }

    ////Xem danh sách bao thiếu theo Khoa 
    @GetMapping("tlsv/baothieukhoa")
    public String getBaoThieuList(Model model) {
        model.addAttribute("khoaList", this.khoaServices.getAllKhoa());
        return "baothieutheokhoa";
    }

    @GetMapping("tlsv/baothieukhoa/{idKhoa}")
    public String getBaoThieuKhoa(Model model, @PathVariable("idKhoa") int idKhoa) {
        List<Baothieu> baoThieuList = baoThieuService.getBaoThieuByKhoa(idKhoa);
        model.addAttribute("baoThieuList", baoThieuList);
            
        model.addAttribute("khoaList", this.khoaServices.getAllKhoa());
        return "baothieutheokhoa";
    }
}
