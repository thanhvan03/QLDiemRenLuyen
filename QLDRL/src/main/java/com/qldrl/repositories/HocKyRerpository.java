/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Hocky;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface HocKyRerpository {
    List<Hocky> getHocKy();
    public List<Hocky> getHocKyByUser(int id);
}
