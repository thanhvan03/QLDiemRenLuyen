
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qldrl.pojo.Baothieu;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.repositories.BaoThieuRepository;
import com.qldrl.services.BaoThieuServices;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class BaoThieuServicesImpl implements BaoThieuServices{

    @Autowired
    private Cloudinary cloudinary;
        @Autowired
    private BaoThieuRepository baoThieuRepo;

    @Override
    public List<Baothieu> getBaoThieuByHoatDong(Hoatdong hoatdong) {
        return this.baoThieuRepo.getBaoThieuByHoatDong(hoatdong);
    }

    @Override
    public void xoaBaoThieu(int baoThieuId) {
        this.baoThieuRepo.xoaBaoThieu(baoThieuId);
    }

    @Override
    public Baothieu getBaoThieuById(int baoThieuId) {
        return this.baoThieuRepo.getBaoThieuById(baoThieuId);
    }



    @Override
    public List<Baothieu> getBaoThieuByKhoa(int idKhoa) {
        return this.baoThieuRepo.getBaoThieuByKhoa(idKhoa);
    }

    @Override
    public void taoBaoThieu(Baothieu b) {
        if(!b.getFile().isEmpty()){
            try {
                Map res = this.cloudinary.uploader().upload(b.getFile().getBytes(), ObjectUtils.asMap("resources_type", "auto"));
                b.setMinhchung(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(BaoThieuServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        this.baoThieuRepo.taoBaoThieu(b);
    }
    
}
