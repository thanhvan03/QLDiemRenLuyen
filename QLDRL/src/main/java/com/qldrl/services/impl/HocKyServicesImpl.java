/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Hocky;
import com.qldrl.repositories.HoatDongRepository;
import com.qldrl.repositories.HocKyRerpository;
import com.qldrl.services.HocKyServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class HocKyServicesImpl implements HocKyServices{
    @Autowired
    private HocKyRerpository hockyRepository;
    
    @Override
    public List<Hocky> getHocKy() {
        return hockyRepository.getHocKy();
    }

    @Override
    public List<Hocky> getHocKyByUser(int id) {
        return this.hockyRepository.getHocKyByUser(id);
    }
}
