/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Thich;
import com.qldrl.repositories.ThichRepository;
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
public class ThichRepositoryImpl implements ThichRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addThich(Thich t) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(t);
    }

    @Override
    public int countLikeOfActi(int hoatDongId) {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "SELECT COUNT(t.id) FROM Thich t WHERE t.idHoatDong.id = :hoatDongId";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("hoatDongId", hoatDongId);

        return query.uniqueResult().intValue();
    }

    @Override
    public Thich getThichUserByHoatDongId(int idUser, int idHoatDong) {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "SELECT t FROM Thich t WHERE t.idHoatDong.id = :hoatDongId AND t.idUser.id = :idUser";
        Query q = session.createQuery(hql);
        q.setParameter("hoatDongId", idHoatDong);
        q.setParameter("idUser", idUser);
        return (Thich) q.uniqueResult();
    }

    @Override
    public void removeThich(int idUser, int idHoatDong) {
        Session session = this.factory.getObject().getCurrentSession();
        Thich t = this.getThichUserByHoatDongId(idUser, idHoatDong);
        if(t != null) 
            session.delete(t);
    }
}
