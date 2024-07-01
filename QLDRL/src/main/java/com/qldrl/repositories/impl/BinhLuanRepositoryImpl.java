/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Binhluan;
import com.qldrl.repositories.BinhLuanRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
///Van
@Repository
@Transactional
public class BinhLuanRepositoryImpl implements BinhLuanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addBinhLuan(Binhluan b) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(b);
    }

    @Override
    public Binhluan getBinhLuanById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Binhluan.class, id);
    }

    @Override
    public void deleteBinhLuan(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Binhluan b = this.getBinhLuanById(id);
        s.delete(b);
    }

    @Override
    public List<Binhluan> getBinhLuanByHoatDongId(int idHoatDong) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Binhluan b WHERE b.idHoatDong.id = :idHoatDong ORDER BY b.id DESC";
        Query<Binhluan> query = s.createQuery(hql, Binhluan.class);
        query.setParameter("idHoatDong", idHoatDong);
        return query.getResultList();
    }

    @Override
    public List<Binhluan> getBinhLuanByHoatDongIdPage(int idHoatDong, int page, int size) {
        Session session = this.factory.getObject().getCurrentSession();
        int offset = page * size;

        Query query = session.createQuery("FROM Binhluan b WHERE b.idHoatDong.id = :idHoatDong ORDER BY b.id DESC");
        query.setParameter("idHoatDong", idHoatDong);
        query.setFirstResult(offset);
        query.setMaxResults(size);

        return query.getResultList();
    }
}
