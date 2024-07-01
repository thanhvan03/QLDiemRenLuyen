/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.Hoatdong;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface DangKyHoatDongRepository {

    List<Dangkyhoatdong> getAllDangKyByHoatDong(Hoatdong hoatdong);

    boolean kiemTraDangKy(int idUser, int idHoatDong);

    Dangkyhoatdong getDangKySV(int idHoatDong, int userId);

    void setThamGia(Dangkyhoatdong d);
    

    ///Van
    List<Dangkyhoatdong> getDangKyHoatDongByUserId(int id);

    List<Integer> getHoatDongIdsByUserIdAndThamGiaTrue(int userId);

    void addDangKyHoatDong(Dangkyhoatdong d);
}
