/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Thich;

/**
 *
 * @author PC
 */
///Van
public interface ThichRepository {
    void addThich(Thich t);
    Thich getThichUserByHoatDongId(int idUser, int idHoatDong);
    void removeThich(int idUser, int idHoatDong);
    int countLikeOfActi(int hoatDongId);
}
