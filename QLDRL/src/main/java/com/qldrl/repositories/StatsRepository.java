/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories;

import java.util.Map;

/**
 *
 * @author DELL
 */
public interface StatsRepository {
    Map<String, Integer> thanhTichTheoKhoa(int hocKyId, int khoaId);
    Map<String, Integer> thanhTichTheoLop(int hocKyId, int lopHocId);
}
