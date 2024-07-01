/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Hoatdong;
import com.qldrl.repositories.HoatDongRepository;
import com.qldrl.services.HoatDongServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class HoatDongServicesImpl implements HoatDongServices {

    @Autowired
    private HoatDongRepository hoatDongRepository;

    @Override
    public void addOrUpdate(Hoatdong hoatdong) {
        hoatDongRepository.addOrUpdate(hoatdong);
    }

    @Override
    public List<Hoatdong> getHoatDong() {
        return hoatDongRepository.getHoatDong();
    }

    @Override
    public List<Hoatdong> getHoatDongPage(int page, int size) {
        return hoatDongRepository.getHoatDongPage(page, size);
    }

    @Override
    public Hoatdong getHoatDongById(int id) {
        return this.hoatDongRepository.getHoatDongById(id);
    }

    @Override
    public void xoaHoatDong(int id) {
        this.hoatDongRepository.xoaHoatDong(id);
    }

    ///Van
    @Override
    public List<Hoatdong> getHoatDongsByUserId(int userId) {
        return this.hoatDongRepository.getHoatDongsByUserId(userId);
    }

    @Override
    public List<Hoatdong> getHoatDongDaDangKy(int userId) {
        return this.hoatDongRepository.getHoatDongDaDangKy(userId);
    }

    @Override
    public List<Hoatdong> getHoatDongsByHocKy(int hocKyId) {
        return this.hoatDongRepository.getHoatDongsByHocKy(hocKyId);
    }

    @Override
    public List<Hoatdong> getHoatDongDaThamGia(int userId) {
        return this.hoatDongRepository.getHoatDongDaThamGia(userId);
    }

    @Override
    public List<Hoatdong> getHoatDongDaThamGiaByHocKy(int userId, int hocKyId) {
        return this.hoatDongRepository.getHoatDongDaThamGiaByHocKy(userId, hocKyId);
    }
}
