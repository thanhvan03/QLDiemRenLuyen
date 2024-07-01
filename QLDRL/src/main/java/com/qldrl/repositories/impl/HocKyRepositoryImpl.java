/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Hocky;
import com.qldrl.repositories.HocKyRerpository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class HocKyRepositoryImpl implements HocKyRerpository{
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @PersistenceContext
    private EntityManager entityManager;
     
    @Override
    public List<Hocky> getHocKy() {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createQuery("FROM Hocky", Hocky.class).getResultList();
    }
    
    @Override
    public List<Hocky> getHocKyByUser(int id) {

        String jpql = "SELECT DISTINCT h FROM Hocky h " +
                      "JOIN h.hoatdongSet hd " +
                      "JOIN hd.dangkyhoatdongSet dk " +
                      "WHERE dk.idUser.id = :userId";

        return entityManager.createQuery(jpql, Hocky.class)
                            .setParameter("userId", id)
                            .getResultList();
    }
}
