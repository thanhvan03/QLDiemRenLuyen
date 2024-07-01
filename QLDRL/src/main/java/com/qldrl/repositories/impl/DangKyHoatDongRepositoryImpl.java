/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.repositories.DangKyHoatDongRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class DangKyHoatDongRepositoryImpl implements DangKyHoatDongRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Dangkyhoatdong> getAllDangKyByHoatDong(Hoatdong hoatdong) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createQuery("SELECT d FROM Dangkyhoatdong d WHERE d.idHoatDong = :hoatdong", Dangkyhoatdong.class)
                .setParameter("hoatdong", hoatdong)
                .getResultList();
    }

    @Override
    public boolean kiemTraDangKy(int idUser, int idHoatDong) {
        Session session = sessionFactory.getObject().getCurrentSession();
        List<Dangkyhoatdong> results = session.createQuery("SELECT d FROM Dangkyhoatdong d WHERE d.idUser.id = :idUser AND d.idHoatDong.id = :idHoatDong", Dangkyhoatdong.class)
                .setParameter("idUser", idUser)
                .setParameter("idHoatDong", idHoatDong)
                .getResultList();
        if (results.isEmpty() == true) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Dangkyhoatdong getDangKySV(int idHoatDong, int userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            String hql = "FROM Dangkyhoatdong d WHERE d.idHoatDong.id = :idHoatDong AND d.idUser.id = :userId";
            Query<Dangkyhoatdong> query = session.createQuery(hql, Dangkyhoatdong.class);
            query.setParameter("idHoatDong", idHoatDong);
            query.setParameter("userId", userId);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setThamGia(Dangkyhoatdong d) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            d.setThamgia(true); // cập nhật thuộc tính thamgia
            session.saveOrUpdate(d); // lưu thay đổi vào cơ sở dữ liệu
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update thamgia status", e);
        }
    }

    
    
    ///Van
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Dangkyhoatdong> getDangKyHoatDongByUserId(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Dangkyhoatdong WHERE idUser = :userId";
        List<Dangkyhoatdong> result = s.createQuery(hql)
                .setParameter("userId", id)
                .getResultList();

        return result;
    }

    @Override
    public List<Integer> getHoatDongIdsByUserIdAndThamGiaTrue(int userId) {
        return entityManager.createQuery(
                "SELECT dh.idHoatDong FROM Dangkyhoatdong dh WHERE dh.idUser = :userId AND dh.thamgia = true")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void addDangKyHoatDong(Dangkyhoatdong d) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.save(d);
    }
}
