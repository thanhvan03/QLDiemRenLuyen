/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qldrl.controllers;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.qldrl.pojo.User;
import com.qldrl.services.DiemRenLuyenServices;
import com.qldrl.services.HocKyServices;
import com.qldrl.services.KhoaServices;
import com.qldrl.services.StatsServices;
import com.qldrl.services.UserServices;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author DELL
 */
@Controller
public class StatsController {

    @Autowired
    private StatsServices statsService;

    @Autowired
    private HocKyServices hkServices;

    @Autowired
    private KhoaServices khoaServices;

    @Autowired
    private DiemRenLuyenServices diemRenLuyenService;

    @Autowired
    private UserServices userServices;

    @GetMapping("admin/stats-khoa")
    public String stats_khoa(Model model, @RequestParam(required = false) Integer hocKyId, @RequestParam(required = false) Integer khoaId) {
        model.addAttribute("hockys", hkServices.getHocKy());
        model.addAttribute("khoas", khoaServices.getAllKhoa());
        if (hocKyId != null && khoaId != null) {
            Map<String, Integer> stats = statsService.thanhTichTheoKhoa(hocKyId, khoaId);
            model.addAttribute("stats", stats);
        }

        model.addAttribute("selectedHocKyId", hocKyId);
        model.addAttribute("selectedKhoaId", khoaId);

        return "thongkekhoa";
    }

    @GetMapping("tlsv/stats-lop")
    public String stats_lop(Model model, @RequestParam(required = false) Integer hocKyId, @RequestParam(required = false) Integer lopHocId) {
        model.addAttribute("hockys", hkServices.getHocKy());
        model.addAttribute("lophoc", khoaServices.getAllLopHoc());
        if (hocKyId != null && lopHocId != null) {
            Map<String, Integer> stats = statsService.thanhTichTheoLop(hocKyId, lopHocId);
            model.addAttribute("statsLop", stats);
        }

        model.addAttribute("selectedHocKyId", hocKyId);
        model.addAttribute("selectedLopHocId", lopHocId);

        return "thongkelop";
    }

    @GetMapping("tlsv/stats-lop/export-csv")
    public void exportcsv(HttpServletResponse response, @RequestParam Integer hocKyId, @RequestParam Integer lopHocId) throws IOException {
        List<User> students = userServices.getUserByLopHoc(lopHocId);

        // Set response headers
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=students.csv");

        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8); ICsvMapWriter csvWriter = new CsvMapWriter(writer, CsvPreference.STANDARD_PREFERENCE)) {

            // Write BOM (Byte Order Mark) for UTF-8
            writer.write('\uFEFF');

            // Write header
            String[] header = {"Tên Sinh Viên", "Mã Sinh Viên", "Điểm Loại 1", "Điểm Loại 2", "Điểm Loại 3", "Tổng điểm", "Xếp Loại"};
            csvWriter.writeHeader(header);

            // Write data
            for (User student : students) {
                int diemDieu1 = diemRenLuyenService.tinhTongDiemDieu1(student.getId(), hocKyId);
                int diemDieu2 = diemRenLuyenService.tinhTongDiemDieu2(student.getId(), hocKyId);
                int diemDieu3 = diemRenLuyenService.tinhTongDiemDieu3(student.getId(), hocKyId);
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

                // Create a map to hold the data
                Map<String, Object> studentData = new HashMap<>();
                studentData.put("Tên Sinh Viên", student.getTen());
                studentData.put("Mã Sinh Viên", student.getMasinhvien());
                studentData.put("Điểm Loại 1", diemDieu1);
                studentData.put("Điểm Loại 2", diemDieu2);
                studentData.put("Điểm Loại 3", diemDieu3);
                studentData.put("Tổng điểm", tongDiem);
                studentData.put("Xếp Loại", xepLoai);

                // Write the data
                csvWriter.write(studentData, header);
            }
        }
    }

    @GetMapping("tlsv/stats-lop/export-pdf")
    public void exportpdf(HttpServletResponse response, @RequestParam Integer hocKyId, @RequestParam Integer lopHocId) throws IOException {
        List<User> students = userServices.getUserByLopHoc(lopHocId);

        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=students.pdf");

        // Create PdfDocument
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Set font
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Paragraph title = new Paragraph("DANH SACH THANH TICH")
                .setFont(font)
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Add some spacing after the title
        document.add(new Paragraph("\n"));

        // Create table with 7 columns
        Table table = new Table(7);

        // Add table header
        table.addCell(new Cell().add(new Paragraph("Tên Sinh Viên").setFont(font).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph("Mã Sinh Viên").setFont(font).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph("Diem Loai 1").setFont(font).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph("Diem Loai 2").setFont(font).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph("Diem Loai 3").setFont(font).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph("Tong Diem").setFont(font).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph("Xep Loai").setFont(font).setTextAlignment(TextAlignment.CENTER)));

        // Add students' data to the table
        for (User student : students) {
            int diemDieu1 = diemRenLuyenService.tinhTongDiemDieu1(student.getId(), hocKyId);
            int diemDieu2 = diemRenLuyenService.tinhTongDiemDieu2(student.getId(), hocKyId);
            int diemDieu3 = diemRenLuyenService.tinhTongDiemDieu3(student.getId(), hocKyId);
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

            // Add student data to the table
            table.addCell(new Cell().add(new Paragraph(student.getTen()).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(student.getMasinhvien()).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(diemDieu1)).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(diemDieu2)).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(diemDieu3)).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(tongDiem)).setFont(font).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(xepLoai).setFont(font).setTextAlignment(TextAlignment.CENTER)));
        }

        // Add table to document
        document.add(table);

        // Close document
        document.close();
    }
}
