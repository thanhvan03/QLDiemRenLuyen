/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services;

import java.util.Map;

/**
 *
 * @author DELL
 */
public interface StatsServices {
    public Map<String, Integer> thanhTichTheoKhoa(int hocKyId, int khoaId);
    public Map<String, Integer> thanhTichTheoLop(int hocKyId, int lopHocId);
}
