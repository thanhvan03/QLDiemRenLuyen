/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.repositories.DiemRenLuyenRepository;
import com.qldrl.services.DiemRenLuyenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DiemRenLuyenServicesImpl implements DiemRenLuyenServices {
    @Autowired
    private DiemRenLuyenRepository diemRenLuyenRepository;
    
    @Override
    public void taoDiemRenLuyen(Hoatdong hoatdong, User user) {
        diemRenLuyenRepository.taoDiemRenLuyen(hoatdong, user);
    }
    
    @Override
    public int tinhTongDiemDieu1(int sinhVienId, int hocKyId) { 
        return diemRenLuyenRepository.tinhTongDiemDieu1(sinhVienId, hocKyId);
    }
    
    @Override
    public int tinhTongDiemDieu2(int sinhVienId, int hocKyId) {
        return diemRenLuyenRepository.tinhTongDiemDieu2(sinhVienId, hocKyId);
    }
    
    @Override
    public int tinhTongDiemDieu3(int sinhVienId, int hocKyId) {
        return diemRenLuyenRepository.tinhTongDiemDieu3(sinhVienId, hocKyId);
    }
    
    @Override
    public int tinhTongDiemHocKy(int i, int i1) {
        return this.diemRenLuyenRepository.tinhTongDiemHocKy(i1, i1);
    }
    
    @Override
    public boolean ktraDRL(int hoatDongId, int userId) {
        return this.diemRenLuyenRepository.ktraDRL(hoatDongId, userId);
    }

    @Override
    public int getDiemSVDatDuoc(int sinhVienId, int hoatDongId) {
        return this.diemRenLuyenRepository.getDiemSVDatDuoc(sinhVienId, hoatDongId);
    }
}
