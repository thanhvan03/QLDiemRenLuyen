/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.repositories.impl;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.Diemrenluyen;
import com.qldrl.pojo.Hoatdong;
import com.qldrl.pojo.User;
import com.qldrl.repositories.DiemRenLuyenRepository;
import java.util.Date;
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
public class DiemRenLuyenRepositoryImpl implements DiemRenLuyenRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void taoDiemRenLuyen(Hoatdong hoatdong, User user) {
        Session session = sessionFactory.getObject().getCurrentSession();

        // Kiểm tra 
        String hql = "FROM Diemrenluyen WHERE idHoatDong = :idHoatDong AND idUser = :idUser";
        Query<Diemrenluyen> query = session.createQuery(hql, Diemrenluyen.class);
        query.setParameter("idHoatDong", hoatdong);
        query.setParameter("idUser", user);

        Diemrenluyen diemRenLuyen = query.uniqueResult();

        if (diemRenLuyen == null) {
            diemRenLuyen = new Diemrenluyen();
            diemRenLuyen.setIdHoatDong(hoatdong);
            diemRenLuyen.setIdUser(user);
        }

        // Cập nhật các thuộc tính còn lại
        diemRenLuyen.setDiem(hoatdong.getDiem());
        diemRenLuyen.setNgaycapnhat(new Date());

        session.saveOrUpdate(diemRenLuyen);
    }

    @Override
    public int tinhTongDiemDieu1(int sinhVienId, int hocKyId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "SELECT d FROM Diemrenluyen d JOIN d.idHoatDong h WHERE d.idUser.id = :userId AND h.idHocKy.id = :hocKyId AND h.tieuchi = :tieuChi";
        Query query = session.createQuery(hql);
        query.setParameter("userId", sinhVienId);
        query.setParameter("hocKyId", hocKyId);
        query.setParameter("tieuChi", "Dieu 1");

        List<Diemrenluyen> diemRenLuyenList = query.getResultList();

        int totalDiemDieu1 = 0;
        for (Diemrenluyen diem : diemRenLuyenList) {
            totalDiemDieu1 += diem.getDiem();
        }
        if (totalDiemDieu1 >= 20) {
            totalDiemDieu1 = 20;
            return totalDiemDieu1;
        }
        return totalDiemDieu1;
    }

    @Override
    public int tinhTongDiemDieu2(int sinhVienId, int hocKyId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "SELECT d FROM Diemrenluyen d JOIN d.idHoatDong h WHERE d.idUser.id = :userId AND h.idHocKy.id = :hocKyId AND h.tieuchi = :tieuChi";
        Query query = session.createQuery(hql);
        query.setParameter("userId", sinhVienId);
        query.setParameter("hocKyId", hocKyId);
        query.setParameter("tieuChi", "Dieu 2");

        List<Diemrenluyen> diemRenLuyenList = query.getResultList();

        int totalDiemDieu2 = 0;
        for (Diemrenluyen diem : diemRenLuyenList) {
            totalDiemDieu2 += diem.getDiem();
        }
        if (totalDiemDieu2 >= 40) {
            totalDiemDieu2 = 40;
            return totalDiemDieu2;
        }
        return totalDiemDieu2;
    }

    @Override
    public int tinhTongDiemDieu3(int sinhVienId, int hocKyId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "SELECT d FROM Diemrenluyen d JOIN d.idHoatDong h WHERE d.idUser.id = :userId AND h.idHocKy.id = :hocKyId AND h.tieuchi = :tieuChi";
        Query query = session.createQuery(hql);
        query.setParameter("userId", sinhVienId);
        query.setParameter("hocKyId", hocKyId);
        query.setParameter("tieuChi", "Dieu 3");

        List<Diemrenluyen> diemRenLuyenList = query.getResultList();

        int totalDiemDieu3 = 0;
        for (Diemrenluyen diem : diemRenLuyenList) {
            totalDiemDieu3 += diem.getDiem();
        }
        if (totalDiemDieu3 >= 40) {
            totalDiemDieu3 = 40;
            return totalDiemDieu3;
        }
        return totalDiemDieu3;
    }

    @Override
    public int tinhTongDiemHocKy(int sinhVienId, int hocKyId) {
        int tongDiem = 0;
        int diemDieu1 = this.tinhTongDiemDieu1(sinhVienId, hocKyId);
        int diemDieu2 = this.tinhTongDiemDieu2(sinhVienId, hocKyId);
        int diemDieu3 = this.tinhTongDiemDieu3(sinhVienId, hocKyId);
        tongDiem = diemDieu1 + diemDieu2 + diemDieu3;
        return tongDiem;
    }

    @Override
    public boolean ktraDRL(int hoatDongId, int userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Diemrenluyen d WHERE d.idHoatDong.id = :hoatDongId AND d.idUser.id = :userId";
        Query<Diemrenluyen> query = session.createQuery(hql, Diemrenluyen.class);
        query.setParameter("hoatDongId", hoatDongId);
        query.setParameter("userId", userId);

        Diemrenluyen diemrenluyen = query.uniqueResult();

        return diemrenluyen != null;
    }
    
    @Override
    public int getDiemSVDatDuoc(int sinhVienId, int hoatDongId) {

        Query query = (Query) this.entityManager.createQuery(
                "SELECT d FROM Dangkyhoatdong d WHERE d.idUser.id = :userId AND d.idHoatDong.id = :hoatDongId");
        query.setParameter("userId", sinhVienId);
        query.setParameter("hoatDongId", hoatDongId);

        List<Dangkyhoatdong> results = query.getResultList();
        boolean thamGia = false;
        if (!results.isEmpty()) {
            for (Dangkyhoatdong dangky : results) {
                if (Boolean.TRUE.equals(dangky.getThamgia())) {
                    thamGia = true;
                    break;
                }
            }
        }

        if (thamGia) {
            // If student participated, fetch the points from Diemrenluyen
            Query diemQuery = (Query) entityManager.createQuery(
                    "SELECT d.diem FROM Diemrenluyen d WHERE d.idUser.id = :userId AND d.idHoatDong.id = :hoatDongId");
            diemQuery.setParameter("userId", sinhVienId);
            diemQuery.setParameter("hoatDongId", hoatDongId);
            Integer diem = (Integer) diemQuery.getSingleResult();
            return (diem != null) ? diem : 0;
        } else {
            // If student did not participate, return 0
            return 0;
        }
    }
}
