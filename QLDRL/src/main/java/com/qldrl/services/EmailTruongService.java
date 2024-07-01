/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import com.qldrl.pojo.Emailtruong;
import java.util.List;

/**
 *
 * @author PC
 */
public interface EmailTruongService {
    List<Emailtruong> getEmailTruong();
    void addOrUpdateEmail(Emailtruong e);
    Emailtruong getEmailById(int id);
    void deleteEmail(int id);
    Emailtruong findByEmail(String email);
}
