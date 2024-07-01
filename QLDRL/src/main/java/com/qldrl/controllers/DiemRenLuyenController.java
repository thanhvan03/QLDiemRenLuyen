    package com.qldrl.controllers;
import com.qldrl.pojo.User;
import com.qldrl.services.DiemRenLuyenServices;
import com.qldrl.services.HocKyServices;
import com.qldrl.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;

@Controller
public class DiemRenLuyenController {

    @Autowired
    private DiemRenLuyenServices diemRenLuyenService;

    @Autowired
    private HocKyServices hkServices;

    @Autowired
    private UserServices userServices;
       
    @GetMapping("tlsv/sinhvien/thanhtich")
    public String formThanhTich(Model model) {
        model.addAttribute("hockys", this.hkServices.getHocKy());
        return "thanhtichsv";
    }

    @GetMapping("tlsv/sinhvien/thanhtich/{idUser}/{idHocKy}")
    public String viewThanhTich(ModelMap model, @PathVariable("idUser") String idUser, @PathVariable("idHocKy") int idHocKy) {
        User u = this.userServices.getUserByMaSV(idUser);

        if (u != null) {
            int diemDieu1 = diemRenLuyenService.tinhTongDiemDieu1(u.getId(), idHocKy);
            int diemDieu2 = diemRenLuyenService.tinhTongDiemDieu2(u.getId(), idHocKy);
            int diemDieu3 = diemRenLuyenService.tinhTongDiemDieu3(u.getId(), idHocKy);
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
            res.put("diemDieu1", diemDieu1);
            res.put("diemDieu2", diemDieu2);
            res.put("diemDieu3", diemDieu3);
            res.put("tongDiem", tongDiem);
            res.put("xepLoai", xepLoai);

            model.addAttribute("results", res);
            model.addAttribute("sinhvien", u);
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy sinh viên có mã: " + idUser);
        }
        model.addAttribute("hockys", this.hkServices.getHocKy());
        return "thanhtichsv";
    }
}
