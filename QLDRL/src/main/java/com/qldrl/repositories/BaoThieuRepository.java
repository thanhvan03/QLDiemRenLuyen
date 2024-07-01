/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Baothieu;
import com.qldrl.pojo.Hoatdong;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface BaoThieuRepository {
    List<Baothieu> getBaoThieuByHoatDong(Hoatdong hoatdong);
    Baothieu getBaoThieuById(int baoThieuId);
    void xoaBaoThieu(int baoThieuId); 
    void taoBaoThieu(Baothieu baothieu);
    List<Baothieu> getBaoThieuByKhoa(int idKhoa);
}
