/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import com.qldrl.pojo.Hocky;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface HocKyServices {
    List<Hocky> getHocKy();
    public List<Hocky> getHocKyByUser(int id);
}
