/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.cloudinary.http44.api.Response;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.Thich;
import com.qldrl.pojo.User;
import com.qldrl.services.ThichServices;
import com.qldrl.services.UserServices;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
public class ApiThichController {

    @Autowired
    private ThichServices thichService;

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

    // Like
    @PostMapping(path = "/like/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, Principal principal) {
        Thich t = new Thich();
        t.setNgaythich(new Date());

        User currentUser = getCurrentUser(principal);

        t.setIdUser(currentUser);

        String activityIdString = params.get("idHoatDong");
        Integer activityId = Integer.valueOf(activityIdString);
        t.setIdHoatDong(new Hoatdong(activityId));

        this.thichService.addThich(t);
    }

    //Đếm số lượt like của một hoạt động
    @GetMapping("/like/count/{hoatDongId}")
    @CrossOrigin
    public ResponseEntity<Integer> getTotalLikes(@PathVariable("hoatDongId") int hoatDongId) {
        int totalLikes = this.thichService.countLikeOfActi(hoatDongId);
        return ResponseEntity.ok(totalLikes);
    }

    @DeleteMapping("like/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void deleteLike(@RequestParam Map<String, String> params, Principal principal, HttpServletRequest request) {
        System.out.println("Received parameters: " + params); // Ghi log các tham số nhận được

        String activityIdString = params.get("idHoatDong");
        if (activityIdString == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activity ID is required");
        }
        /// Chuyển lại thành Int
        Integer activityId = Integer.valueOf(activityIdString);

        User currentUser = getCurrentUser(principal);

        this.thichService.removeThich(currentUser.getId(), activityId);
    }

    @GetMapping(path = "/like/{idHoatDong}", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<Thich> getThich(@PathVariable(value = "idHoatDong") int idHoatDong, Principal principal) {
        User currentUser = getCurrentUser(principal);
        return ResponseEntity.ok(this.thichService.getThichUserByHoatDongId(currentUser.getId(), idHoatDong));
    }
}
