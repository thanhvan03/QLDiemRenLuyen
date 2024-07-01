/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;

/**
 *
 * @author DELL
 */
public interface DiemRenLuyenRepository {

    void taoDiemRenLuyen(Hoatdong hoatdong, User user);

     // Tính tổng điểm rèn luyện điều 1 của sinh viên trong một kỳ
    int tinhTongDiemDieu1(int sinhVienId, int hocKyId);
    
     // Tính tổng điểm rèn luyện điều 2 của sinh viên trong một kỳ
    int tinhTongDiemDieu2(int sinhVienId, int hocKyId);
    
     // Tính tổng điểm rèn luyện điều 3 của sinh viên trong một kỳ
    int tinhTongDiemDieu3(int sinhVienId, int hocKyId);
    
    int tinhTongDiemHocKy(int sinhVienId, int hocKyId);
    
    boolean ktraDRL(int hoatDongId, int userId);
    
    public int getDiemSVDatDuoc(int sinhVienId, int hoatDongId);
}
