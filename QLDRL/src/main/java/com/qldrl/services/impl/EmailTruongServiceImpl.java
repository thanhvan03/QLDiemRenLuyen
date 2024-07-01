/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Emailtruong;
import com.qldrl.repositories.EmailTruongRepository;
import com.qldrl.services.EmailTruongService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class EmailTruongServiceImpl implements EmailTruongService {

    @Autowired
    private EmailTruongRepository emailRepo;

    @Override
    public List<Emailtruong> getEmailTruong() {
        return this.emailRepo.getEmailTruong();
    }

    @Override
    public Emailtruong getEmailById(int id) {
        return this.emailRepo.getEmailById(id);
    }

    @Override
    public void addOrUpdateEmail(Emailtruong e) {
        this.emailRepo.addOrUpdateEmail(e);
    }

    @Override
    public void deleteEmail(int id) {
        this.emailRepo.deleteEmail(id);
    }

    @Override
    public Emailtruong findByEmail(String email) {
        return this.emailRepo.findByEmail(email);
    }
}
