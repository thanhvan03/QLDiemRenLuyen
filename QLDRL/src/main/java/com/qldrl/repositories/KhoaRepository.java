/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import com.qldrl.pojo.Khoa;
import com.qldrl.pojo.Lophoc;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface KhoaRepository {
    List<Khoa> getAllKhoa();
    List<Lophoc> getAllLopHoc();
}
