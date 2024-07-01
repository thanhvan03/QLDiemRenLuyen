/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.qldrl.pojo.Dangkyhoatdong;
import com.qldrl.pojo.User;
import com.qldrl.services.DangKyHoatDongServices;
import com.qldrl.services.DiemRenLuyenServices;
import com.qldrl.services.HoatDongServices;
import com.qldrl.services.UserServices;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiDiemRenLuyenController {

    @Autowired
    private DiemRenLuyenServices drlServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private HoatDongServices hdServices;

    @Autowired
    private DangKyHoatDongServices dkyServices;

    private User getCurrentUser(Principal principal) {
        String username = principal.getName();
        User currentUser = this.userServices.getUserByUsername(username);

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        return currentUser;
    }

    ///TLSV Nhap diem cho sinh vien 
    @PostMapping("tlsv/diemrenluyen/nhapdiem/{hoatdongId}/{userId}")
    @CrossOrigin
    public ResponseEntity<String> nhapDiem(HttpSession session, @PathVariable("hoatdongId") int hoatdongId, @PathVariable("userId") int userId) {
        if (this.drlServices.ktraDRL(hoatdongId, userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Điểm rèn luyện đã tồn tại.");
        } else {
            Dangkyhoatdong d = this.dkyServices.getDangKySV(hoatdongId, userId);
            if (d != null) {
                d.setThamgia(Boolean.TRUE);
                this.dkyServices.setThamGia(d);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đăng ký hoạt động không tồn tại.");
            }
            User u = this.userServices.getUserById(userId);
            drlServices.taoDiemRenLuyen(this.hdServices.getHoatDongById(hoatdongId), u);
            return ResponseEntity.status(HttpStatus.CREATED).body("Điểm rèn luyện đã được tạo thành công.");
        }
    }

    ///TLSV nhập điểm CSV
    @PostMapping("tlsv/diemrenluyen/nhapdiem-csv/{hoatdongId}")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<String> nhapDiemCSV(@RequestParam("file") MultipartFile file, @PathVariable("hoatdongId") int hoatdongId) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try (InputStreamReader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8); ICsvMapReader csvReader = new CsvMapReader(reader, CsvPreference.STANDARD_PREFERENCE)) {

            // Read the header
            final String[] header = csvReader.getHeader(true);

            Map<String, String> csvRecord;
            while ((csvRecord = csvReader.read(header)) != null) {
                String maSv = csvRecord.get("MaSinhVien");
                String tenSv = csvRecord.get("TenSinhVien");

                // Tìm sinh viên theo mã sinh viên
                User student = userServices.getUserByMaSV(maSv);
                if (student == null) {
                    throw new RuntimeException("Student with maSv " + maSv + " and " + tenSv + " not found");
                }

                // Tìm đăng ký hoạt động của sinh viên
                Dangkyhoatdong dangKy = dkyServices.getDangKySV(hoatdongId, student.getId());
                if (dangKy != null) {
                    this.dkyServices.setThamGia(dangKy);
                    // Tạo hoặc cập nhật điểm rèn luyện cho sinh viên
                    drlServices.taoDiemRenLuyen(hdServices.getHoatDongById(hoatdongId), student);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing CSV file: " + e.getMessage());
        }
        return ResponseEntity.ok("SUCCESS");
    }

    //xem tổng điểm rèn luyện từng điều của user 
//    @GetMapping("/tongdiemdieu1/{sinhVienId}/{hocKyId}")
//    @CrossOrigin
//    public ResponseEntity<Integer> tongDiemDieu1(@PathVariable("sinhVienId") int sinhVienId, @PathVariable("hocKyId") int hocKyId) {
//        int totalDiemDieu1 = drlServices.tinhTongDiemDieu1(sinhVienId, hocKyId);
//        return ResponseEntity.ok(totalDiemDieu1);
//    }
//
//    @GetMapping("/tongdiemdieu2/{sinhVienId}/{hocKyId}")
//    @CrossOrigin
//    public ResponseEntity<Integer> tongDiemDieu2(@PathVariable("sinhVienId") int sinhVienId, @PathVariable("hocKyId") int hocKyId) {
//        int totalDiemDieu2 = drlServices.tinhTongDiemDieu2(sinhVienId, hocKyId);
//        return ResponseEntity.ok(totalDiemDieu2);
//    }
//
//    @GetMapping("/tongdiemdieu3/{sinhVienId}/{hocKyId}")
//    @CrossOrigin
//    public ResponseEntity<Integer> tongDiemDieu3(@RequestParam("sinhVienId") int sinhVienId, @RequestParam("hocKyId") int hocKyId) {
//        int totalDiemDieu3 = drlServices.tinhTongDiemDieu3(sinhVienId, hocKyId);
//        return ResponseEntity.ok(totalDiemDieu3);
//    }
    @GetMapping("/tongdiemdieu1/{hocKyId}")
    @CrossOrigin
    public ResponseEntity<Integer> tongDiemDieu1(Principal p, @PathVariable("hocKyId") int hocKyId) {
        User currentUser = getCurrentUser(p);
        int totalDiemDieu1 = drlServices.tinhTongDiemDieu1(currentUser.getId(), hocKyId);
        return ResponseEntity.ok(totalDiemDieu1);
    }

    @GetMapping("/tongdiemdieu2/{hocKyId}")
    @CrossOrigin
    public ResponseEntity<Integer> tongDiemDieu2(Principal p, @PathVariable("hocKyId") int hocKyId) {
        User currentUser = getCurrentUser(p);
        int totalDiemDieu2 = drlServices.tinhTongDiemDieu2(currentUser.getId(), hocKyId);
        return ResponseEntity.ok(totalDiemDieu2);
    }

    @GetMapping("/tongdiemdieu3/{hocKyId}")
    @CrossOrigin
    public ResponseEntity<Integer> tongDiemDieu3(Principal p, @PathVariable("hocKyId") int hocKyId) {
        User currentUser = getCurrentUser(p);
        int totalDiemDieu3 = drlServices.tinhTongDiemDieu3(currentUser.getId(), hocKyId);
        return ResponseEntity.ok(totalDiemDieu3);
    }

    @GetMapping("/sinhvien/thanhtich/{idHocKy}")
    @CrossOrigin
    public ResponseEntity<?> viewThanhTich(Principal p, @PathVariable("idHocKy") int idHocKy) {
        User u = getCurrentUser(p);

        if (u != null) {
            int diemDieu1 = drlServices.tinhTongDiemDieu1(u.getId(), idHocKy);
            int diemDieu2 = drlServices.tinhTongDiemDieu2(u.getId(), idHocKy);
            int diemDieu3 = drlServices.tinhTongDiemDieu3(u.getId(), idHocKy);
            int tongDiem = diemDieu1 + diemDieu2 + diemDieu3;

            String xepLoai;
            if (tongDiem >= 90) {
                xepLoai = "Xuất Sắc";
            } else if (tongDiem >= 80) {
                xepLoai = "Giỏi";
            } else if (tongDiem >= 60) {
                xepLoai = "Khá";
            } else if (tongDiem >= 40) {
                xepLoai = "Trung Bình";
            } else if (tongDiem >= 30) {
                xepLoai = "Yếu";
            } else {
                xepLoai = "Kém";
            }

            Map<String, Object> res = new HashMap<>();
            res.put("tongDiem", tongDiem);
            res.put("xepLoai", xepLoai);

            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sinh viên có mã: " + u.getMasinhvien());
        }
    }

    @GetMapping("/diemhoatdong/{hoatDongId}")
    @CrossOrigin
    public ResponseEntity<Integer> xemdiem(Principal p, @PathVariable("hoatDongId") int hoatDongId) {
        User currentUser = getCurrentUser(p);
        int totalDiem = drlServices.getDiemSVDatDuoc(currentUser.getId(), hoatDongId);
        return ResponseEntity.ok(totalDiem);
    }

    @GetMapping("/tongdiem/{hocKyId}")
    @CrossOrigin
    public ResponseEntity<Integer> tongDiem(Principal p, @PathVariable("hocKyId") int hocKyId) {
        User currentUser = getCurrentUser(p);
        int totalDiem = drlServices.tinhTongDiemHocKy(currentUser.getId(), hocKyId);
        return ResponseEntity.ok(totalDiem);
    }
}
