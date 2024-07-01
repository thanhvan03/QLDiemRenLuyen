/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.repositories.StatsRepository;
import com.qldrl.services.StatsServices;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class StatsServicesImpl implements StatsServices{

    @Autowired
    private StatsRepository statsRepo;

    @Override
    public Map<String, Integer> thanhTichTheoKhoa(int hocKyId, int khoaId) {
        return statsRepo.thanhTichTheoKhoa(hocKyId, khoaId);
    }

    @Override
    public Map<String, Integer> thanhTichTheoLop(int hocKyId, int lopHocId) {
        return statsRepo.thanhTichTheoLop(hocKyId, lopHocId);
    }
    
}
