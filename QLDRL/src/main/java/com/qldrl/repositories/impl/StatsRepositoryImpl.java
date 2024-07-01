package com.qldrl.repositories.impl;

import com.qldrl.pojo.User;
import com.qldrl.repositories.StatsRepository;
import com.qldrl.services.DiemRenLuyenServices;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private DiemRenLuyenServices drlservices;

    @Override
    public Map<String, Integer> thanhTichTheoKhoa(int hocKyId, int khoaId) {
        return calculateStatistics(hocKyId, khoaId, "khoa");
    }

    @Override
    public Map<String, Integer> thanhTichTheoLop(int hocKyId, int lopHocId) {
        return calculateStatistics(hocKyId, lopHocId, "lop");
    }

    private Map<String, Integer> calculateStatistics(int hocKyId, int id, String type) {
        Session session = factory.getObject().getCurrentSession();
        String hql;
        if (type.equals("khoa")) {
            hql = "SELECT u FROM User u WHERE u.idKhoa.id = :id AND u.userRole = :role";
        } else {
            hql = "SELECT u FROM User u WHERE u.idLopHoc.id = :id AND u.userRole = :role";
        }

        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        query.setParameter("role", "ROLE_SV");
        List<User> sinhVienList = query.getResultList();

        Map<String, Integer> soLuongSinhVienTheoXepLoai = new HashMap<>();
        soLuongSinhVienTheoXepLoai.put("Xuat Sac", 0);
        soLuongSinhVienTheoXepLoai.put("Gioi", 0);
        soLuongSinhVienTheoXepLoai.put("Kha", 0);
        soLuongSinhVienTheoXepLoai.put("Trung Binh", 0);
        soLuongSinhVienTheoXepLoai.put("Yeu", 0);
        soLuongSinhVienTheoXepLoai.put("Kem", 0);

        for (User sinhVien : sinhVienList) {
            int diemDieu1 = drlservices.tinhTongDiemDieu1(sinhVien.getId(), hocKyId);
            int diemDieu2 = drlservices.tinhTongDiemDieu2(sinhVien.getId(), hocKyId);
            int diemDieu3 = drlservices.tinhTongDiemDieu3(sinhVien.getId(), hocKyId);
            int tongDiem = diemDieu1 + diemDieu2 + diemDieu3;

            String xepLoai;
            if (tongDiem >= 90) {
                xepLoai = "Xuat Sac";
            } else if (tongDiem >= 80) {
                xepLoai = "Gioi";
            } else if (tongDiem >= 60) {
                xepLoai = "Kha";
            } else if (tongDiem >= 40) {
                xepLoai = "Trung Binh";
            } else if (tongDiem >= 30) {
                xepLoai = "Yeu";
            } else {
                xepLoai = "Kem";
            }
            soLuongSinhVienTheoXepLoai.put(xepLoai, soLuongSinhVienTheoXepLoai.get(xepLoai) + 1);
        }

        return soLuongSinhVienTheoXepLoai;
    }
}
