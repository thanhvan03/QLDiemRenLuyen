/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Binhluan;
import java.util.List;

/**
 *
 * @author DELL
 */
////Van 
public interface BinhLuanRepository {
    void addBinhLuan(Binhluan b);
    Binhluan getBinhLuanById(int id);
    void deleteBinhLuan(int id);
    List<Binhluan> getBinhLuanByHoatDongId(int idHoatDong);
    List<Binhluan> getBinhLuanByHoatDongIdPage(int idHoatDong, int page, int size);    
}
