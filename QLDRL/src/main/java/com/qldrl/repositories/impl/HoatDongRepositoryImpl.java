/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Hoatdong;
import com.qldrl.repositories.HoatDongRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
public class HoatDongRepositoryImpl implements HoatDongRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public List<Hoatdong> getHoatDong() {
//        Session s = this.sessionFactory.getObject().getCurrentSession();
//        Query q = s.createNamedQuery("Hoatdong.findAll");
//        return q.getResultList();
//    }
    @Override
    public List<Hoatdong> getHoatDong() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        // Sử dụng HQL với ORDER BY
        Query<Hoatdong> q = s.createQuery("FROM Hoatdong h ORDER BY h.id DESC", Hoatdong.class);
        return q.getResultList();
    }

    @Override
    public List<Hoatdong> getHoatDongPage(int page, int size) {
        Session session = sessionFactory.getObject().getCurrentSession();
        int offset = page * size;
        return session.createQuery("FROM Hoatdong ORDER BY id DESC", Hoatdong.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public void addOrUpdate(Hoatdong hoatdong) {
        Session session = sessionFactory.getObject().getCurrentSession();
        if (hoatdong.getNgaytao() == null) {
            hoatdong.setNgaytao(new Date());
        }
//        if (hoatdong.getId() > 0)
//            session.update(hoatdong);
//        else {
//            session.save(hoatdong);
//        }
        session.saveOrUpdate(hoatdong);
    }

    @Override
    public Hoatdong getHoatDongById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(Hoatdong.class, id);
    }

    @Override
    public void xoaHoatDong(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Hoatdong hd = this.getHoatDongById(id);
        if (hd != null) {
            session.delete(hd);
        }
    }

    ///Van
    @Override
    public List<Hoatdong> getHoatDongsByUserId(int userId) {
        return entityManager.createQuery("SELECT dh FROM Hoatdong dh "
                + "JOIN Dangkyhoatdong dk ON dh.id = dk.idHoatDong "
                + "WHERE dk.idUser = :userId", Hoatdong.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Hoatdong> getHoatDongDaDangKy(int userId) {
        TypedQuery<Hoatdong> query = entityManager.createQuery(
                "SELECT d.idHoatDong FROM Dangkyhoatdong d WHERE d.idUser.id = :userId", Hoatdong.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Hoatdong> getHoatDongsByHocKy(int hocKyId) {
        TypedQuery<Hoatdong> query = entityManager.createQuery(
                "SELECT h FROM Hoatdong h WHERE h.idHocKy.id = :hocKyId", Hoatdong.class);
        query.setParameter("hocKyId", hocKyId);
        return query.getResultList();
    }

    @Override
    public List<Hoatdong> getHoatDongDaThamGia(int userId) {
        TypedQuery<Hoatdong> query = entityManager.createQuery(
                "SELECT d.idHoatDong FROM Dangkyhoatdong d WHERE d.idUser.id = :userId AND d.thamgia = true", Hoatdong.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Hoatdong> getHoatDongDaThamGiaByHocKy(int userId, int hocKyId) {
        TypedQuery<Hoatdong> query = entityManager.createQuery(
                "SELECT DISTINCT hd FROM Hoatdong hd "
                + "JOIN hd.dangkyhoatdongSet dh "
                + "WHERE dh.idUser.id = :userId "
                + "AND hd.idHocKy.id = :hocKyId", Hoatdong.class);

        query.setParameter("userId", userId);
        query.setParameter("hocKyId", hocKyId);

        return query.getResultList();
    }
}
