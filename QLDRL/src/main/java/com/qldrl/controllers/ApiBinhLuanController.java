/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Binhluan;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.services.BinhLuanServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api")
public class ApiBinhLuanController {

    @Autowired
    private BinhLuanServices binhluanService;

    @Autowired
    private UserServices userServices;

    //bình luận
    @PostMapping(path = "/comment/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, Principal principal) {
        Binhluan b = new Binhluan();
        b.setNgaybinhluan(new Date());

        // Lấy thông tin người dùng hiện tại từ Principal
        String username = principal.getName();
        User currentUser = this.userServices.getUserByUsername(username);

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        b.setIdUser(currentUser);

        b.setNoidung(params.get("noidung"));

        String activityIdString = params.get("idHoatDong");
        Integer activityId = Integer.valueOf(activityIdString);
        b.setIdHoatDong(new Hoatdong(activityId));

        this.binhluanService.addBinhLuan(b);
    }

    //xóa bình luận
    @DeleteMapping("comments/delete/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(value = "commentId") int id) {
        this.binhluanService.deleteBinhLuan(id);
    }

    @GetMapping(path = "comments/{activityId}", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<List<Binhluan>> listComment(@PathVariable(value = "activityId") int id) {
        List<Binhluan> b = this.binhluanService.getBinhLuanByHoatDongId(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping(path = "comments/page/{activityId}", produces = 
        MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<List<Binhluan>> listComment(
            @PathVariable(value = "activityId") int id,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        List<Binhluan> comments = this.binhluanService.getBinhLuanByHoatDongIdPage(id, page, 8);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
