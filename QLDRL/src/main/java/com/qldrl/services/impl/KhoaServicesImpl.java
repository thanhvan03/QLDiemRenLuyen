/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Khoa;
import com.qldrl.pojo.Lophoc;
import com.qldrl.repositories.KhoaRepository;
import com.qldrl.services.KhoaServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class KhoaServicesImpl implements KhoaServices{
    @Autowired
    private KhoaRepository khoaRepo;

    @Override
    public List<Khoa> getAllKhoa() {
        return this.khoaRepo.getAllKhoa();
    }

    @Override
    public List<Lophoc> getAllLopHoc() {
        return this.khoaRepo.getAllLopHoc();
    }
    
}
