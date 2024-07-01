/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.services.DangKyHoatDongServices;
import com.qldrl.services.HoatDongServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api")
public class ApiDangKyHoatDongController {

    @Autowired
    private DangKyHoatDongServices dkhdService;

    @Autowired
    private HoatDongServices hoatdongService;

    @Autowired
    private UserServices userServices;

    // Đăng ký hoạt động
    @PostMapping(path = "/registry/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    public ResponseEntity<?> create(@RequestParam Map<String, String> params, Principal principal) {
        try {
            Dangkyhoatdong d = new Dangkyhoatdong();

            // Lấy thông tin người dùng hiện tại từ Principal
            String username = principal.getName();
            User currentUser = this.userServices.getUserByUsername(username);
            
            String activityIdString = params.get("idHoatDong");
            Integer activityId = Integer.valueOf(activityIdString);

            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
            
            if (this.dkhdService.kiemTraDangKy(currentUser.getId(), activityId) == true) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sinh Vien Da Dang Ky");
            }
            
            d.setIdHoatDong(new Hoatdong(activityId));
            d.setIdUser(currentUser);
            d.setThamgia(Boolean.FALSE);            

            Hoatdong h = hoatdongService.getHoatDongById(activityId);

            Date ngayDangKy = new Date(); // Lấy ngày hiện tại
            if (ngayDangKy.before(h.getNgaytao()) || ngayDangKy.after(h.getNgaybatdau())) {
                return ResponseEntity.badRequest().body("Thời gian đăng ký hoạt động không hợp lệ.");
            }

            d.setNgaydangky(ngayDangKy);

            this.dkhdService.addDangKyHoatDong(d);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Id hoạt động không hợp lệ.");
        }
    }
}
