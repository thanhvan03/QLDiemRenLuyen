/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Baothieu;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.Khoa;
import com.qldrl.pojo.User;
import com.qldrl.repositories.BaoThieuRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class BaoThieuRepositoryImpl implements BaoThieuRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Baothieu> getBaoThieuByHoatDong(Hoatdong hoatdong) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createQuery("SELECT bt FROM Baothieu bt WHERE bt.idHoatDong = :hoatdong", Baothieu.class)
                .setParameter("hoatdong", hoatdong)
                .getResultList();
    }

    @Override
    public void xoaBaoThieu(int baoThieuId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Baothieu b = this.getBaoThieuById(baoThieuId);
        if (b != null) {
            session.delete(b);
        }
    }

    @Override
    public Baothieu getBaoThieuById(int baoThieuId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(Baothieu.class, baoThieuId);
    }

    @Override
    public void taoBaoThieu(Baothieu baothieu) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.save(baothieu);
    }

    @Override
    public List<Baothieu> getBaoThieuByKhoa(int idKhoa) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Baothieu> cq = cb.createQuery(Baothieu.class);
        Root<Baothieu> root = cq.from(Baothieu.class);

        Join<Baothieu, User> userJoin = root.join("idUser");

        Join<User, Khoa> khoaJoin = userJoin.join("idKhoa");

        Predicate condition = cb.equal(khoaJoin.get("id"), idKhoa);

        cq.where(condition);

        return session.createQuery(cq).getResultList();
    }

}
