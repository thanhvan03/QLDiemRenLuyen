/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Baothieu;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.services.BaoThieuServices;
import com.qldrl.services.DangKyHoatDongServices;
import com.qldrl.services.DiemRenLuyenServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api")
public class ApiBaoThieuController {

    @Autowired
    private BaoThieuServices baothieuService;

    @Autowired
    private DiemRenLuyenServices drlServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private DangKyHoatDongServices dkhdServices;

    // Báo thiếu
    @PostMapping(path = "/baothieu/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile file, Principal principal) {
        Baothieu b = new Baothieu();
        b.setNgaybao(new Date());

        // Lấy thông tin người dùng hiện tại từ Principal
        String username = principal.getName();
        User currentUser = this.userServices.getUserByUsername(username);

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        b.setIdUser(currentUser);

        String activityIdString = params.get("idHoatDong");

        if (file != null && !file.isEmpty()) {
            b.setFile(file);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is required");
        }

        Integer activityId = Integer.valueOf(activityIdString);
        b.setIdHoatDong(new Hoatdong(activityId));

        this.baothieuService.taoBaoThieu(b);
    }

    // Duyệt báo thiếu
    @PostMapping("tlsv/duyetbaothieu/{baoThieuId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void duyetBaoThieu(HttpSession session, @PathVariable("baoThieuId") int baoThieuId, @PathVariable("userId") int userId) {
        Baothieu baothieu = baothieuService.getBaoThieuById(baoThieuId);
        if (baothieu != null && dkhdServices.kiemTraDangKy(userId, baothieu.getIdHoatDong().getId())) {
            User u = this.userServices.getUserById(userId);
            drlServices.taoDiemRenLuyen(baothieu.getIdHoatDong(), u);
        } else {
            // Nếu sinh viên chưa đăng ký hoạt động, trả về status code 400 (Bad Request)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sinh viên chưa đăng ký hoạt động hoặc không tồn tại.");
        }
    }

    // Lấy báo thiếu theo id
    @GetMapping(path = "/baothieu/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Baothieu> retrieve(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.baothieuService.getBaoThieuById(id), HttpStatus.OK);
    }

    // Xóa báo thiếu
    @DeleteMapping("tlsv/xoabaothieu/{baoThieuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void deleteBaoThieu(Model model, @PathVariable(value = "baoThieuId") int id) {
        this.baothieuService.xoaBaoThieu(id);
    }
}
