/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.services.HoatDongServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiHoatDongController {

    @Autowired
    private HoatDongServices hdServices;
    
    @Autowired
    private UserServices userServices;

    @DeleteMapping("tlsv/xoahoatdong/{hoatdongId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void deleteHoatDong(Model model, @PathVariable(value = "hoatdongId") int id) {
        this.hdServices.xoaHoatDong(id);
    }

    @GetMapping("/activities/")
    @CrossOrigin
    public ResponseEntity<List<Hoatdong>> list() {
        return new ResponseEntity<>(this.hdServices.getHoatDong(), HttpStatus.OK);
    }

    @GetMapping("/activities/page")
    @CrossOrigin
    public ResponseEntity<List<Hoatdong>> getActivitiesByPage(
            @RequestParam(defaultValue = "0") int page) {
        List<Hoatdong> hoatDongs = hdServices.getHoatDongPage(page, 5);
        return new ResponseEntity<>(hoatDongs, HttpStatus.OK);
    }

    //Xem chi tiết hoạt động
    @GetMapping(path = "/activities/{activityId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Hoatdong> retrieve(@PathVariable(value = "activityId") int id) {
        return new ResponseEntity<>(this.hdServices.getHoatDongById(id), HttpStatus.OK);
    }

    private User getCurrentUser(Principal principal) {
        String username = principal.getName();
        User currentUser = this.userServices.getUserByUsername(username);

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        return currentUser;
    }
    
    //xem các hoạt động user đã tham gia
    @GetMapping("/hoatdongdadangky")
    @CrossOrigin
    public ResponseEntity<List<Hoatdong>> listdadangky(Principal p){
        User u = getCurrentUser(p);
        return new ResponseEntity<>(this.hdServices.getHoatDongDaDangKy(u.getId()), HttpStatus.OK);
    }
    
    @GetMapping("/hoatdongdathamgia")
    @CrossOrigin
    public ResponseEntity<List<Hoatdong>> listdathamgia(Principal p){
        User u = getCurrentUser(p);
        return new ResponseEntity<>(this.hdServices.getHoatDongDaThamGia(u.getId()), HttpStatus.OK);
    }
    
    @GetMapping(path = "/activities-hocky/{hocKyId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<Hoatdong>> xemchitietbyhocky(@PathVariable(value = "hocKyId") int id) {
        
        return new ResponseEntity<>(this.hdServices.getHoatDongsByHocKy(id), HttpStatus.OK);
    }
    
    //xem các hoạt động user đã tham gia  theo học kỳ
    @GetMapping(path = "/hoatdongdathamgia/{hocKyId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<Hoatdong>> xemhoatdongdathamgia(Principal p, @PathVariable("hocKyId") int idHocKy) {     
        User u = getCurrentUser(p);
        return new ResponseEntity<>(this.hdServices.getHoatDongDaThamGiaByHocKy(u.getId(), idHocKy), HttpStatus.OK);
    }
}
