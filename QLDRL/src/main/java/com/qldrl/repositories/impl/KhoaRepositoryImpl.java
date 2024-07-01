/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Khoa;
import com.qldrl.pojo.Lophoc;
import com.qldrl.repositories.KhoaRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class KhoaRepositoryImpl implements KhoaRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Khoa> getAllKhoa() {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createQuery("FROM Khoa", Khoa.class).getResultList();
    }
    
    @Override
    public List<Lophoc> getAllLopHoc() {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createNamedQuery("Lophoc.findAll").getResultList();
    }

}
