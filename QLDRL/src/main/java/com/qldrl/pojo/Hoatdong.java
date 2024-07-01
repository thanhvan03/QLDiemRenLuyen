package com.qldrl.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "hoatdong")
@NamedQueries({
    @NamedQuery(name = "Hoatdong.findAll", query = "SELECT h FROM Hoatdong h"),
    @NamedQuery(name = "Hoatdong.findById", query = "SELECT h FROM Hoatdong h WHERE h.id = :id"),
    @NamedQuery(name = "Hoatdong.findByTenhoatdong", query = "SELECT h FROM Hoatdong h WHERE h.tenhoatdong = :tenhoatdong"),
    @NamedQuery(name = "Hoatdong.findByNoidung", query = "SELECT h FROM Hoatdong h WHERE h.noidung = :noidung"),
    @NamedQuery(name = "Hoatdong.findByTieuchi", query = "SELECT h FROM Hoatdong h WHERE h.tieuchi = :tieuchi"),
    @NamedQuery(name = "Hoatdong.findByDiem", query = "SELECT h FROM Hoatdong h WHERE h.diem = :diem"),
    @NamedQuery(name = "Hoatdong.findByNgaytao", query = "SELECT h FROM Hoatdong h WHERE h.ngaytao = :ngaytao"),
    @NamedQuery(name = "Hoatdong.findByNgaybatdau", query = "SELECT h FROM Hoatdong h WHERE h.ngaybatdau = :ngaybatdau"),
    @NamedQuery(name = "Hoatdong.findByNgayketthuc", query = "SELECT h FROM Hoatdong h WHERE h.ngayketthuc = :ngayketthuc"),
    @NamedQuery(name = "Hoatdong.findByDiadiem", query = "SELECT h FROM Hoatdong h WHERE h.diadiem = :diadiem")})
public class Hoatdong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "{Hoatdong.tenhoatdong.nullErr}")
    @Size(max = 255)
    @Column(name = "tenhoatdong")
    private String tenhoatdong;

    @NotNull(message = "{Hoatdong.noidung.nullErr}")
    @Size(max = 255555555)
    @Column(name = "noidung")
    private String noidung;

    @Size(max = 6)
    @Column(name = "tieuchi")
    private String tieuchi;

    @Column(name = "diem")
    private Integer diem;

    @Column(name = "ngaytao")
    @Temporal(TemporalType.DATE)
    private Date ngaytao;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaybatdau")
    private Date ngaybatdau;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngayketthuc")
    private Date ngayketthuc;

    @Size(max = 255)
    @Column(name = "diadiem")
    private String diadiem;

    @JsonIgnore
    @OneToMany(mappedBy = "idHoatDong", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dangkyhoatdong> dangkyhoatdongSet;

    @JsonIgnore
    @OneToMany(mappedBy = "idHoatDong", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Diemrenluyen> diemrenluyenSet;

    @JsonIgnore
    @OneToMany(mappedBy = "idHoatDong", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Binhluan> binhluanSet;

    @JsonIgnore
    @OneToMany(mappedBy = "idHoatDong", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Baothieu> baothieuSet;

    @JsonIgnore
    @OneToMany(mappedBy = "idHoatDong", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Thich> thichSet;

    @JoinColumn(name = "idHocKy", referencedColumnName = "id")
    @ManyToOne
//    @JsonIgnore
    private Hocky idHocKy;

    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private User idUser;

    public Hoatdong() {
    }

    public Hoatdong(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenhoatdong() {
        return tenhoatdong;
    }

    public void setTenhoatdong(String tenhoatdong) {
        this.tenhoatdong = tenhoatdong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieuchi() {
        return tieuchi;
    }

    public void setTieuchi(String tieuchi) {
        this.tieuchi = tieuchi;
    }

    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    @XmlTransient
    public Set<Dangkyhoatdong> getDangkyhoatdongSet() {
        return dangkyhoatdongSet;
    }

    public void setDangkyhoatdongSet(Set<Dangkyhoatdong> dangkyhoatdongSet) {
        this.dangkyhoatdongSet = dangkyhoatdongSet;
    }

    @XmlTransient
    public Set<Diemrenluyen> getDiemrenluyenSet() {
        return diemrenluyenSet;
    }

    public void setDiemrenluyenSet(Set<Diemrenluyen> diemrenluyenSet) {
        this.diemrenluyenSet = diemrenluyenSet;
    }

    @XmlTransient
    public Set<Binhluan> getBinhluanSet() {
        return binhluanSet;
    }

    public void setBinhluanSet(Set<Binhluan> binhluanSet) {
        this.binhluanSet = binhluanSet;
    }

    @XmlTransient
    public Set<Baothieu> getBaothieuSet() {
        return baothieuSet;
    }

    public void setBaothieuSet(Set<Baothieu> baothieuSet) {
        this.baothieuSet = baothieuSet;
    }

    
    public Set<Thich> getThichSet() {
        return thichSet;
    }

    @XmlTransient
    public void setThichSet(Set<Thich> thichSet) {
        this.thichSet = thichSet;
    }

    public Hocky getIdHocKy() {
        return idHocKy;
    }

    public void setIdHocKy(Hocky idHocKy) {
        this.idHocKy = idHocKy;
    }

    public User getIdUser() {
        return idUser;
    }

    @XmlTransient
    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Hoatdong)) {
            return false;
        }
        Hoatdong other = (Hoatdong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qldrl.pojo.Hoatdong[ id=" + id + " ]";
    }
}
