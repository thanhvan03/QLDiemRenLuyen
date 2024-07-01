/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Hocky;
import com.qldrl.pojo.User;
import com.qldrl.services.HocKyServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api")
public class ApiHocKyController {
    
    @Autowired
    private HocKyServices hockyService;
    
    @Autowired
    private UserServices userServices;
    
    private User getCurrentUser(Principal principal) {
        String username = principal.getName();
        User currentUser = this.userServices.getUserByUsername(username);

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        return currentUser;
    }
    
    @GetMapping("/hocky")
    @CrossOrigin
    public ResponseEntity<List<Hocky>> list(){
        return new ResponseEntity<>(this.hockyService.getHocKy(), HttpStatus.OK);
    }
    
    @GetMapping("/hockydathamgia/")
    @CrossOrigin
    public ResponseEntity<List<Hocky>> getHocKyDaThamGia(Principal p) {
        User u = getCurrentUser(p);
        List<Hocky> hocKyList = hockyService.getHocKyByUser(u.getId());
        return new ResponseEntity<>(hocKyList, HttpStatus.OK);
    }
}
