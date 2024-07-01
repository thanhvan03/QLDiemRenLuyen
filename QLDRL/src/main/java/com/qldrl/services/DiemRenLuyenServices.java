/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import com.qldrl.pojo.Diemrenluyen;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface DiemRenLuyenServices {

    void taoDiemRenLuyen(Hoatdong hoatdong, User user);

    int tinhTongDiemDieu1(int sinhVienId, int hocKyId);

    int tinhTongDiemDieu2(int sinhVienId, int hocKyId);

    int tinhTongDiemDieu3(int sinhVienId, int hocKyId);

    int tinhTongDiemHocKy(int sinhVienId, int hocKyId);

    boolean ktraDRL(int hoatDongId, int userId);
    
    int getDiemSVDatDuoc(int sinhVienId, int hoatDongId);
}
