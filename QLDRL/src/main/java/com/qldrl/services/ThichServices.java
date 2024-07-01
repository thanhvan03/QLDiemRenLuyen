/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import com.qldrl.pojo.Thich;

/**
 *
 * @author DELL
 */
//Van
public interface ThichServices {
    void addThich(Thich t);
     Thich getThichUserByHoatDongId(int idUser, int idHoatDong);
    void removeThich(int idUser, int idHoatDong);
    int countLikeOfActi(int hoatDongId);
}
