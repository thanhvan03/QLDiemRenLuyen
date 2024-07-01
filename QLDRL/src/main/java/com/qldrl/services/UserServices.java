/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import com.qldrl.pojo.User;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 *
 * @author DELL
 */
public interface UserServices extends UserDetailsService{
    User getUserById(int id);
    User getUserByMaSV(String maSV);
    User getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    List<User> getUserByLopHoc(int lopHocId);
    List<User> getUser();
    void addOrUpdateUser(User u);
    boolean authUser(String username, String password);
    List<User> getUsersByUserRole(String userRole);
}
