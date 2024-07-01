/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Emailtruong;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PC
 */
public interface EmailTruongRepository {
    List<Emailtruong> getEmailTruong();
    void addOrUpdateEmail(Emailtruong e);
    Emailtruong getEmailById(int id);
    void deleteEmail(int id);
    Emailtruong findByEmail(String email);
}
