/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Emailtruong;
import com.qldrl.repositories.EmailTruongRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
@Repository
@Transactional
public class EmailTruongRepositoryImpl implements EmailTruongRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Emailtruong> getEmailTruong() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Emailtruong.findAll");
        return q.getResultList();
    }

    @Override
    public void addOrUpdateEmail(Emailtruong e) {
        Session s = this.factory.getObject().getCurrentSession();
//        if(e.getId()> 0)
//            s.update(e);
//        else
//            s.save(e);
        s.saveOrUpdate(e);
    }

    @Override
    public Emailtruong getEmailById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Emailtruong.class, id);
    }

    @Override
    public void deleteEmail(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Emailtruong e = this.getEmailById(id);
        s.delete(e);
    }

    @Override
    public Emailtruong findByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Emailtruong WHERE email = :email", Emailtruong.class);
        q.setParameter("email", email);
        return (Emailtruong) q.getSingleResult();
    }
}
