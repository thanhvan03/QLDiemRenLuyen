/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import com.qldrl.pojo.Hoatdong;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface HoatDongServices {

    List<Hoatdong> getHoatDong();

    List<Hoatdong> getHoatDongPage(int page, int size);

    void addOrUpdate(Hoatdong hoatdong);

    Hoatdong getHoatDongById(int id);

    public void xoaHoatDong(int id);

    ///Van
    List<Hoatdong> getHoatDongsByUserId(int userId);

    List<Hoatdong> getHoatDongDaDangKy(int userId);

    List<Hoatdong> getHoatDongsByHocKy(int hocKyId);
    
    List<Hoatdong> getHoatDongDaThamGia(int userId);
    
    List<Hoatdong> getHoatDongDaThamGiaByHocKy(int userId, int hocKyId);
}
