/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author DELL
 */
public interface UserRepository {
    User getUserById(int id);
    User getUserByMaSV(String maSV);
    User getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    boolean authUser(String username, String password);
    List<User> getUserByLopHoc(int lopHocId);
    List<User> getUser();
    void addOrUpdateUser(User u);
    List<User> getUsersByUserRole(String userRole);
}
