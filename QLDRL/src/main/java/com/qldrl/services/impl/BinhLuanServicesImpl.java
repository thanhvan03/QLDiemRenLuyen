/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.qldrl.services.impl;

import com.qldrl.pojo.Binhluan;
import com.qldrl.repositories.BinhLuanRepository;
import com.qldrl.services.BinhLuanServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
///Van
@Service
public class BinhLuanServicesImpl implements BinhLuanServices{
    @Autowired
    private BinhLuanRepository binhluanRepo;

    @Override
    public void addBinhLuan(Binhluan b) {
        this.binhluanRepo.addBinhLuan(b);
    }

    @Override
    public Binhluan getBinhLuanById(int id) {
        return this.binhluanRepo.getBinhLuanById(id);
    }

    @Override
    public void deleteBinhLuan(int id) {
        this.binhluanRepo.deleteBinhLuan(id);
    }

    @Override
    public List<Binhluan> getBinhLuanByHoatDongId(int idHoatDong) {
        return this.binhluanRepo.getBinhLuanByHoatDongId(idHoatDong);
    }

    @Override
    public List<Binhluan> getBinhLuanByHoatDongIdPage(int idHoatDong, int page, int size) {
        return this.binhluanRepo.getBinhLuanByHoatDongIdPage(idHoatDong, page, size);
    }
    
}

