/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.repositories.DangKyHoatDongRepository;
import com.qldrl.services.DangKyHoatDongServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DangKyHoatDongServicesImpl implements DangKyHoatDongServices {

    @Autowired
    private DangKyHoatDongRepository dangKyRepo;

    @Override
    public List<Dangkyhoatdong> getAllDangKyByHoatDong(Hoatdong hoatdong) {
        return this.dangKyRepo.getAllDangKyByHoatDong(hoatdong);
    }

    @Override
    public boolean kiemTraDangKy(int idUser, int idHoatDong) {
        return this.dangKyRepo.kiemTraDangKy(idUser, idHoatDong);
    }

    @Override
    public Dangkyhoatdong getDangKySV(int idHoatDong, int userId) {
        return this.dangKyRepo.getDangKySV(idHoatDong, userId);
    }

    @Override
    public void setThamGia(Dangkyhoatdong d) {
        this.dangKyRepo.setThamGia(d);
    }

    ///Van
    @Override
    public List<Dangkyhoatdong> getDangKyHoatDongByUserId(int id) {
        return this.dangKyRepo.getDangKyHoatDongByUserId(id);
    }

    @Override
    public List<Integer> getHoatDongIdsByUserIdAndThamGiaTrue(int userId) {
        return this.dangKyRepo.getHoatDongIdsByUserIdAndThamGiaTrue(userId);
    }

    @Override
    public void addDangKyHoatDong(Dangkyhoatdong d) {
        this.dangKyRepo.addDangKyHoatDong(d);
    }

    
}
