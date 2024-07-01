/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.services.impl;

import com.qldrl.pojo.Thich;
import com.qldrl.repositories.ThichRepository;
import com.qldrl.services.ThichServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
////Van
@Service
public class ThichServicesImpl implements ThichServices{
    @Autowired
    private ThichRepository thichRepo;

    @Override
    public void addThich(Thich t) {
        this.thichRepo.addThich(t);
    }

    @Override
    public int countLikeOfActi(int hoatDongId) {
        return this.thichRepo.countLikeOfActi(hoatDongId);
    }

    @Override
    public Thich getThichUserByHoatDongId(int idUser, int idHoatDong) {
        return this.thichRepo.getThichUserByHoatDongId(idUser, idHoatDong);
    }

    @Override
    public void removeThich(int idUser, int idHoatDong) {
        this.thichRepo.removeThich(idUser, idHoatDong);
    }
}
